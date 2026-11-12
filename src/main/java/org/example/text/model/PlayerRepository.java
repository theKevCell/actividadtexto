package org.example.text.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PlayerRepository {

    private final Path filePath;

    public PlayerRepository() {
        this(Path.of("record.txt"));
    }

    public PlayerRepository(Path filePath) {
        this.filePath = filePath;
    }

    // Guardar y reemplazar el registro anterior
    public void save(Player player) throws IOException {

        try (BufferedWriter writer = Files.newBufferedWriter(
                filePath,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING)) {

            writer.write(player.getName());
            writer.newLine();

            writer.write(String.valueOf(player.getScore()));
            writer.newLine();
        }
    }

    // Recuperar el registro almacenado
    public Player load() throws IOException {

        try (BufferedReader reader = Files.newBufferedReader(
                filePath,
                StandardCharsets.UTF_8)) {

            String name = reader.readLine();
            String scoreText = reader.readLine();

            if (name == null || name.isBlank()
                    || scoreText == null || scoreText.isBlank()) {

                throw new IOException(
                        "El archivo no contiene un registro completo."
                );
            }

            try {

                int score = Integer.parseInt(scoreText.trim());

                if (score < 0) {
                    throw new IOException(
                            "El puntaje almacenado es negativo."
                    );
                }

                return new Player(name.trim(), score);

            } catch (NumberFormatException e) {

                throw new IOException(
                        "El puntaje almacenado no es un entero válido.",
                        e
                );
            }
        }
    }
}