# Gestión de Contactos

Este programa es una aplicación Java que permite gestionar contactos de alumnos y profesores. 

## Funcionalidades

### Funcionalidades mínimas

- **Crear contactos**: Permite crear nuevos contactos como Alumno o Profesor.
- **Mostrar todos los contactos**
- **Guardar contactos en un fichero (.csv)**
- **Cargar contactos desde un fichero**

### Funcionalidades opcionales (para subir nota)

- **Buscar un contacto por nombre**
- **Contar cuántos alumnos y profesores hay**
- **Mostrar estadísticas**
- **Interfaz gráfica con JOptionPane**

## Requisitos técnicos

1. **Uso de herencia**:
   - La clase Persona es la clase base, de la cual heredan las clases Alumno y Profesor.
   
2. **Uso de listas (ArrayList)**:
   - Los contactos (alumnos y profesores) se almacenan en una lista (ArrayList<Persona>).
   
3. **Lectura y escritura en fichero**:
   - Los contactos se guardan en un archivo CSV, y al iniciar la aplicación, se cargan los datos desde el archivo.
   
4. **Menú interactivo**:
   - El programa tiene un menú interactivo implementado con la librería JOptionPane, donde el usuario puede seleccionar las opciones.

## Estructura del proyecto

### Clases

1. **Main**: Contiene el menú principal, crear, mostrar, buscar, y guardar los contactos.
2. **Persona**: Clase base para Alumno y Profesor, con atributos como nombre y email.
3. **Alumno**: Subclase de Persona que añade la variable curso.
4. **Profesor**: Subclase de Persona que añade la variable especialidad.

### Relación entre las clases

- **Persona**: Es la clase base que define los variables comunes y el método abstracto getTipo(). 
- **Alumno y Profesor**: Son clases que heredan de Persona y añaden variables específicss como curso y especialidad, respectivamente. Las dos implementan el método getTipo() para diferenciar el tipo de contacto.
- **Main**: Flujo principal del programa, gestionando los contactos utilizando las clases Alumno y Profesor.

### Diagrama de clases

Diagrama de las clases:

-- Insertar diagrama


### Estructura de archivos

El proyecto tiene los siguientes archivos `.java`:

- `Main.java`: Lógica principal de la aplicación y el menú interactivo.
- `Persona.java`: Define la clase base con variables comunes a todos los contactos.
- `Alumno.java`: Subclase de Persona para gestionar los alumnos.
- `Profesor.java`: Subclase de Persona para gestionar los profesores.

Además, el proyecto incluye este archivo:

- Un archivo de ejemplo `contactos.csv` con los datos de prueba.

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


## Creadores

- Félix Sácnhez y Alain Vázquez.

---

## Video

--Insertar video

## Archivos entregados

1. `Main.java`
2. `Persona.java`
3. `Alumno.java`
4. `Profesor.java`
5. `contactos.csv`
6. `README.md`
7. Enlace al video 

---
