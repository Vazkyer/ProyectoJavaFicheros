public class Profesor extends Persona {
    private String especialidad;

    public Profesor(String nombre, String email, String especialidad) {
        super(nombre, email);
        this.especialidad = especialidad;
    }

    @Override
    public String getTipo() {
        return "Profesor";
    }

    // Funcion obtener el especialidad
    public String getEspecialidad() {
        return especialidad;
    }

    // Agregar a el toString la especialidad
    @Override
    public String toString() {
        return super.toString() + " - Especialidad: " + especialidad;
    }
}
