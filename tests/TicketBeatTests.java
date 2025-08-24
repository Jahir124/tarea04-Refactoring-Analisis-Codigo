import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class TicketBeatTests {

    @Test
    public void testAgregarOrdenAComprador() {
        Comprador comprador = new Comprador(1, "Jahir", "jahir@example.com");
        OrdenCompra orden = new OrdenCompra(100, comprador);
        comprador.agregarOrden(orden);

        assertEquals(1, comprador.getOrdenes().size());
        assertEquals(orden, comprador.getOrdenes().get(0));
    }

    @Test
    public void testContarBoletosParaEvento() {
        Comprador comprador = new Comprador(1, "Jahir", "jahir@example.com");
        OrdenCompra orden = new OrdenCompra(100, comprador);

        Evento evento1 = new Evento(10, "Concierto", "Arena", new Date(), new Date(), "Artista", "Rock");
        Evento evento2 = new Evento(11, "Festival", "Parque", new Date(), new Date(), "Artista2", "Pop");

        Boleto boleto1 = new Boleto(1, evento1);
        Boleto boleto2 = new Boleto(2, evento1);
        Boleto boleto3 = new Boleto(3, evento2);

        orden.agregarBoleto(boleto1);
        orden.agregarBoleto(boleto2);
        orden.agregarBoleto(boleto3);

        comprador.agregarOrden(orden);

        assertEquals(2, orden.contarBoletosParaEvento(evento1.getId()));
        assertEquals(3, comprador.totalBoletosCompradosParaEvento(evento1.getId())); // en este caso solo una orden
    }

    @Test
    public void testReservaEstadoYExpiracion() throws InterruptedException {
        Reserva reserva = new Reserva(1, 1); // 1 minuto de duración
        Evento evento = new Evento(1, "Evento", "Lugar", new Date(), new Date(), "Artista", "Pop");
        Boleto boleto = new Boleto(1, evento);

        reserva.agregarBoleto(boleto);

        assertEquals(Boleto.Estado.RESERVADO, boleto.getEstado());
        assertTrue(reserva.estaActiva());


        Thread.sleep(61 * 1000);
        assertFalse(reserva.estaActiva());
    }

    @Test
    public void testGestionDevoluciones_aplicaDevolucion() {
        PoliticaDevolucion politica = new PoliticaDevolucion(30); 
        GestionDevoluciones gestion = new GestionDevoluciones(politica);

        Evento evento = new Evento(1, "Evento", "Lugar", new Date(), new Date(), "Artista", "Pop");
        Boleto boleto = new Boleto(1, evento);

        // Simular compra hace 10 días (menos que plazo)
        Date fechaCompra = new Date(System.currentTimeMillis() - (10L * 24 * 60 * 60 * 1000));
        boleto.setFechaCompra(fechaCompra);

        boolean resultado = gestion.procesarDevolucion(boleto);

        assertTrue(resultado);
        assertEquals(Boleto.Estado.CANCELADO, boleto.getEstado());
    }

    @Test
    public void testGestionDevoluciones_noAplicaDevolucion() {
        PoliticaDevolucion politica = new PoliticaDevolucion(30);
        GestionDevoluciones gestion = new GestionDevoluciones(politica);

        Evento evento = new Evento(1, "Evento", "Lugar", new Date(), new Date(), "Artista", "Pop");
        Boleto boleto = new Boleto(1, evento);

        // Simular compra hace 40 días (más que plazo)
        Date fechaCompra = new Date(System.currentTimeMillis() - (40L * 24 * 60 * 60 * 1000));
        boleto.setFechaCompra(fechaCompra);

        boolean resultado = gestion.procesarDevolucion(boleto);

        assertFalse(resultado);
        assertNotEquals(Boleto.Estado.CANCELADO, boleto.getEstado());
    }

    @Test
    public void testOrdenCompraMarcarPagado() {
        Comprador comprador = new Comprador(1, "Jahir", "jahir@example.com");
        OrdenCompra orden = new OrdenCompra(100, comprador);

        assertFalse(orden.isPagado());
        orden.marcarPagado();
        assertTrue(orden.isPagado());
    }

    @Test
    public void testBoletoEstadoInicial() {
        Evento evento = new Evento(1, "Evento", "Lugar", new Date(), new Date(), "Artista", "Pop");
        Boleto boleto = new Boleto(1, evento);
        assertEquals(Boleto.Estado.DISPONIBLE, boleto.getEstado());
        assertEquals(evento, boleto.getEvento());
    }

    @Test
    public void testAgregarEventoAOrganizador() {
        Organizador organizador = new Organizador(1, "Org1");
        Evento evento = new Evento(1, "Evento", "Lugar", new Date(), new Date(), "Artista", "Pop");
        organizador.agregarEvento(evento);
        assertEquals(1, organizador.getEventos().size());
        assertEquals(evento, organizador.getEventos().get(0));
    }

    @Test
    public void testNotificacionEmailEnvio() {
        Notificacion email = new AdaptadorEmail("user@example.com", "Mensaje de prueba");
        assertTrue(email.enviar());
    }

    @Test
    public void testNotificacionSMSEnvio() {
        Notificacion sms = new NotificacionSMS("1234567890", "Mensaje SMS");
        assertTrue(sms.enviar());
    }
}
