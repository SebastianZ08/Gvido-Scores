package app;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.DatabaseConnection;

public class Gestor extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        File archivo = new File("config.txt");

        if (!archivo.exists()) {
            DatabaseConnection.initDatabase();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
            Scene scene = new Scene(loader.load());

            scene.getStylesheets().add(
                    getClass().getResource("/view/estilos.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("GvidoScores App");
            stage.setHeight(825);
            stage.setWidth(825);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}