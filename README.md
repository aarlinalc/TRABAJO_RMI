# RoomRMI: Distributed Hotel Booking System

RoomRMI es una implementación robusta de una arquitectura Cliente-Servidor diseñada para la asignatura de Sistemas Distribuidos. Permite la gestión remota del inventario hotelero, autenticación de usuarios y procesamiento de transacciones en tiempo real.

## ⚙️ Arquitectura y Características Técnicas

* **Middleware:** Comunicación remota mediante **Java RMI** (Remote Method Invocation) con despliegue de registro automático.
* **Control de Concurrencia:** Implementación estricta de exclusión mutua (`synchronized`) en el servidor para evitar condiciones de carrera durante transacciones simultáneas y lectura/escritura de ficheros.
* **Persistencia Segura:** Base de datos basada en archivos de texto planos, protegida contra la corrupción de datos en entornos multihilo.
* **Interfaz Monolítica:** Cliente de terminal CLI optimizado para un despliegue rápido y evaluación de la lógica de red pura, aislando la complejidad de las interfaces gráficas.

## 🚀 Despliegue Rápido

**1. Levantar el Servidor:**
`javac *.java`
`java Servidor 1099`

**2. Conectar un Cliente:**
`javac *.java`
`java Cliente localhost 1099`
