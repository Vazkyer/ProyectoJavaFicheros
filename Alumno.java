public class Alumno extends Persona {
    private String curso;

    public Alumno(String nombre, String email, String curso) {
        super(nombre, email);
        this.curso = curso;
    }

    @Override
    public String getTipo() {
        return "Alumno";
    }

    // Funcion obtener el curso
    public String getCurso() {
        return curso;
    }

    // Agregar a el toString el curso
    @Override
    public String toString() {
        return super.toString() + " - Curso: " + curso;
    }
}
