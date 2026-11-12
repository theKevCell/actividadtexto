# Registro de Puntajes 🎮

**Actividad académica — Fundamentos de Programación Orientada a Eventos**  
**Tecnología principal:** JavaFX  
**Repositorio:** https://github.com/theKevCell/actividadtexto

## Descripción

Este proyecto es una aplicación de escritorio desarrollada en **Java y JavaFX** que permite **guardar y consultar el nombre y el puntaje de un jugador** mediante un archivo de texto llamado `record.txt`.

La aplicación almacena **un solo registro a la vez**: cada vez que se presiona **Guardar**, reemplaza los datos anteriores. Al presionar **Cargar**, recupera la información del archivo y la muestra en los campos de la interfaz, incluso después de cerrar y volver a abrir el programa.

El ejercicio busca aplicar la **persistencia de datos mediante archivos**, el **manejo de excepciones** y la organización del código con el patrón **Modelo–Vista–Controlador (MVC)**.

## Objetivos de aprendizaje

- Construir una interfaz gráfica con JavaFX y Scene Builder.
- Separar las responsabilidades del proyecto mediante MVC.
- Validar los datos ingresados por el usuario.
- Escribir y leer un archivo de texto con `BufferedWriter` y `BufferedReader`.
- Manejar errores de entrada y de acceso a archivos con excepciones.
- Comprobar que los datos persisten al cerrar y volver a abrir la aplicación.

## Funcionalidades

| Elemento | Función |
| --- | --- |
| **Nombre** | Permite ingresar el nombre del jugador. |
| **Puntaje** | Permite ingresar un número entero mayor o igual a cero. |
| **Guardar** | Valida los campos y guarda el registro, sobrescribiendo el anterior. |
| **Cargar** | Lee el archivo `record.txt` y muestra la información en los campos. |
| **Mensaje** | Informa si la operación se completó o si ocurrió un error. |

Se controlan situaciones como nombre o puntaje vacíos, valores no numéricos, puntajes negativos, archivos inexistentes y archivos con información incompleta o inválida.

## Tecnologías y herramientas

- **Java 17:** lenguaje y versión del JDK empleados en el proyecto.
- **JavaFX 21.0.6:** componentes de la interfaz gráfica.
- **FXML y Scene Builder:** diseño visual de la ventana.
- **CSS:** estilos de campos, botones y mensajes.
- **Maven:** gestión de dependencias y ejecución.
- **IntelliJ IDEA:** entorno de desarrollo.
- **Git y GitHub:** control de versiones y publicación del proyecto.

La interfaz también incluye recursos gráficos y tipografías dentro de la carpeta `resources`.

## Organización del proyecto (MVC)

```text
src/main/
├── java/
│   ├── module-info.java
│   └── org/example/text/
│       ├── Launcher.java
│       ├── MainApp.java
│       ├── controller/
│       │   └── PlayerController.java
│       └── model/
│           ├── Player.java
│           └── PlayerRepository.java
└── resources/org/example/text/
    ├── view/
    │   └── menu-view.fxml
    ├── styles/
    │   └── styles.css
    ├── images/
    └── fonts/
```

- **Modelo:** `Player.java` representa al jugador y `PlayerRepository.java` se encarga de guardar y recuperar su información.
- **Vista:** `menu-view.fxml` define los componentes gráficos diseñados con Scene Builder; `styles.css` establece su apariencia.
- **Controlador:** `PlayerController.java` recibe los eventos de los botones, valida las entradas y comunica la vista con el modelo.
- **Inicio de la aplicación:** `Launcher.java` y `MainApp.java` permiten iniciar JavaFX y cargar la vista FXML.

## ¿Cómo se guardan y cargan los datos?

Al pulsar **Guardar**, el controlador obtiene el nombre y el puntaje, comprueba que sean válidos y crea un objeto `Player`. Después, `PlayerRepository` escribe los datos utilizando `BufferedWriter`.

Por ejemplo, `record.txt` puede contener:

```text
Kevin
1500
```

La primera línea corresponde al **nombre** y la segunda al **puntaje**. Para reemplazar el registro anterior se utiliza `StandardOpenOption.TRUNCATE_EXISTING`.

Al pulsar **Cargar**, `PlayerRepository` utiliza `BufferedReader` para leer las dos líneas. El puntaje se convierte a un entero y la información se muestra nuevamente en los campos de texto.

Para manejar posibles errores se utilizan `try`, `catch` y **try-with-resources**, lo cual también permite cerrar adecuadamente los recursos utilizados para acceder al archivo.

