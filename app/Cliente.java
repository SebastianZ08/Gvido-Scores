package app;

public class Cliente extends Usuario{

    public Cliente(){
        
    }

    public Cliente(int id, String nombre, String email, String contrasenya){
        super(id, nombre, email, contrasenya);
    }

    public String toString(){
        return "\n . ID: " + this.getId() + "\n . Nombre: " + this.getNombre() + "\n . Eamil: " + this.getEmail();
    }
}