package app;

public class Admin extends Usuario {

    public Admin() {

    }

    public Admin(int id, String nombre, String email, String contrasenya) {
        super(id, nombre, email, contrasenya);
    }

    public String toString() {
        return "\n. ID: " + this.getId() + "\n. Nombre: " + this.getNombre() + "\n. Eamil: " + this.getEmail()
                + "\n . Contraseña: " + this.getContrasenya();
    }
}