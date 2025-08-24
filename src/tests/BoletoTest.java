package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoletoTest {

    private Evento evento;
    private Boleto boleto;

    @BeforeEach
    void setup() {
        evento = new Evento(1, "Concierto Test", "Estadio", null, null, "Artista", "Genero");
        boleto = new Boleto(1, evento); // estado inicial DISPONIBLE
    }

    // TC-Boleto-01
    @Test
    void testCambioEstadoBoletoADisponible() {
        boleto.setEstado(Boleto.Estado.DISPONIBLE);
        assertEquals(Boleto.Estado.DISPONIBLE, boleto.getEstado(), "El boleto debería estar DISPONIBLE");
    }

    // TC-Boleto-02
    @Test
    void testCambioEstadoBoletoAReservado() {
        boleto.setEstado(Boleto.Estado.RESERVADO);
        assertEquals(Boleto.Estado.RESERVADO, boleto.getEstado(), "El boleto debería estar RESERVADO");
    }

    // TC-Boleto-03
    @Test
    void testCambioEstadoBoletoAVendido() {
        boleto.setEstado(Boleto.Estado.VENDIDO);
        assertEquals(Boleto.Estado.VENDIDO, boleto.getEstado(), "El boleto debería estar VENDIDO");
    }

    // TC-Boleto-04
    @Test
    void testCambioEstadoBoletoACancelado() {
        boleto.setEstado(Boleto.Estado.CANCELADO);
        assertEquals(Boleto.Estado.CANCELADO, boleto.getEstado(), "El boleto debería estar CANCELADO");
    }

    // TC-Boleto-05
    @Test
    void testCambiarEstadosMultiplesVeces() {
        boleto.setEstado(Boleto.Estado.RESERVADO);
        assertEquals(Boleto.Estado.RESERVADO, boleto.getEstado());

        boleto.setEstado(Boleto.Estado.VENDIDO);
        assertEquals(Boleto.Estado.VENDIDO, boleto.getEstado());

        boleto.setEstado(Boleto.Estado.CANCELADO);
        assertEquals(Boleto.Estado.CANCELADO, boleto.getEstado());

        boleto.setEstado(Boleto.Estado.DISPONIBLE);
        assertEquals(Boleto.Estado.DISPONIBLE, boleto.getEstado());
    }
}
