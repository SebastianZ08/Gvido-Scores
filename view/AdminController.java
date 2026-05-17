package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import util.DatabaseConnection;

public class AdminController {

    @FXML
    private TextField tituloField;
    @FXML
    private TextField compositorField;
    @FXML
    private ComboBox<String> tipoBox;
    @FXML
    private TextField precioField;
    @FXML
    private ListView<String> listaPartituras;
    @FXML
    private Label mensajeLabel;

    private Connection con = DatabaseConnection.getConnection();

    @FXML
    public void initialize() {
        tipoBox.getItems().addAll("clasica", "moderna");
        cargarPartituras();
    }

    private void cargarPartituras() {
        listaPartituras.getItems().clear();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM partituras");

            while (rs.next()) {
                listaPartituras.getItems().add(
                        rs.getInt("id") + " - " + rs.getString("titulo"));
            }

        } catch (Exception e) {
            mensajeLabel.setText("Error al cargar");
        }
    }

    @FXML
    private void handleAdd() {
        try {
            String sql = "INSERT INTO partituras (titulo, compositor, tipo, precio) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, tituloField.getText());
            ps.setString(2, compositorField.getText());
            ps.setString(3, tipoBox.getValue());
            ps.setDouble(4, Double.parseDouble(precioField.getText()));

            ps.executeUpdate();

            mensajeLabel.setText("Partitura añadida");
            cargarPartituras();

        } catch (Exception e) {
            mensajeLabel.setText("Error al añadir");
        }
    }

    @FXML
    private void handleUpdate() {
        try {
            String selected = listaPartituras.getSelectionModel().getSelectedItem();
            int id = Integer.parseInt(selected.split(" - ")[0]);

            String sql = "UPDATE partituras SET titulo=?, compositor=?, tipo=?, precio=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, tituloField.getText());
            ps.setString(2, compositorField.getText());
            ps.setString(3, tipoBox.getValue());
            ps.setDouble(4, Double.parseDouble(precioField.getText()));
            ps.setInt(5, id);

            ps.executeUpdate();

            mensajeLabel.setText("Actualizado");
            cargarPartituras();

        } catch (Exception e) {
            mensajeLabel.setText("Error al actualizar");
        }
    }

    @FXML
    private void handleDelete() {
        try {
            String selected = listaPartituras.getSelectionModel().getSelectedItem();
            int id = Integer.parseInt(selected.split(" - ")[0]);

            String sql = "DELETE FROM partituras WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

            mensajeLabel.setText("Eliminado");
            cargarPartituras();

        } catch (Exception e) {
            mensajeLabel.setText("Error al eliminar");
        }
    }

    @FXML
    private void handleCSV() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("partituras.csv"));
            String linea;

            String sql = "INSERT INTO partituras (titulo, compositor, tipo, precio) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                ps.setString(1, datos[0]);
                ps.setString(2, datos[1]);
                ps.setString(3, datos[2]);
                ps.setDouble(4, Double.parseDouble(datos[3]));

                ps.executeUpdate();
            }

            br.close();

            mensajeLabel.setText("CSV cargado correctamente");
            cargarPartituras();

        } catch (Exception e) {
            mensajeLabel.setText("Error al cargar CSV");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/LoginView.fxml"));

            Scene scene = new Scene(loader.load());

            scene.getStylesheets().add(
                    getClass().getResource("/view/estilos.css").toExternalForm());

            Stage stage = (Stage) listaPartituras.getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("GvidoScores App");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
