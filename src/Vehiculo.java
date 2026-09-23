public class Vehiculo extends Thread {
    private final String tipo;
    private final int tarifa;
    private final Pista pista;
    private final CasetaPeaje caseta;

    public Vehiculo(String nombre, String tipo, int tarifa, Pista pista, CasetaPeaje caseta) {
        super(nombre);
        this.tipo = tipo;
        this.tarifa = tarifa;
        this.pista = pista;
        this.caseta = caseta;
    }

    @Override
    public void run() {
        try {
            System.out.println(getName() + " circulando por Pista " + pista.getNumero() + "...");
            Thread.sleep((int) (Math.random() * 80));
        } catch (InterruptedException e) {
            System.out.println(getName() + " fue interrumpido.");
            Thread.currentThread().interrupt();
        }

        caseta.pagarPeaje(this);
    }

    public String getTipo() {
        return tipo;
    }

    public int getTarifa() {
        return tarifa;
    }

    public Pista getPista() {
        return pista;
    }
}
