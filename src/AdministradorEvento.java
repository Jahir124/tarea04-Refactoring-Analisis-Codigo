public class AdministradorEvento extends Persona implements ManejadorIncidente {
    private ManejadorIncidente siguiente;

    public AdministradorEvento(int id, String nombre, String email) {
        super(id, nombre, email);
    }

    @Override
    public boolean atenderIncidente(Incidente incidente) {
        incidente.asignarResponsable(this);
        incidente.actualizarEstado(EstadoIncidente.RESUELTO);
        return true;
    }

    @Override
    public void setSiguiente(ManejadorIncidente siguiente) {
        this.siguiente = siguiente;
    }
}
