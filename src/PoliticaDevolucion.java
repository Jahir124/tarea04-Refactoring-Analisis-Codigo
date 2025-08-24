import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PoliticaDevolucion {
    private int plazoDias;

    public PoliticaDevolucion(int plazoDias) {
        this.plazoDias = plazoDias;
    }

    public boolean aplicarDevolucion(Date fechaCompra) {
        long diffInMillies = new Date().getTime() - fechaCompra.getTime();
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diffInDays <= plazoDias;
    }

    public int getPlazoDias() {
        return plazoDias;
    }

    public void setPlazoDias(int plazoDias) {
        this.plazoDias = plazoDias;
    }
}
