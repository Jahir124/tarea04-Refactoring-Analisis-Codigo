import java.util.Date;

public class Incidente {
    private int id;
    private String descripcion;
    private EstadoIncidente estado;
    private Comprador usuarioReporta;
    private Persona responsable;
    private Date fechaCreacion;
    private Date fechaResolucion;

    public Incidente(int id, String descripcion, Comprador usuarioReporta) {
        this.id = id;
        this.descripcion = descripcion;
        this.usuarioReporta = usuarioReporta;
        this.estado = EstadoIncidente.ABIERTO;
        this.fechaCreacion = new Date();
    }

    public void asignarResponsable(Persona responsable) {
        this.responsable = responsable;
    }

    public void actualizarEstado(EstadoIncidente nuevoEstado) {
        this.estado = nuevoEstado;
        if (nuevoEstado == EstadoIncidente.CERRADO || nuevoEstado == EstadoIncidente.RESUELTO) {
            this.fechaResolucion = new Date();
        }
    }

    public void cerrar() {
        actualizarEstado(EstadoIncidente.CERRADO);
    }

    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public EstadoIncidente getEstado() { return estado; }
    public Comprador getUsuarioReporta() { return usuarioReporta; }
    public Persona getResponsable() { return responsable; }
    public Date getFechaCreacion() { return fechaCreacion; }
    public Date getFechaResolucion() { return fechaResolucion; }
}
