import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Organizador {
    private int id;
    private String nombre;
    private List<Evento> eventos = new ArrayList<>();

    public Organizador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void agregarEvento(Evento evento) {
        eventos.add(evento);
    }

    public void cambiarFechaEvento(Evento evento, Date nuevaFechaInicio, Date nuevaFechaFin) {
        evento.setFechaInicio(nuevaFechaInicio);
        evento.setFechaFin(nuevaFechaFin);
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public List<Evento> getEventos() { return eventos; }
}
