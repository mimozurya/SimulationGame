package org.example;

import java.util.HashSet;
import java.util.Set;

public abstract class Creature extends Entity {
    public int speed;
    public int health;

    public Creature(FigureType figureType, Coordinate coordinate) {
        super(figureType, coordinate);
    }

    protected void makeMove(Map map, FigureType figureTypeEnemy) {
        Coordinate desiredCell = map.findDesiredCell(coordinate, figureTypeEnemy);
        System.out.println(coordinate + " " + desiredCell);
        System.out.println(map.findShortestPath(coordinate, desiredCell) + "путь");
    }

    @Override
    public String toString() {
        return figureType.toString() + " " + coordinate.width + " " + coordinate.height + " " + speed + " " + health;
    }
}
