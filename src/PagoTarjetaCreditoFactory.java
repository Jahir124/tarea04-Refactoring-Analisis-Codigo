public class PagoTarjetaCreditoFactory implements IPaymentProcessor {

    @Override
    public Pago crearPago() {
        return new PagoTarjetaCredito();
    }
}

class PagoTarjetaCredito implements Pago {

    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Procesando pago con tarjeta de crédito por: " + monto);
        return true;
    }
}
