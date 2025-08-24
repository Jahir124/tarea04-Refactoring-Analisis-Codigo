import java.util.ArrayList;
import java.util.List;

public class OrdenCompra {
    private int id;
    private Comprador comprador;
    private List<Boleto> boletos = new ArrayList<>();
    private boolean pagado = false;

    public OrdenCompra(int id, Comprador comprador) {
        this.id = id;
        this.comprador = comprador;
    }

    public void agregarBoleto(Boleto boleto) {
        boletos.add(boleto);
    }

    public int contarBoletosParaEvento(int eventoId) {
        int total = 0;
        for (Boleto boleto : boletos) {
            if (boleto.getEvento().getId() == eventoId) {
                total++;
            }
        }
        return total;
    }

    public void marcarPagado() {
        this.pagado = true;
    }

    public boolean isPagado() {
        return pagado;
    }

    public int getId() { return id; }
    public Comprador getComprador() { return comprador; }
    public List<Boleto> getBoletos() { return boletos; }
}
