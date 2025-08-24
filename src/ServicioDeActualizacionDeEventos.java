import java.util.Date;
import java.util.List;

public class ServicioDeActualizacionDeEventos {

    public void cambiarFechaEvento(Evento evento, Date nuevaFechaInicio, Date nuevaFechaFin, List<Boleto> boletos) {
        evento.setFechaInicio(nuevaFechaInicio);
        evento.setFechaFin(nuevaFechaFin);

        for (Boleto boleto : boletos) {
            if (boleto.getEvento().getId() == evento.getId()) {
                boleto.setEstado(Boleto.Estado.CANCELADO);
                // Aquí crearías nuevo boleto con la nueva fecha si es necesario
            }
        }
    }
}
