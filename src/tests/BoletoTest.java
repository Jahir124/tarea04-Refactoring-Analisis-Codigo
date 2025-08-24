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
        boleto = new Boleto(1, evento);
    }

    @Test
    void testCambioEstadoBoleto() {
        boleto.setEstado(Boleto.Estado.RESERVADO);
        assertEquals(Boleto.Estado.RESERVADO, boleto.getEstado(), "El boleto debería estar en estado RESERVADO");
    }

    @Test
    void testEstadoInicialDisponible() {
        assertEquals(Boleto.Estado.DISPONIBLE, boleto.getEstado(), "El boleto recién creado debe estar DISPONIBLE");
    }

    @Test
    void testSetEstadoVendido() {
        boleto.setEstado(Boleto.Estado.VENDIDO);
        assertEquals(Boleto.Estado.VENDIDO, boleto.getEstado(), "El boleto debería estar en estado VENDIDO");
    }

    @Test
    void testSetEstadoCancelado() {
        boleto.setEstado(Boleto.Estado.CANCELADO);
        assertEquals(Boleto.Estado.CANCELADO, boleto.getEstado(), "El boleto debería estar en estado CANCELADO");
    }

}
