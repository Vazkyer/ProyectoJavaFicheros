import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;


public class Main {
    private static final String ARCHIVO = "contactos.csv"; // Nombre del archivo CSV donde se guardan los contactos
    private static ArrayList<Persona> contactos = new ArrayList<>(); // ArrayList de la clase Persona
    
    // Punto de entrada del programa: carga los datos y muestra el menú principal
    public static void main(String[] args) {
        cargarContactos();
        mostrarMenuPrincipal();
    }
    // Mostrar el menu principal con JOptionPane
    private static void mostrarMenuPrincipal() {
        // Opciones del menú principal
        String[] opciones = {
                "Crear contacto",
                "Mostrar todos",
                "Buscar contacto",
                "Estadísticas",
                "Guardar y salir"
        };
        // Bucle para mostrar el menu
        while (true) {
            int seleccion = JOptionPane.showOptionDialog(
                    null,
                    "GESTIÓN DE ACADEMIA\nSeleccione una opción:",
                    "Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );
            // Switch para ejecutar cada funcion en funcion de la eleccion del usuario
            switch (seleccion) {
                // Opcion Crear contacto
                case 0: 
                    crearContacto(); 
                    break;
                // Opcion Mostrar los Contactos
                case 1: 
                    mostrarTodos(); 
                    break;
                 // Opcion Buscar los Conctos 
                case 2: 
                    buscarContacto(); 
                    break;
                // Opcion Mostrar estadisticas
                case 3: 
                    mostrarEstadisticas(); 
                    break;
                // Opcion para Guardar y salir, el cual hace un return para salir del bucle
                case 4: 
                    guardarYSalir(); 
                    return;
                // Opcion por defecto sale del bucle
                default: 
                    return;
            }
        }
    }
    // Funcion para crear un nuevo contacto he insertarlo en el archivo .csv
    private static void crearContacto() {
        // Array String de los dos tipos de contactos
        String[] tipos = {"Alumno", "Profesor"};
        // Menu JOptionPane para registrar entrada del usuario
        String tipo = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione tipo de contacto:",
                "Nuevo Contacto",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tipos,
                tipos[0]
        );

        // Terminar la funcion si no hay input del usuario
        if (tipo == null) 
            return;

        // Variables para recoger inputs del usuario
        JTextField nombreField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField detalleField = new JTextField();

        // Array de objetos para recoger el input del usuario
        Object[] campos = {
                "Nombre:", nombreField,
                "Email:", emailField,
                tipo.equals("Alumno") ? "Curso:" : "Especialidad:", detalleField
        };

        // Varibale int para ver la confirmacion del boton para crear el contacto
        int confirmacion = JOptionPane.showConfirmDialog(
                null,
                campos,
                "Datos del contacto",
                JOptionPane.OK_CANCEL_OPTION
        );

        // Una vez recogido el input del usuario y confirmado, lo metemos en su tipo de clase con un try para mostrar si ha tenido exito o no
        if (confirmacion == JOptionPane.OK_OPTION) {
            try {
                Persona nuevo = tipo.equals("Alumno") ?
                        new Alumno(
                                nombreField.getText().trim(),
                                emailField.getText().trim(),
                                detalleField.getText().trim()
                        ) :
                        new Profesor(
                                nombreField.getText().trim(),
                                emailField.getText().trim(),
                                detalleField.getText().trim()
                        );

                contactos.add(nuevo);
                JOptionPane.showMessageDialog(null, "¡Contacto creado con éxito!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: Datos inválidos");
            }
        }
    }
    // Funcion para mostrar todos los contactos
    private static void mostrarTodos() {
        // Dar mensaje de no existencia de contactos si ese es el caso
        if (contactos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay contactos registrados");
            return;
        }

        // Crear un área de texto para mostrar la lista de contactos
        JTextArea areaTexto = new JTextArea(15, 40);
        areaTexto.setEditable(false); // No permitir la edicin del texto en dicha area
        contactos.forEach(p -> areaTexto.append(p + "\n")); // Agregar cada contacto al área de texto haciendo ForEach

        // Mostrar ventana con el area de texto y el mensaje de Listado completo
        JOptionPane.showMessageDialog(
                null,
                new JScrollPane(areaTexto),
                "Listado completo",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    // Funcion para buscar contectos dado el nombre
    private static void buscarContacto() {
        // Mostrar ventana para recibir el nombre de usuario por el cual buscar mediante el input del usuario
        String nombre = JOptionPane.showInputDialog("Introduzca nombre a buscar:");

        // Si el nombre el null o vacio termina la funcion
        if (nombre == null || nombre.isBlank()) 
            return;
        // Creamos una lista llamada resultados con el contenido de los contactos los 
        // cuales contienen el nombre introduciodo, convirtiendo tanto el guardado como
        // el buscado en minusculas apra que no haya problemas de formato
        List<Persona> resultados = contactos.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();

        // Si la lista de los resultados esta vacia se le muesta al usuario que no existe ese contacto
        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
            return;
        }

        // Creamos un area de texto 
        JTextArea areaTexto = new JTextArea(10, 30);
        areaTexto.setEditable(false); // No permitir la edicin del texto en dicha area
        // Imprimir en el area de texto cada contacto  dentro del resultado en una linea nueva haciendo For Each
        resultados.forEach(p -> areaTexto.append(p + "\n")); 

        // Mostramos el area de texto y el mensaje Resultados de busqueda con todas las conicidencias
        JOptionPane.showMessageDialog(
                null,
                new JScrollPane(areaTexto),
                "Resultados de búsqueda",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Funcion apra mostrar estadisticas de la base de datos
    private static void mostrarEstadisticas() {
        // Creamos una variable long contando el numeor de alumnos
        long alumnos = contactos.stream().filter(p -> p instanceof Alumno).count();
        // Creamos otra varibale long con el numero de profesores restandole el de alumnos
        long profesores = contactos.size() - alumnos;

        // Creamos un string con las estadisticas de numero de alumnos y profesores, 
        // tambien en funcion de el numero nos muestra si hay mas alumnos profesores o el mismo numero
        String mensaje = String.format(
                "Estadísticas:\n- Alumnos: %d\n- Profesores: %d\n\n%s",
                alumnos,
                profesores,
                alumnos > profesores ? "Hay más alumnos" :
                        profesores > alumnos ? "Hay más profesores" : "Misma cantidad"
        );
        
        // Mostramos el mensaje con las estadisticas
        JOptionPane.showMessageDialog(
                null,
                mensaje,
                "Estadísticas",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Funcion para cargar contactos del archivo
    private static void cargarContactos() {
        // Hacemos un try para intentar leer el archivo .csv 
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea; // Creamos un string linea
            // Leer el archivo línea por línea
            while ((linea = br.readLine()) != null) {
                // Creamos un array datos que separe los datos por la coma
                String[] datos = linea.split(",");
                //  Comprobamos que el array tenga 4 datos
                if (datos.length != 4) 
                    continue;
                // Crear un objeto Alumno o Profesor según el tipo indicado
                Persona p = datos[0].equals("Alumno") ?
                        new Alumno(datos[1], datos[2], datos[3]) :
                        new Profesor(datos[1], datos[2], datos[3]);
                // Inserta la nueva persona al ArrayList
                contactos.add(p);
            }
        // Mostrar mensaje de error si no se pudieron cargar los contactos
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "No se encontraron contactos previos",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // Funcion para guardar el array list en el archivo .csv y salir del programa
    private static void guardarYSalir() {
        // Abrir el archivo para escritura usando PrintWriter para poder escribir mas facilmente
        try (PrintWriter pw = new PrintWriter(ARCHIVO)) {
            // Recorremos cada contacto del ArrayList
            contactos.forEach(p -> {
                // Utilizamos instanceof para sabar si es alumno o profesor
                if (p instanceof Alumno a) {
                    // Se inserta el alumno dividido por ,
                    pw.println(String.join(",",
                            "Alumno",
                            a.getNombre(),
                            a.getEmail(),
                            a.getCurso()
                    ));
                } else if (p instanceof Profesor pr) {
                    // Escribir los datos del alumno en formato CSV
                    pw.println(String.join(",",
                            "Profesor",
                            pr.getNombre(),
                            pr.getEmail(),
                            pr.getEspecialidad()
                    ));
                }
            });
            // Se muestra mensaje de existo al usuario si todo salio bien
            JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
        // Damos el mensaje de error al usuario si no ha salido bien el guardado
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
