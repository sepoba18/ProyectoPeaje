public class CasetaPeaje {
    private final String nombre;
    private final CajaRegistradora caja;
    private final boolean usarSincronizacion;

    public CasetaPeaje(String nombre, CajaRegistradora caja, boolean usarSincronizacion) {
        this.nombre = nombre;
        this.caja = caja;
        this.usarSincronizacion = usarSincronizacion;
    }

    public void pagarPeaje(Vehiculo vehiculo) {
        System.out.println("[" + nombre + "] Atendiendo a " + vehiculo.getName() + " de la Pista " + vehiculo.getPista().getNumero());
        
        if (usarSincronizacion) {
            synchronized (caja) {
                caja.registrarPago(vehiculo.getTarifa(), vehiculo.getName());
            }
        } else {
            caja.registrarPago(vehiculo.getTarifa(), vehiculo.getName());
        }
    }
}
