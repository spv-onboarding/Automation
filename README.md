# Automation Supervielle Project #

Este proyecto permite la automatización para dar soporte a pruebas de UI y servicios.
El mismo implementa buenas prácticas de automation tales como:

* Patrón de PageObject
* Cucumber para el uso de Gherkins
* Tags de casos de prueba
* Selenium

## Caracteristicas del framework ##
 
 **Soporte para build y deployment**
 
 * Apache Maven.
 
 * [Cucumber] (https://cucumber.io/)
     * Definición de escenarios.
     
 * [Selenium] (https://www.seleniumhq.org/)
     * Soporte para automatización web.
         
 * Para la creación de páginas:
 [PageObject](https://code.google.com/p/selenium/wiki/PageObjects)
 [PageFactory](https://code.google.com/p/selenium/wiki/PageFactory) 
 
 * Generación de reporte de las ejecuciones.
     
## Cómo instalar el framework ##

Para instalar el framework es necesario contar con ciertos prerequisitos instalados en nuestro ambiente de trabajo:

* Git
* Maven
* Java
* IDE


### Instalacion Git ###

Para instalar desde Windows (https://git-scm.com/download/win)

### Instalacion JDK ###

Descargar e intstalar (https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

Para validar que JDK se haya instalado ingresamos en la consola el siguiente comando:

java -version

### Instalacion Maven ###
 
Descargar Maven (http://maven.apache.org/download.cgi) y extraerlos en la carpeta seleccionada.

* La variable de entorno JAVA_HOME debe estar seteada en el path del sistema y apuntar a la instalacion de nuestro SDK.
* Agregamos el directorio bin de maven a la variable de entorno path.
* Confirmamos que quedo bien instalado ejecutando el comando mvn -v desde consola.

### Configurar el proyecto por primera vez ###

Para configurar el proyecto por primera vez debemos navegar al workspace donde queremos tenerlo, y una vez parados ahi, desde una consola, ejecutar Git para traerlo del repositorio:

~~~
git clone url_proyecto
~~~

Una vez clonado el proyecto debemos instalarlo para que maven descargue todas las dependencias que tenemos definidas en el pom.xml, para ello ejecutamos:

~~~
mvn install
~~~

### Instalación Cucumber en Eclipse ###

En el caso que se utilice Eclipse como IDE se necesitará instalar un plugin desde el Marketplace;

https://marketplace.eclipse.org/content/cucumber-eclipse-plugin


### Instalación JDBC Driver ###

Para acceder a la Base de Datos:

* Descargar desde: https://www.microsoft.com/en-us/download/details.aspx?id=11774 
* Extraer el archivo .tar.gz ir a sqljdbc_version\fra\auth\x86 o \x64 
* Copiar sqljdbc_auth.dll a C:\Program Files\Java\jre_Version\bin 
# Automation
