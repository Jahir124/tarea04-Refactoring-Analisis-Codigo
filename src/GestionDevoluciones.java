import java.util.Date;

public class GestionDevoluciones {

    private PoliticaDevolucion politica;

    public GestionDevoluciones(PoliticaDevolucion politica) {
        this.politica = politica;
    }

    public boolean procesarDevolucion(Boleto boleto) {
        Date fechaCompra = boleto.getFechaCompra();
        if (politica.aplicarDevolucion(fechaCompra)) {
            boleto.setEstado(Boleto.Estado.CANCELADO);
            // Aquí podrías añadir lógica para devolver dinero
            return true;
        }
        return false;
    }
}
