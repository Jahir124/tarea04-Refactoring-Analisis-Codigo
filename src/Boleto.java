import java.util.Date;

public class Boleto {
    public enum Estado { DISPONIBLE, RESERVADO, VENDIDO, CANCELADO, EXPIRADO } 

    private int id;
    private Evento evento;
    private Estado estado;
    private Date fechaCompra;
    private double precio; 

    public Boleto(int id, Evento evento) {
        this(id, evento, 0.0);
    }

    public Boleto(int id, Evento evento, double precio) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo");
        this.id = id;
        this.evento = evento;
        this.precio = precio;
        this.estado = Estado.DISPONIBLE;
    }

    public double getPrecio() { return precio; }

    // Getters y setters
    public int getId() { return id; }
    public Evento getEvento() { return evento; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public Date getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(Date fechaCompra) { this.fechaCompra = fechaCompra; }
}
