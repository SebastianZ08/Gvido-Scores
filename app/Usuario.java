package app;

public abstract class Usuario{
    protected int id;
    protected String nombre;
    protected String email;
    protected String contrasenya;

    public Usuario(){}

    public Usuario(int id, String nombre, String email, String contrasenya){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasenya = contrasenya;
    }

    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getContrasenya() {
        return contrasenya;
    }



    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }



    public abstract String toString();
}