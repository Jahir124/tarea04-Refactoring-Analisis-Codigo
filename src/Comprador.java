import java.util.ArrayList;
import java.util.List;

public class Comprador extends Persona {
    private List<OrdenCompra> ordenes = new ArrayList<>();

    public Comprador(int id, String nombre, String email) {
        super(id, nombre, email);
    }

    public void agregarOrden(OrdenCompra orden) {
        ordenes.add(orden);
    }

    public int totalBoletosCompradosParaEvento(int eventoId) {
        int total = 0;
        for (OrdenCompra orden : ordenes) {
            total += orden.contarBoletosParaEvento(eventoId);
        }
        return total;
    }

    public List<OrdenCompra> getOrdenes() {
        return ordenes;
    }
}
