public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("==========================================================");
        System.out.println("   SIMULACIÓN CONCURRENTE: PEAJE DE AUTOPISTA            ");
        System.out.println("   (8 Pistas a 1 Caseta para Todos)                      ");
        System.out.println("==========================================================\n");

        correrPrueba(false);
        correrPrueba(true);
    }

    private static void correrPrueba(boolean usarSincronizacion) throws InterruptedException {
        String modo = usarSincronizacion ? "CON SINCRONIZACIÓN" : "SIN SINCRONIZACIÓN";
        System.out.println("----------------------------------------------------------");
        System.out.println(">>> INICIANDO PRUEBA: " + modo);
        System.out.println("----------------------------------------------------------");

        CajaRegistradora caja = new CajaRegistradora();
        CasetaPeaje caseta = new CasetaPeaje("Caseta Central 01", caja, usarSincronizacion);

        Pista[] pistas = new Pista[8];
        for (int i = 0; i < 8; i++) {
            pistas[i] = new Pista(i + 1);
        }

        Vehiculo[] vehiculos = new Vehiculo[8];
        vehiculos[0] = new Vehiculo("Moto-1", "Moto", 500, pistas[0], caseta);
        vehiculos[1] = new Vehiculo("Auto-2", "Auto", 1000, pistas[1], caseta);
        vehiculos[2] = new Vehiculo("Camion-3", "Camion", 3000, pistas[2], caseta);
        vehiculos[3] = new Vehiculo("Auto-4", "Auto", 1000, pistas[3], caseta);
        vehiculos[4] = new Vehiculo("Moto-5", "Moto", 500, pistas[4], caseta);
        vehiculos[5] = new Vehiculo("Camion-6", "Camion", 3000, pistas[5], caseta);
        vehiculos[6] = new Vehiculo("Auto-7", "Auto", 1000, pistas[6], caseta);
        vehiculos[7] = new Vehiculo("Camion-8", "Camion", 3000, pistas[7], caseta);

        int totalEsperado = 0;
        for (Vehiculo v : vehiculos) {
            totalEsperado += v.getTarifa();
        }

        for (Vehiculo v : vehiculos) {
            v.start();
        }

        for (Vehiculo v : vehiculos) {
            v.join();
        }

        System.out.println("\n----------------------------------------------------------");
        System.out.println(">>> CONCLUSIÓN DE LA PRUEBA (" + modo + ")");
        System.out.println("Vehículos procesados: " + vehiculos.length);
        System.out.println("Monto total esperado: $" + totalEsperado);
        System.out.println("Monto total registrado en caja: $" + caja.getSaldo());
        if (caja.getSaldo() == totalEsperado) {
            System.out.println("Resultado: FUNCIONAMIENTO CORRECTO (No se perdió dinero).");
        } else {
            System.out.println("Resultado: ERROR - CONDICIÓN DE CARRERA DETECTADA (Se perdió dinero).");
        }
        System.out.println("----------------------------------------------------------\n");
    }
}
