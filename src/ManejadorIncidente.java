public interface ManejadorIncidente {
    boolean atenderIncidente(Incidente incidente);
    void setSiguiente(ManejadorIncidente siguiente);
}
