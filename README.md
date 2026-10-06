# 🚦 Simulación Concurrente: Peaje de Autopista

<p align="center">
  <img src="https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Paradigma-Concurrencia_%7C_Multithreading-4B8BBE?style=for-the-badge" alt="Multithreading" />
  <img src="https://img.shields.io/badge/Sincronizaci%C3%B3n-Mutex_%7C_Synchronized-2E7D32?style=for-the-badge" alt="Synchronized" />
  <img src="https://img.shields.io/badge/Instituci%C3%B3n-USS_%F0%9F%87%A8%F0%9F%87%B1-1f4287?style=for-the-badge" alt="USS" />
</p>

Este proyecto simula el comportamiento de una **estación de peaje de autopista** donde múltiples vehículos (Motos, Autos y Camiones) transitan y realizan sus pagos de forma concurrente. El objetivo es modelar, simular y analizar conceptos del paradigma concurrente en Java utilizando la sintaxis básica y directa del curso.

---

## 1. Descripción del Sistema

La simulación consta de:

1. **Flujo de Tránsito:** Múltiples vehículos de diferentes tipos (motos, autos y camiones) que se ejecutan como hilos independientes de forma asíncrona, llegando a una caseta de peaje común.
2. **Caja Registradora:** Una caja común dentro de la caseta acumula el dinero de los peajes.
3. **Modos de Simulación:** El sistema ejecuta de forma secuencial dos fases:
   - **Fase Sin Sincronización:** Muestra cómo el acceso simultáneo de múltiples hilos de vehículos a la caja registradora genera una **Condición de Carrera**, perdiéndose transacciones financieras debido a la falta de exclusión mutua.
   - **Fase Con Sincronización:** Muestra la resolución del problema mediante exclusión mutua (`synchronized`), asegurando la integridad del dinero recolectado.

---

## 2. Estructura del Proyecto

El código está organizado de la siguiente manera:

```text
ProyectoPeaje/
 ├── src/
 │    ├── Main.java              
 │    ├── CasetaPeaje.java       
 │    ├── CajaRegistradora.java  
 │    ├── Vehiculo.java          
 │    ├── ReportePeaje.java      
 ├── evidencias/
 │    └── resultado_consola.txt  
 └── README.md                   
```

---

## 3. Clases Principales y Responsabilidades (Diseño POO)

El sistema cuenta con **5 clases propias** estructuradas bajo los principios de la Programación Orientada a Objetos:

1. **`Main`**: Configura las dos simulaciones (con y sin sincronización), lanza los hilos de los vehículos, espera a que terminen usando `join()` y llama al reporteador.
2. **`CasetaPeaje`**: Representa la caseta física. Es el punto común que asocia los hilos con la caja registradora.
3. **`CajaRegistradora`**: Encapsula la variable de saldo (`saldo`). Contiene la lógica crítica de actualización de dinero y el control de exclusión mutua.
4. **`Vehiculo`**: Extiende de `Thread`. Representa un hilo concurrente. Cada instancia tiene un nombre de hilo, tipo y tarifa asignada.
5. **`ReportePeaje`**: Clase encargada de generar y mostrar la conclusión comparativa final. Ocupa un rol de separación de responsabilidades y encapsula la lógica de reporte.

---

## 4. Concurrencia y Sincronización

### Hilos Utilizados

- **Hilos de Vehículos:** 8 hilos concurrentes activos simultáneamente en cada prueba (moto, auto, camión).

### Recursos Compartidos

El recurso compartido principal es la clase **`CasetaPeaje`** (y dentro de ella, la **`CajaRegistradora`**). Múltiples hilos modifican el mismo objeto en memoria al mismo tiempo.

### El Problema de la Condición de Carrera

Sin sincronización, la operación `saldo = saldoAnterior + tarifa` no es atómica. Múltiples hilos leen el mismo saldo antes de incrementarlo, sobrescribiéndose unos a otros. Al simular un retardo con `Thread.sleep(15)`, esto es evidente en la consola donde el total recaudado final es menor al esperado.

### Estrategia de Sincronización Aplicada

1. **Exclusión Mutua (`synchronized`):** Se sincronizan las secciones críticas de la `CajaRegistradora` mediante un bloque `synchronized (this)` para garantizar que solo un hilo a la vez pueda realizar el proceso de suma de dinero al saldo, asegurando consistencia.

---

## 👨‍💻 Autor
Desarrollado por **Sebastián Orellana** ([@sepoba18](https://github.com/sepoba18)) — Universidad San Sebastián.
