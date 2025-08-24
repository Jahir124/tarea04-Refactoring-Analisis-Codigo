import java.util.Date;

public class Boleto {
    public enum Estado { DISPONIBLE, RESERVADO, VENDIDO, CANCELADO }

    private int id;
    private Evento evento;
    private Estado estado;
    private Date fechaCompra;

    public Boleto(int id, Evento evento) {
        this.id = id;
        this.evento = evento;
        this.estado = Estado.DISPONIBLE;
    }

    // Getters y setters
    public int getId() { return id; }
    public Evento getEvento() { return evento; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public Date getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(Date fechaCompra) { this.fechaCompra = fechaCompra; }
}
