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

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String toString() {
        return super.toString() + " - Especialidad: " + especialidad;
    }
}
