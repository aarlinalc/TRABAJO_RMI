#!/bin/bash

# Verificar si la carpeta Resenas esta creada
if [[ -d "Resenas" ]]; then
    echo "La carpeta 'Resenas' ya existe."
else
    # Si la carpeta no existe, crearla
    mkdir "Resenas"
    echo "Se ha creado la carpeta 'Resenas'."
fi

# Verificar si la carpeta Logs esta creada
if [[ -d "Logs" ]]; then
    echo "La carpeta 'Logs' ya existe."
else
    # Si la carpeta no existe, crearla
    mkdir "Logs"
    echo "Se ha creado la carpeta 'Logs'."
fi

touch Logs/compilacion_servidor.log
touch Logs/compilacion_cliente.log

javac -nowarn -Xlint:-path *.java 2>Logs/compilacion_servidor.log
cp Item.class ../Cliente
cp ServicioResena.class ../Cliente
cp ServicioInsertarBuscar.class ../Cliente
cp ServicioLogear.class ../Cliente
cp Usuario.class ../Cliente
cd ../Cliente
javac -nowarn -Xlint:-path *.java 2>../Servidor/Logs/compilacion_cliente.log
cd ../Servidor

# Verificar si el registro RMI está activo en el puerto 54321
if lsof -Pi :54321 -sTCP:LISTEN -t >/dev/null; then
    echo "El registro RMI ya está activo en el puerto 54321."
else
    # Crear el registro RMI en el puerto 54321
    rmiregistry 54321 &
    echo "Se ha creado el registro RMI en el puerto 54321."
fi


echo "Ejecución en marcha . . . "

java -Djava.security.policy=servidor.permisos Servidor 54321 2>Logs/ejecucion_servidor.log 


