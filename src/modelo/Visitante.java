package modelo;

public class Visitante {
    private String nombre;
    private String id;
    private String email;

    public Visitante(String nombre, String id, String email) {
        this.nombre = nombre;
        this.id = id;
        this.email = email;
    }

    public String getNombre() { return nombre; }
    public String getId() { return id; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Visitante: " + nombre + " (ID: " + id + "), Email: " + email;
    }
}
