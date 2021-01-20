# TFG_GII_20.15
# Estudio e implementación de una herramienta LOW CODE para una aplicación web

# Resumen del proyecto 
Este proyecto se basa en la creación de una aplicación para la gestión de un negocio (PYME) de venta al público. 

El objetivo es que se pueda acceder a ella mediante web, sin la necesidad de que el usuario tenga que realizar una instalación en su terminal. 

Para el desarrollo de la aplicación se ha hecho un pequeño estudio para saber qué tecnología cumple con los requisitos. La escogida ha sido una herramienta low code conocida como OpenXava.

Durante el desarrollo se afrontará la implementación de la aplicación con los requisitos iniciales, así como nuevos requisitos o funcionalidades que vayan surgiendo durante la implementación.

# Aplicación Web

    wwww.formestallesgrans.com/formes

# Instalación en local
La aplicación está pensada para ser alojada en un servidor, actualmente esta alojada en mi PC a modo de servidor, pero para la realización de las pruebas pertinentes, se puede instalar de forma local.

Requisitos: solo se necesita tener instalado jdk (en qualquier versión igual o superior a la 11) de Java.

Pasos: 
1.- Descargar la carpeta de Tomcat del repositorio o de este link : https://drive.google.com/drive/folders/1b5WG22elE_Q3QjIo0zgqftPHDDnen9JV?usp=sharing

2.- Descomprimirlo en el directorio que se prefiera

3.- Abrir una nueva terminal (CMD), e ir al directorio donde se ha extraído el Tomcat, más concretamente en la carpeta lib.

4.- Una vez en la carpeta lib ejecutar la siguiente orden:

    java -classpath hsqldb.jar org.hsqldb.Server -database mi-database-db -port 1666 -silent true -trace false
    
5.-Abrir una nueva terminal (CMD), e ir al directorio donde se ha extraído el Tomcat, más concretamente en la carpeta bin.

6.-Ejecutar la siguiente orden: (importante, por defecto (C:\Program Files\Java\jdk-11.0.8) aparecera entre comillas, hay que quitarlas)
    
    set JAVA_HOME=C:\Program Files\Java\jdk-11.0.8

7.-Ahora ya se puede iniciar la aplicación con el comando:
    
    startup

8.-Dirigirse al navegador que se prefiera e introducir:

    localhost:8080/formes
    
9.-Al acabar de realizar las pruebas hay que matar a los procesos de las dos terminales.

    
