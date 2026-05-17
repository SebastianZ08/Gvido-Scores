package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.Admin;
import app.Cliente;
import app.Usuario;
import util.DatabaseConnection;

public class LoginAccessDB {
    private static Connection con = DatabaseConnection.getConnection();

    public Usuario login(String email, String password) throws SQLException {

        String sql = "SELECT * FROM usuarios WHERE email = ? AND contrasenya = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String tipo = rs.getString("tipo");

            Usuario user = tipo.equals("admin") ? new Admin() : new Cliente();

            user.setId(rs.getInt("id"));
            user.setNombre(rs.getString("nombre"));
            user.setEmail(rs.getString("email"));

            return user;
        }

        return null;
    }

    public List<Usuario> getUsuarios() throws SQLException {

        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                String tipo = resultSet.getString("tipo");

                Usuario user;

                if (tipo.equals("admin")) {
                    user = new Admin();
                } else {
                    user = new Cliente();
                }

                user.setId(resultSet.getInt("id"));
                user.setNombre(resultSet.getString("nombre"));
                user.setEmail(resultSet.getString("email"));
                user.setContrasenya(resultSet.getString("contrasenya"));

                usuarios.add(user);
            }
        }

        return usuarios;
    }

    public boolean existeEmail(String email) throws SQLException {

        String sql = "SELECT * FROM usuarios WHERE email = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();

        return rs.next();
    }

    public void registrar(String nombre, String email, String password) throws SQLException {

        String sql = "INSERT INTO usuarios (nombre, email, contrasenya, tipo) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nombre); 
        ps.setString(2, email);
        ps.setString(3, password);
        ps.setString(4, "cliente");

        ps.executeUpdate();
    }
}
