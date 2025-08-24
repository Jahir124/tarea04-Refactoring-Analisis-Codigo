package tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class IncidenteAsignacionResponsableTest {

    @Test
    public void asignarResponsable_AgenteSoporte() {
        Comprador comprador = new Comprador(1, "Ana", "ana@correo.com");
        Incidente incidente = new Incidente(10, "Consulta general", comprador);

        AgenteSoporte agente = new AgenteSoporte(2, "Pedro", "pedro@soporte.com");
        incidente.asignarResponsable(agente);

        assertNotNull(incidente.getResponsable());
        assertTrue(incidente.getResponsable() instanceof AgenteSoporte);
    }

    @Test
    public void asignarResponsable_AdministradorEvento() {
        Comprador comprador = new Comprador(1, "Ana", "ana@correo.com");
        Incidente incidente = new Incidente(11, "Consulta general", comprador);

        AdministradorEvento admin = new AdministradorEvento(3, "Laura", "laura@evento.com");
        incidente.asignarResponsable(admin);

        assertNotNull(incidente.getResponsable());
        assertTrue(incidente.getResponsable() instanceof AdministradorEvento);
    }
}
