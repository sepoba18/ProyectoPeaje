public class CajaRegistradora {
    private int saldo = 0;

    public void registrarPago(int tarifa, String nombreVehiculo) {
        int saldoActual = this.saldo;
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.saldo = saldoActual + tarifa;
        System.out.println("   " + nombreVehiculo + " pagó $" + tarifa 
                           + " (Saldo previo: $" + saldoActual + " -> Saldo Nuevo: $" + this.saldo + ")");
    }

    public int getSaldo() {
        return this.saldo;
    }
}
