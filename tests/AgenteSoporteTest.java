package tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AgenteSoporteTest {

    private AgenteSoporte agente;
    private AdministradorEvento administrador;
    private Incidente incidente;

    @BeforeEach
    public void setup() {
        agente = new AgenteSoporte(1, "Pedro Soporte", "pedro@soporte.com");
        administrador = new AdministradorEvento(2, "Laura Admin", "laura@evento.com");

        // Cadena de responsabilidad: agente → administrador
        agente.setSiguiente(administrador);

        Comprador comprador = new Comprador(3, "Carlos Comprador", "carlos@comprador.com");
        incidente = new Incidente(101, "Problema de acceso a boleto", 
                                   TipoIncidente.ACCESO_BOLETO_DIGITAL, comprador);
    }

    @Test
    public void testAgenteResuelveIncidenteEnPrimerNivel() {
        boolean resuelto = agente.atenderIncidente(incidente);

        assertTrue(resuelto, "El agente debería resolver el incidente");
        assertEquals(EstadoIncidente.RESUELTO, incidente.getEstado(), "El incidente debe marcarse como resuelto");
        assertNull(incidente.getAdministradorEventoAsignado(), "No debe asignarse un administrador porque no se escaló");
    }
}
