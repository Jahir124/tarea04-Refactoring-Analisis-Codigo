import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class OrdenCompraTest {

    @Test
    void testCrearOrdenCompraYAgregarBoletos() {
        Comprador comprador = new Comprador(1, "Juan Pérez", "juan@correo.com");

        OrdenCompra ordenCompra = new OrdenCompra(1, comprador);


        Evento evento = new Evento(1, "Concierto de Rock", "Estadio Nacional");
        Boleto boleto1 = new Boleto(1, evento, "VIP", 100.0);
        Boleto boleto2 = new Boleto(2, evento, "General", 50.0);
        ordenCompra.agregarBoleto(boleto1);
        ordenCompra.agregarBoleto(boleto2);

        assertEquals(2, ordenCompra.getBoletos().size(), "La cantidad de boletos debería ser 2");
        assertEquals(comprador, ordenCompra.getComprador(), "El comprador no coincide");
        assertEquals(2, ordenCompra.contarBoletosParaEvento(1), "El total de boletos para el evento debería ser 2");
    }

  @Test
    void testMarcarOrdenComoPagada() {
        Comprador comprador = new Comprador(1, "Juan Pérez", "juan@correo.com");

        
        OrdenCompra ordenCompra = new OrdenCompra(1, comprador);

        Evento evento = new Evento(1, "Concierto de Rock", "Estadio Nacional");
        Boleto boleto = new Boleto(1, evento, "VIP", 100.0);


        ordenCompra.agregarBoleto(boleto);

        assertFalse(ordenCompra.isPagado(), "La orden debería no estar pagada inicialmente");

        ordenCompra.marcarPagado();

        assertTrue(ordenCompra.isPagado(), "La orden debería estar pagada ahora");
    }

    @Test
    void testNoPermitirBoletosDuplicados() {
        Comprador comprador = new Comprador(1, "Ana", "ana@mail.com");
        OrdenCompra orden = new OrdenCompra(1, comprador);
        Evento evento = new Evento(1, "Concierto", "Teatro");
        Boleto boleto = new Boleto(1, evento, "VIP", 120.0);
    
        orden.agregarBoleto(boleto);
        orden.agregarBoleto(boleto); // intento duplicado
    
        assertEquals(1, orden.getBoletos().size(), "No debería aceptar boletos duplicados");
    }

    @Test
    void testContarBoletosEnOrdenVacia() {
        Comprador comprador = new Comprador(2, "Pedro", "pedro@mail.com");
        OrdenCompra orden = new OrdenCompra(2, comprador);
    
        assertEquals(0, orden.contarBoletosParaEvento(1), "Una orden vacía no debe tener boletos");
    }
    
       
}
