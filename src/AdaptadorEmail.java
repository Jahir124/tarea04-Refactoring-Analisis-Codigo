public class AdaptadorEmail extends Notificacion {

    public AdaptadorEmail(String destinatario, String mensaje) {
        super(destinatario, mensaje);
    }

    @Override
    public boolean enviar() {
        System.out.println("Enviando email a: " + destinatario + " con mensaje: " + mensaje);
        return true;
    }
}
