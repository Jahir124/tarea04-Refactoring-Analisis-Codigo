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

    @Test
    void testReservaInicialSinBoletos() {
        Reserva reserva = new Reserva(2, 5);
        assertTrue(reserva.getBoletos().isEmpty(), "Una reserva recién creada no debe tener boletos");
    }

    @Test
    void testReservaExpiradaCambiaEstadoBoletos() throws InterruptedException {
        Reserva reserva = new Reserva(3, 0); // expira inmediato
        Evento evento = new Evento(1, "Evento", "Lugar");
        Boleto b1 = new Boleto(1, evento);
        Boleto b2 = new Boleto(2, evento);
    
        reserva.agregarBoleto(b1);
        reserva.agregarBoleto(b2);
    
        Thread.sleep(100); // pequeña espera
        assertFalse(reserva.estaActiva());
        assertEquals(Boleto.Estado.EXPIRADO, b1.getEstado());
        assertEquals(Boleto.Estado.EXPIRADO, b2.getEstado());
    }

    @Test
    void testAgregarBoletoYaReservado() {
        Evento evento = new Evento(2, "Show", "Coliseo");
        Boleto boleto = new Boleto(10, evento);
        boleto.setEstado(Boleto.Estado.RESERVADO);
    
        Reserva reserva = new Reserva(5, 15);
    
        assertThrows(IllegalStateException.class, () -> reserva.agregarBoleto(boleto));
    }
       
    
}

