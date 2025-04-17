package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Creature extends Entity {
    public int speed;
    public int health;

    public Creature(FigureType figureType, Coordinate coordinate) {
        super(figureType, coordinate);
    }

    protected abstract void makeMove(Map map);

    @Override
    public String toString() {
        return figureType.toString() + " " + coordinate.width + " " + coordinate.height + " " + speed + " " + health;
    }
}
