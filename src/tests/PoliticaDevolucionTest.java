package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class PoliticaDevolucionTest {

    private PoliticaDevolucion politica;

    @BeforeEach
    public void setup() {
        politica = new PoliticaDevolucion(7); // 7 días para devolver
    }

    @Test
    public void testAplicarDevolucionDentroDePlazo() {
        Date fechaCompra = new Date();
        assertTrue(politica.aplicarDevolucion(fechaCompra));
    }

    @Test
    public void testAplicarDevolucionFueraDePlazo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -10);
        Date fechaCompra = cal.getTime();
        assertFalse(politica.aplicarDevolucion(fechaCompra));
    }
}
