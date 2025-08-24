public class AgenteSoporte extends Persona implements ManejadorIncidente {
    private ManejadorIncidente siguiente;

    public AgenteSoporte(int id, String nombre, String email) {
        super(id, nombre, email);
    }

    @Override
    public boolean atenderIncidente(Incidente incidente) {
        if (incidente.getDescripcion().toLowerCase().contains("acceso boleto")) {
            incidente.asignarResponsable(this);
            incidente.actualizarEstado(EstadoIncidente.RESUELTO);
            return true;
        } else if (siguiente != null) {
            return siguiente.atenderIncidente(incidente);
        }
        return false;
    }

    @Override
    public void setSiguiente(ManejadorIncidente siguiente) {
        this.siguiente = siguiente;
    }
}

