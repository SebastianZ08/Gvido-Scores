package app;

public class Partitura {
    private int id;
    private String titulo;
    private String compositor;
    private String tipo;
    private double precio;

    public Partitura() {

    }

    public Partitura(int id, String titulo, String compositor, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.compositor = compositor;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String toString() {
        return ". ID: " + this.getId() + "\n. Título: " + this.getTitulo() + "\n. Compositor: " + this.getCompositor()
                + "\n. Tipo: " + this.getTipo() + "\n. Precio: " + this.getPrecio();
    }
}