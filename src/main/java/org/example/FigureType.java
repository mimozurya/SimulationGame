package org.example;

public enum FigureType {
    GRASS("\uD83C\uDF3F"),
    ROCK("\uD83D\uDDFB"),
    TREE("\uD83C\uDF33"),
    HERBIVORE("\uD83D\uDC04"),
    PREDATOR("\uD83D\uDE08"),
    EMPTY_CELL("\uD83D\uDFE9");

    private final String name;

    FigureType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
