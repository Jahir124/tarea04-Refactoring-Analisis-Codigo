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
       if (boleto.getEstado() == Boleto.Estado.RESERVADO) {
          throw new IllegalStateException("El boleto ya está reservado");
       }
       boletos.add(boleto);
       boleto.setEstado(Boleto.Estado.RESERVADO);
    }

    public boolean estaActiva() {
       long diff = (new Date().getTime() - fechaReserva.getTime()) / 60000;
       boolean activa = diff < duracionMinutos;
       if (!activa) {
          for (Boleto b : boletos) {
              if (b.getEstado() == Boleto.Estado.RESERVADO) {
                b.setEstado(Boleto.Estado.EXPIRADO); 
              }
          }
      }
      return activa;
     }


    public List<Boleto> getBoletos() {
        return boletos;
    }
}
