package org.example.text;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        try (
                InputStream fuente = MainApp.class.getResourceAsStream(
                        "/org/example/text/fonts/Jaro-Regular-VariableFont_opsz.ttf"
                );

                InputStream fuenteT = MainApp.class.getResourceAsStream(
                        "/org/example/text/fonts/ProtestStrike-Regular.ttf"
                )
        ) {
            if (fuente == null || fuenteT == null) {
                throw new IOException("No se encontraron las fuentes.");
            }

            Font jaro = Font.loadFont(fuente, 18);
            Font protest = Font.loadFont(fuenteT, 18);

            if (jaro == null || protest == null) {
                throw new IOException("No se pudieron cargar las fuentes.");
            }
        }

        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/org/example/text/view/menu-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        stage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/org/example/text/images/logoiconogame.png")));
        stage.setTitle("Registro de Puntajes");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }
}
