package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static Connection connection = null;

    // Constructor privado para evitar instanciación (sacado de los apuntes)
    private DatabaseConnection() {
    }

    // Bloque estático para inicializar la conexión (sacado de los apuntes)
    static {
        String url = "jdbc:mysql://localhost:3306/tienda_partituras";
        String user = "root";
        String password = "1234";
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Se ejecuta la primera vez que se inicia la aplicación, esto para crear las tablas vacías
     * y listas para cargar los datos con el archivo csv
     * en el panel de admin
     */
    public static void initDatabase() {

        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "1234";

        try (Connection con = DriverManager.getConnection(url, user, password);
                Statement st = con.createStatement()) {

            st.executeUpdate("DROP DATABASE IF EXISTS tienda_partituras");
            st.executeUpdate("CREATE DATABASE tienda_partituras");
            st.executeUpdate("USE tienda_partituras");

            st.executeUpdate("""
                        CREATE TABLE usuarios (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            nombre VARCHAR(50),
                            email VARCHAR(100) UNIQUE,
                            contrasenya VARCHAR(100),
                            tipo ENUM('admin', 'cliente')
                        )
                    """);

            st.executeUpdate("""
                        CREATE TABLE partituras (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            titulo VARCHAR(100),
                            compositor VARCHAR(100),
                            tipo ENUM('clasica','moderna'),
                            precio DECIMAL(6,2)
                        )
                    """);

            st.executeUpdate("""
                        CREATE TABLE pedidos (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            usuario_id INT,
                            fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
                            total DECIMAL(8,2),
                            FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
                        )
                    """);

            st.executeUpdate("""
                        CREATE TABLE detalle_pedido (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            pedido_id INT,
                            partitura_id INT,
                            cantidad INT DEFAULT 1,
                            FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
                            FOREIGN KEY (partitura_id) REFERENCES partituras(id)
                        )
                    """);

            st.executeUpdate("""
                        INSERT INTO usuarios (nombre, email, contrasenya, tipo)
                        VALUES ('admin', 'admin@admin.com', 'admin123', 'admin')
                    """);

             //*Hola Ginés, este archivo lo estoy usando para controlar que este método solo se ejecute
             // la primera vez que se abre la app, porque no tiene sentido que cada vez siga borrando los
             // datos, así que mediante el config.txt hago control en Gestor.java
             //  */
            File archivo = new File("config.txt");
            archivo.createNewFile();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para obtener la conexión
    public static Connection getConnection() {
        return connection;
    }

    // Método para cerrar la conexión
    public static void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}