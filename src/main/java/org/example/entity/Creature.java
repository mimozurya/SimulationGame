package org.example;

public abstract class Creature extends Entity {
    int speed;
    int health;

    public Creature(Coordinate coordinate, FigureType figureType, int speed, int health) {
        super(coordinate, figureType);
        this.speed = speed;
        this.health = health;
    }

    protected void makeMove () {}
}
