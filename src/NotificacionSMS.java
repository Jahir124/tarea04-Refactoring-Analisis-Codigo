public class NotificacionSMS extends Notificacion {

    public NotificacionSMS(String destinatario, String mensaje) {
        super(destinatario, mensaje);
    }

    @Override
    public boolean enviar() {
        System.out.println("Enviando SMS a: " + destinatario + " con mensaje: " + mensaje);
        return true;
    }
}
