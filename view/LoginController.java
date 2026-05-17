package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mysql.LoginAccessDB;
import util.Libreria;
import app.Admin;
import app.Usuario;
import app.Autenticable;

public class LoginController implements Autenticable {

    @FXML
    private TextField emailField;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label mensajeLabel;

    private LoginAccessDB dao = new LoginAccessDB();

    @FXML
    public void handleLogin() {

        String email = emailField.getText();
        String pass = passwordField.getText();

        try {
            Usuario user = dao.login(email, pass);

            if (user != null) {

                Stage stage = (Stage) emailField.getScene().getWindow();
                FXMLLoader loader;
                Scene scene;

                if (user instanceof Admin) {

                    loader = new FXMLLoader(getClass().getResource("/view/AdminView.fxml"));
                    scene = new Scene(loader.load());

                    scene.getStylesheets().add(
                            getClass().getResource("/view/estilos2.css").toExternalForm());

                    stage.setTitle("Panel Admin");

                } else {

                    loader = new FXMLLoader(getClass().getResource("/view/UserView.fxml"));
                    scene = new Scene(loader.load());

                    scene.getStylesheets().add(
                            getClass().getResource("/view/estilos3.css").toExternalForm());

                    stage.setTitle("Cliente - GvidoScores");
                }

                stage.setScene(scene);

            } else {
                mensajeLabel.setText("Credenciales incorrectas");
            }

        } catch (Exception e) {
            mensajeLabel.setText("Error BD");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRegister() {

        String email = emailField.getText();
        String pass = passwordField.getText();
        String nombre = nameField.getText();

        boolean valido = true;
        String mensaje = "";

        if (!Libreria.comprobarEmail(email)) {
            valido = false;
            mensaje = "Email no válido";
        }

        if (email.isEmpty() && pass.isEmpty() && nombre.isEmpty()) {
            mensaje = "Ombe pero cómo te vas a registrar sin datos. Respeta";
            valido = false;

        } else if (email.isEmpty()) {
            mensaje = "Falta introducir un email";
            valido = false;

        } else if (pass.isEmpty()) {
            mensaje = "Falta definir una contraseña";
            valido = false;
        } else if (nombre.isEmpty()) {
            mensaje = "Falta añadir el nombre";
        }

        try {

            if (valido) {
                if (dao.existeEmail(email)) {
                    valido = false;
                    mensaje = "Email ya registrado";
                }
            }

            if (valido) {
                dao.registrar(nombre, email, pass);
                mensaje = "Registro exitoso";
            }

        } catch (Exception e) {
            mensaje = "Error en la BD";
            e.printStackTrace();
        }

        mensajeLabel.setText(mensaje);
    }

}