package org.example.text.controller;

import org.example.text.model.Player;
import org.example.text.model.PlayerRepository;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class PlayerController {

    // Elementos de la vista FXML

    @FXML
    private Button saveButton;

    @FXML
    private Button loadButton;

    @FXML
    private TextField textfieldName;

    @FXML
    private TextField textfieldScore;

    @FXML
    private Label labelMessage;

    // Modelo encargado de guardar y cargar datos

    private final PlayerRepository repository =
            new PlayerRepository();


    // Se ejecuta al cargar el archivo FXML

    @FXML
    private void initialize() {

        saveButton.setOnAction(event -> savePlayer());

        loadButton.setOnAction(event -> loadPlayer());
    }


    // Guardar los datos del jugador

    private void savePlayer() {

        String name = textfieldName.getText().trim();
        String scoreText = textfieldScore.getText().trim();

        // Validar nombre vacío
        if (name.isEmpty()) {

            labelMessage.setText(
                    "Ingrese el nombre del jugador."
            );

            return;
        }

        // Validar puntaje vacío
        if (scoreText.isEmpty()) {

            labelMessage.setText(
                    "Ingrese el puntaje."
            );

            return;
        }

        // Convertir el puntaje a entero
        int score;

        try {

            score = Integer.parseInt(scoreText);

        } catch (NumberFormatException e) {

            labelMessage.setText(
                    "El puntaje debe ser un número entero."
            );

            return;
        }

        // Validar puntaje negativo
        if (score < 0) {

            labelMessage.setText(
                    "El puntaje debe ser mayor o igual a cero."
            );

            return;
        }

        // Guardar en record.txt
        try {

            Player player = new Player(name, score);

            repository.save(player);

            labelMessage.setText(
                    "Registro guardado correctamente."
            );

        } catch (IOException | IllegalArgumentException e) {

            labelMessage.setText(
                    "Error al guardar: " + e.getMessage()
            );
        }
    }


    // Cargar los datos guardados

    private void loadPlayer() {

        try {

            Player player = repository.load();

            // Mostrar los datos en los TextField

            textfieldName.setText(
                    player.getName()
            );

            textfieldScore.setText(
                    String.valueOf(player.getScore())
            );

            labelMessage.setText(
                    "Registro cargado correctamente."
            );

        } catch (NoSuchFileException e) {

            labelMessage.setText(
                    "No existe record.txt. Guarde primero."
            );

        } catch (IOException e) {

            labelMessage.setText(
                    "Error al cargar: " + e.getMessage()
            );
        }
    }
}