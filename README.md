# Gestión de Contactos

Este programa es una aplicación Java que permite gestionar contactos de alumnos y profesores de una academia. Se ha desarrollado siguiendo los requisitos de un ejercicio de programación orientada a objetos y tiene un menú interactivo para la gestión de los contactos. 

## Funcionalidades

### Funcionalidades mínimas

- **Crear contactos**: Permite crear nuevos contactos como `Alumno` o `Profesor`.
- **Mostrar todos los contactos**: Muestra una lista con todos los contactos registrados.
- **Guardar contactos en un fichero (.csv)**: Los contactos se guardan en un archivo CSV para persistencia de datos.
- **Cargar contactos desde un fichero**: Al iniciar la aplicación, los contactos se cargan desde un archivo CSV si existe.

### Funcionalidades opcionales (para subir nota)

- **Buscar un contacto por nombre**: Permite buscar un contacto por su nombre, mostrando todos los resultados que coincidan.
- **Contar cuántos alumnos y profesores hay**: Muestra la cantidad total de alumnos y profesores registrados.
- **Mostrar estadísticas**: Muestra estadísticas simples, como si hay más alumnos que profesores o viceversa.
- **Interfaz gráfica con JOptionPane**: Utiliza la librería `JOptionPane` para mostrar cuadros de diálogo y hacer la interacción con el usuario más amigable.

## Requisitos técnicos

1. **Uso de herencia**:
   - La clase `Persona` es la clase base, de la cual heredan las clases `Alumno` y `Profesor`.
   
2. **Uso de listas (ArrayList)**:
   - Los contactos (alumnos y profesores) se almacenan en una lista (`ArrayList<Persona>`).
   
3. **Lectura y escritura en fichero**:
   - Los contactos se guardan en un archivo CSV, y al iniciar la aplicación, se cargan los datos desde dicho archivo.
   
4. **Menú interactivo**:
   - El programa tiene un menú interactivo implementado con la librería `JOptionPane`, donde el usuario puede seleccionar diversas opciones.

## Estructura del proyecto

### Clases

1. **Main**: Contiene el menú principal, la lógica para crear, mostrar, buscar, y guardar los contactos.
2. **Persona**: Clase base para `Alumno` y `Profesor`, con atributos comunes como `nombre` y `email`.
3. **Alumno**: Subclase de `Persona` que añade el atributo `curso`.
4. **Profesor**: Subclase de `Persona` que añade el atributo `especialidad`.

### Relación entre las clases

- **Persona**: Es la clase base que define los atributos comunes y el método abstracto `getTipo()`. 
- **Alumno y Profesor**: Son clases que heredan de `Persona` y añaden atributos específicos como `curso` y `especialidad`, respectivamente. Ambas implementan el método `getTipo()` para diferenciar el tipo de contacto.
- **Main**: Controla el flujo principal del programa, gestionando los contactos y utilizando las clases `Alumno` y `Profesor`.

### Diagrama de clases

Un diagrama simple de las clases es el siguiente:

--Insertar diagrama


### Estructura de archivos

El proyecto está compuesto por los siguientes archivos `.java`:

- `Main.java`: Contiene la lógica de la aplicación y el menú interactivo.
- `Persona.java`: Define la clase base con atributos comunes a todos los contactos.
- `Alumno.java`: Subclase de `Persona` para gestionar los alumnos.
- `Profesor.java`: Subclase de `Persona` para gestionar los profesores.

Además, el proyecto incluye:

- Un archivo de ejemplo `contactos.csv` que puede ser utilizado para cargar los datos al iniciar la aplicación.
- Un archivo `README.md` con la documentación.

## Instrucciones de uso

**Opciones del menú**:
   - **Crear contacto**: Permite agregar un nuevo contacto (alumno o profesor).
   - **Mostrar todos**: Muestra todos los contactos almacenados.
   - **Buscar contacto**: Permite buscar un contacto por su nombre.
   - **Estadísticas**: Muestra cuántos alumnos y profesores hay registrados.
   - **Guardar y salir**: Guarda todos los contactos en un archivo CSV y termina la ejecución del programa.

## Cómo está organizado el proyecto

1. **Main.java**:
   - Controla el menú interactivo, la creación, búsqueda, y visualización de contactos.
   - Carga y guarda los contactos desde y hacia un archivo CSV.
   
2. **Persona.java, Alumno.java, Profesor.java**:
   - Define la estructura y atributos de los contactos, utilizando la herencia.

3. **Ficheros**:
   - El archivo CSV contiene los datos de los contactos, que son cargados y guardados cada vez que se inicia o termina el programa.


## Créditos

- Este proyecto ha sido realizado por Félix Sácnhez y Alain Vázquez.

---

## Video Explicativo

--Insertar video

## Archivos entregados

1. `Main.java`
2. `Persona.java`
3. `Alumno.java`
4. `Profesor.java`
5. `contactos.csv`
6. `README.md`
7. [Enlace al video explicativo]

---