> **Nota:** `record.txt` se maneja como una ruta relativa. Su ubicación depende del directorio de trabajo desde el que se ejecute la aplicación; cuando se inicia desde la raíz del proyecto, se guarda allí.

## Cómo ejecutar la aplicación

**Requisitos:** JDK 17, Maven y un entorno compatible con JavaFX (por ejemplo, IntelliJ IDEA).

1. Clonar o descargar el repositorio.
2. Abrir la carpeta del proyecto en IntelliJ IDEA.
3. Esperar a que Maven descargue las dependencias del archivo `pom.xml`.
4. Ejecutar la clase `Launcher.java` o utilizar, desde la raíz del proyecto:

```bash
mvn clean javafx:run
```

## Prueba de persistencia

Para comprobar el requisito principal de la actividad:

1. Escribir un nombre y un puntaje válido.
2. Presionar **Guardar** y revisar el mensaje mostrado.
3. Borrar el contenido de los campos y presionar **Cargar**.
4. Verificar que los datos reaparezcan.
5. Cerrar completamente la aplicación, volver a abrirla y presionar **Cargar**.
6. Ingresar otro jugador y guardar para comprobar que el registro anterior se reemplaza.

Estas pruebas permiten verificar tanto el almacenamiento como la recuperación de la información.

## Dudas que surgieron durante el desarrollo

Durante el ejercicio me planteé preguntas como las siguientes:

1. **¿Por qué se utiliza `BufferedWriter` para guardar y `BufferedReader` para consultar información?** Quería comprender la función de cada clase y por qué se necesitan operaciones distintas para leer y escribir.
2. **¿Cómo hago para reemplazar el registro anterior y no agregar otro debajo?** Fue importante entender el uso de `TRUNCATE_EXISTING`.
3. **¿Qué sucede si intento cargar un registro antes de guardar por primera vez?** Esto me llevó a revisar cómo tratar los archivos inexistentes.
4. **¿Cómo evito que la aplicación falle cuando el usuario escribe letras o números negativos en el puntaje?** Aquí resultaron relevantes la validación de entradas y las excepciones.
5. **¿Cómo se conectan los elementos creados en Scene Builder con los atributos de `PlayerController`?** Necesité comprender los identificadores `fx:id` y la anotación `@FXML`.
6. **¿Por qué JavaFX necesita permisos de acceso al paquete del controlador?** Al trabajar con `module-info.java` surgieron dudas sobre `opens` y el acceso mediante reflexión.
7. **¿En qué lugar se crea realmente `record.txt`?** Aprendí que una ruta relativa depende del directorio desde el cual se ejecuta el programa.
8. **¿Qué responsabilidad corresponde al controlador y cuál al modelo?** La separación MVC me hizo revisar qué partes debían manejar validaciones, eventos y persistencia.

Estas preguntas representan los temas que necesité explorar para comprender mejor la implementación. *(Puedo actualizar esta sección con nuevas dudas o dificultades encontradas durante las pruebas).* 

## Declaración de uso responsable de inteligencia artificial

Durante el desarrollo utilicé herramientas de inteligencia artificial **como apoyo para aprender, explorar alternativas y revisar aspectos específicos del proyecto**. Su uso se distribuyó de la siguiente manera:

- **ChatGPT:** me apoyó en la creación y conceptualización de recursos gráficos para la aplicación, como íconos, fondos y mockups de referencia. También lo utilicé para aclarar conceptos técnicos durante el proceso.
- **Gemini:** me brindó orientación sobre funciones nuevas que estaba incorporando y sobre el manejo de archivos de texto (`.txt`) para guardar y recuperar los registros.
- **Claude:** lo utilicé como apoyo en la revisión de las implementaciones y las correcciones finales del código.

Estas herramientas se emplearon para **orientar y complementar el proceso de desarrollo**, no como sustituto del aprendizaje. Soy responsable de revisar, comprender, integrar y comprobar el funcionamiento de los elementos incluidos en la entrega, y de identificar las dificultades que aún estén pendientes.

## Reflexión final

Esta actividad me permitió relacionar los conceptos de **programación orientada a eventos, POO, MVC, archivos y excepciones** dentro de una misma aplicación. Más allá de conseguir que los botones funcionen, el principal aprendizaje fue entender cómo se conectan la interfaz, el controlador y la persistencia, y cómo abordar los errores que aparecen durante la implementación.

---

**Autor:** [theKevCell](https://github.com/theKevCell)  
**Proyecto académico:** Registro de Puntajes con JavaFX.
