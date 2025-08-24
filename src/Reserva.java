import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reserva {
    private int id;
    private List<Boleto> boletos = new ArrayList<>();
    private Date fechaReserva;
    private long duracionMinutos;

    public Reserva(int id, long duracionMinutos) {
        this.id = id;
        this.duracionMinutos = duracionMinutos;
        this.fechaReserva = new Date();
    }

    public void agregarBoleto(Boleto boleto) {
        boletos.add(boleto);
        boleto.setEstado(Boleto.Estado.RESERVADO);
    }

    public boolean estaActiva() {
        long diff = (new Date().getTime() - fechaReserva.getTime()) / 60000;
        return diff < duracionMinutos;
    }

    public List<Boleto> getBoletos() {
        return boletos;
    }
}
