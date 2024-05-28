# Gestión de premios MercadoUQ - MUQ

Este proyecto se centra en la automatización de la generación y envío de premios a los clientes de la plataforma de comercio electrónico MUQ a nivel mundial en el año 2024, con el objetivo de establecer lazos de fidelización.

## Descripción

Se requiere automatizar el análisis de facturas, la generación de premios, el enturnado de envíos, la generación de paquetes de carga y la planificación de rutas aéreas de envío. De esta manera, se optimizará el proceso aprovechando el recurso tecnológico y liberando el recurso humano para otras tareas.
## Librerías Utilizadas

Este proyecto utiliza las siguientes librerías:

- **JGrapht**
- **JGraphX**
- **Apache POI**
- **JavaFX**

## Dependencias de Maven

Para utilizar estas librerías, asegúrate de incluir las siguientes dependencias en tu archivo `pom.xml`:

<dependencies>
    <dependency>
        <groupId>org.controlsfx</groupId>
        <artifactId>controlsfx</artifactId>
        <version>11.1.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.3</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>21</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>${junit.version}</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>${junit.version}</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.jgrapht</groupId>
        <artifactId>jgrapht-core</artifactId>
        <version>1.0.1</version>
    </dependency>
    <dependency>
        <groupId>org.jgrapht</groupId>
        <artifactId>jgrapht-ext</artifactId>
        <version>1.0.1</version>
    </dependency>
</dependencies>

## Instrucciones de uso

1. Clonar el repositorio.
2. Instalar las dependencias necesarias.
3. Ejecutar la aplicación según las instrucciones proporcionadas en el código fuente.

## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir al proyecto, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama para tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`).
3. Haz tus cambios y confirma (`git commit -am 'Agrega nueva funcionalidad'`).
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`).
5. Crea un nuevo pull request.

## Autor
Juan Camilo Cuenca, Brandon Montealegre, Santiago Arbelaez, Diego alexander jimenez.

