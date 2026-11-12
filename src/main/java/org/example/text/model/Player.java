package org.example.text.model;

public class Player {

    private final String name;
    private final int score;

    public Player(String name, int score) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre es obligatorio."
            );
        }

        if (score < 0) {
            throw new IllegalArgumentException(
                    "El puntaje no puede ser negativo."
            );
        }

        this.name = name.trim();
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}