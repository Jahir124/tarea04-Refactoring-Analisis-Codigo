package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReservaTest {

    private Reserva reserva;
    private Boleto boleto;

    @BeforeEach
    public void setup() {
        reserva = new Reserva(1, 10); // duración 10 minutos
        Evento evento = new Evento(1, "Evento Test", "Lugar", null, null, "Artista", "Género");
        boleto = new Boleto(1, evento);
    }

    @Test
    public void testAgregarBoletoYEstado() {
        reserva.agregarBoleto(boleto);
        assertTrue(reserva.getBoletos().contains(boleto));
        assertEquals(Boleto.Estado.RESERVADO, boleto.getEstado());
    }

    @Test
    public void testEstaActivaDentroDeDuracion() {
        assertTrue(reserva.estaActiva());
    }

    @Test
    public void testEstaActivaDespuesDeDuracion() throws InterruptedException {
        // Esperar 11 minutos para pasar la duración
        Thread.sleep(11 * 60 * 1000);
        assertFalse(reserva.estaActiva());
    }
}

