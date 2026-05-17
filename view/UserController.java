package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnection;
import java.io.FileWriter;

public class UserController {

    @FXML
    private TextField busquedaField;
    @FXML
    private ListView<String> listaResultados;
    @FXML
    private ListView<String> listaCarrito;
    @FXML
    private Label mensajeLabel;

    private Connection con = DatabaseConnection.getConnection();
    private List<String> carrito = new ArrayList<>();

    @FXML
    private void handleBuscar() {
        listaResultados.getItems().clear();

        try {
            String sql = "SELECT * FROM partituras WHERE titulo LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + busquedaField.getText() + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listaResultados.getItems().add(
                        rs.getInt("id") + " - " +
                                rs.getString("titulo") + " (" +
                                rs.getDouble("precio") + "€)");
            }

        } catch (Exception e) {
            mensajeLabel.setText("Error al buscar");
        }
    }

    @FXML
    private void handleAddCarrito() {
        String selected = listaResultados.getSelectionModel().getSelectedItem();

        if (selected != null) {
            carrito.add(selected);
            listaCarrito.getItems().add(selected);
        }
    }

    @FXML
    private void handleComprar() {

        try {
            FileWriter writer = new FileWriter("compra.txt");

            double total = 0;

            writer.write("===== COMPRA GVIDOSCORES =====\n\n");
            for (String item : carrito) {
                writer.write(item + "\n");

                String precioStr = item.substring(item.lastIndexOf("(") + 1, item.lastIndexOf("€"));
                total += Double.parseDouble(precioStr);
            }

            writer.write("\nTOTAL: " + total + "€");

            writer.write("\nGracias por tu compra (apruébame Ginés)");

            writer.close();

            mensajeLabel.setText("Compra realizada (TXT generado)");
            carrito.clear();
            listaCarrito.getItems().clear();

        } catch (Exception e) {
            mensajeLabel.setText("Error al generar archivo");
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

            Stage stage = (Stage) listaCarrito.getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("GvidoScores App");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
