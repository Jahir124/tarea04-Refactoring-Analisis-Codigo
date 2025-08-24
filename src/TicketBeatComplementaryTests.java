import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
public class TicketBeatComplementaryTests {

    private Evento eventoA;
    private Evento eventoB;
    private Comprador comprador;

    @BeforeEach
    void init() {
        eventoA = new Evento(1, "Rock Fest", "GYE",
                new Date(), new Date(), "Artista A", "Rock");
        eventoB = new Evento(2, "Indie Night", "UIO",
                new Date(), new Date(), "Artista B", "Indie");
        comprador = new Comprador(10, "Juan", "juan@mail.com");
    }

    // TB-01
    @Test
    void devolucionDentroDePlazo_cancelaBoleto() {
        PoliticaDevolucion pol = new PoliticaDevolucion(7);
        GestionDevoluciones gd = new GestionDevoluciones(pol);

        Boleto b = new Boleto(100, eventoA);
        b.setFechaCompra(UtilFechas.hoyMenosDias(2));

        boolean ok = gd.procesarDevolucion(b);

        assertAll(
            () -> assertTrue(ok),
            () -> assertEquals(Boleto.Estado.CANCELADO, b.getEstado())
        );
    }

    // TB-02
    @Test
    void devolucionFueraDePlazo_noCancela() {
        PoliticaDevolucion pol = new PoliticaDevolucion(7);
        GestionDevoluciones gd = new GestionDevoluciones(pol);

        Boleto b = new Boleto(101, eventoA);
        b.setFechaCompra(UtilFechas.hoyMenosDias(10)); 
        b.setEstado(Boleto.Estado.VENDIDO);

        boolean ok = gd.procesarDevolucion(b);

        assertAll(
            () -> assertFalse(ok),
            () -> assertEquals(Boleto.Estado.VENDIDO, b.getEstado())
        );
    }

    // TB-03
    @Test
    void reservaActiva() {
        Reserva r = new Reserva(1, 30);
        r.agregarBoleto(new Boleto(1, eventoA));
        assertTrue(r.estaActiva());
    }

    // TB-04
    @Test
    void reservaExpirada() throws InterruptedException {
        Reserva r = new Reserva(2, 0);
        assertFalse(r.estaActiva());
    }

    // TB-05
    @Test
    void agenteSoporteResuelveIncidenteAcceso() {
        AgenteSoporte soporte = new AgenteSoporte(20, "Soporte", "s@tb.com");
        Incidente inc = new Incidente(1, "Problema de acceso boleto en la app", comprador);

        boolean res = soporte.atenderIncidente(inc);

        assertAll(
            () -> assertTrue(res),
            () -> assertEquals(EstadoIncidente.RESUELTO, inc.getEstado()),
            () -> assertEquals(soporte.getId(), inc.getResponsable().getId())
        );
    }

    // TB-06
    @Test
    void cadenaResponsabilidadEscalaAdministrador() {
        AgenteSoporte soporte = new AgenteSoporte(21, "Soporte", "s@tb.com");
        AdministradorEvento admin = new AdministradorEvento(22, "Admin", "a@tb.com");
        soporte.setSiguiente(admin);

        Incidente inc = new Incidente(2, "Fallo extraño no identificado", comprador);

        boolean res = soporte.atenderIncidente(inc);

        assertAll(
            () -> assertTrue(res),
            () -> assertEquals(EstadoIncidente.RESUELTO, inc.getEstado()),
            () -> assertEquals(admin.getId(), inc.getResponsable().getId())
        );
    }

    // TB-07
    @Test
    void cambioFechaCancelaBoletosDelEvento() {
        ServicioDeActualizacionDeEventos svc = new ServicioDeActualizacionDeEventos();

        Boleto ba1 = new Boleto(1, eventoA);
        Boleto ba2 = new Boleto(2, eventoA);
        Boleto bb1 = new Boleto(3, eventoB);
        List<Boleto> todos = Arrays.asList(ba1, ba2, bb1);

        Date nuevaI = new Date(System.currentTimeMillis() + 86_400_000L);
        Date nuevaF = new Date(System.currentTimeMillis() + 172_800_000L);

        svc.cambiarFechaEvento(eventoA, nuevaI, nuevaF, todos);

        assertAll(
            () -> assertEquals(Boleto.Estado.CANCELADO, ba1.getEstado()),
            () -> assertEquals(Boleto.Estado.CANCELADO, ba2.getEstado()),
            () -> assertNotEquals(Boleto.Estado.CANCELADO, bb1.getEstado())
        );
    }

    // TB-08
    @Test
    void contarBoletosPorEventoEnOrdenCompra() {
        OrdenCompra oc = new OrdenCompra(30, comprador);
        oc.agregarBoleto(new Boleto(1, eventoA));
        oc.agregarBoleto(new Boleto(2, eventoA));
        oc.agregarBoleto(new Boleto(3, eventoB));

        assertAll(
            () -> assertEquals(2, oc.contarBoletosParaEvento(eventoA.getId())),
            () -> assertEquals(1, oc.contarBoletosParaEvento(eventoB.getId()))
        );
    }

    // TB-09
    @Test
    void totalPorEventoEnCompradorVariasOrdenes() {
        OrdenCompra oc1 = new OrdenCompra(31, comprador);
        oc1.agregarBoleto(new Boleto(1, eventoA));
        oc1.agregarBoleto(new Boleto(2, eventoB));

        OrdenCompra oc2 = new OrdenCompra(32, comprador);
        oc2.agregarBoleto(new Boleto(3, eventoA));

        comprador.agregarOrden(oc1);
        comprador.agregarOrden(oc2);

        assertEquals(2, comprador.totalBoletosCompradosParaEvento(eventoA.getId()));
    }

    // TB-10
    @Test
    void cerrarIncidenteDefineFechaResolucion() {
        Incidente inc = new Incidente(50, "Pago duplicado", comprador);
        assertNull(inc.getFechaResolucion());
        inc.cerrar();
        assertAll(
            () -> assertEquals(EstadoIncidente.CERRADO, inc.getEstado()),
            () -> assertNotNull(inc.getFechaResolucion())
        );
    }
}
