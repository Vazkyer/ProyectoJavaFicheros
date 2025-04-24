import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String ARCHIVO = "contactos.csv"; // Variable estatica nombre archivo .csv
    private static ArrayList<Persona> contactos = new ArrayList<>(); // ArrayList de la clase Persona
    // Funcion main del flujo del programa
    public static void main(String[] args) {
        cargarContactos();
        mostrarMenuPrincipal();
    }
    // Mostrar el menu principal con JOptionPane
    private static void mostrarMenuPrincipal() {
        // Array String de botones de opciones
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
                case 0: 
                    crearContacto(); 
                    break;
                case 1: 
                    mostrarTodos(); 
                    break;
                case 2: 
                    buscarContacto(); 
                    break;
                case 3: 
                    mostrarEstadisticas(); 
                    break;
                case 4: 
                    guardarYSalir(); 
                    return;
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

    private static void mostrarTodos() {
        if (contactos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay contactos registrados");
            return;
        }

        JTextArea areaTexto = new JTextArea(15, 40);
        areaTexto.setEditable(false);
        contactos.forEach(p -> areaTexto.append(p + "\n"));

        JOptionPane.showMessageDialog(
                null,
                new JScrollPane(areaTexto),
                "Listado completo",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private static void buscarContacto() {
        String nombre = JOptionPane.showInputDialog("Introduzca nombre a buscar:");
        if (nombre == null || nombre.isBlank()) return;

        List<Persona> resultados = contactos.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
            return;
        }

        JTextArea areaTexto = new JTextArea(10, 30);
        areaTexto.setEditable(false);
        resultados.forEach(p -> areaTexto.append(p + "\n"));

        JOptionPane.showMessageDialog(
                null,
                new JScrollPane(areaTexto),
                "Resultados de búsqueda",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private static void mostrarEstadisticas() {
        long alumnos = contactos.stream().filter(p -> p instanceof Alumno).count();
        long profesores = contactos.size() - alumnos;

        String mensaje = String.format(
                "Estadísticas:\n- Alumnos: %d\n- Profesores: %d\n\n%s",
                alumnos,
                profesores,
                alumnos > profesores ? "Hay más alumnos" :
                        profesores > alumnos ? "Hay más profesores" : "Misma cantidad"
        );

        JOptionPane.showMessageDialog(
                null,
                mensaje,
                "Estadísticas",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private static void cargarContactos() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length != 4) continue;

                Persona p = datos[0].equals("Alumno") ?
                        new Alumno(datos[1], datos[2], datos[3]) :
                        new Profesor(datos[1], datos[2], datos[3]);

                contactos.add(p);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "No se encontraron contactos previos",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private static void guardarYSalir() {
        try (PrintWriter pw = new PrintWriter(ARCHIVO)) {
            contactos.forEach(p -> {
                if (p instanceof Alumno a) {
                    pw.println(String.join(",",
                            "Alumno",
                            a.getNombre(),
                            a.getEmail(),
                            a.getCurso()
                    ));
                } else if (p instanceof Profesor pr) {
                    pw.println(String.join(",",
                            "Profesor",
                            pr.getNombre(),
                            pr.getEmail(),
                            pr.getEspecialidad()
                    ));
                }
            });
            JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
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
