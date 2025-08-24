import java.util.Date;

public class Evento {
    private int id;
    private String nombre;
    private String lugar;
    private Date fechaInicio;
    private Date fechaFin;
    private String artista; 
    private String genero; 

    public Evento(int id, String nombre, String lugar, Date fechaInicio, Date fechaFin, String artista, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.lugar = lugar;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.artista = artista;
        this.genero = genero;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getLugar() { return lugar; }
    public Date getFechaInicio() { return fechaInicio; }
    public Date getFechaFin() { return fechaFin; }
    public String getArtista() { return artista; }
    public String getGenero() { return genero; }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
