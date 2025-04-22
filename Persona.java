public abstract class Persona {
    protected String nombre;
    protected String email;

    public Persona(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public abstract String getTipo();

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return getTipo() + ": " + nombre + " - " + email;
    }
}