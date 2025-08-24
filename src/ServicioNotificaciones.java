import java.util.ArrayList;
import java.util.List;

public class ServicioNotificaciones {
    private List<Notificacion> observadores = new ArrayList<>();

    public void agregarObservador(Notificacion notificacion) {
        observadores.add(notificacion);
    }

    public void removerObservador(Notificacion notificacion) {
        observadores.remove(notificacion);
    }

    public void notificar(String mensaje) {
        for (Notificacion notificacion : observadores) {
            notificacion.enviar();
        }
    }
}
