package org.example;

public abstract class Creature extends Entity {
    public int speed;
    public int health;

    public Creature(FigureType figureType, Coordinate coordinate) {
        super(figureType, coordinate);
    }

    @Override
    public String toString() {
        return figureType.toString() + " " + coordinate.width + " " + coordinate.height + " " + speed + " " + health;
    }
}
