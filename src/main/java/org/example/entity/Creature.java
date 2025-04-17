package org.example.entity;

import org.example.Coordinate;
import org.example.FigureType;
import org.example.GameMap;

public abstract class Creature extends Entity {
    public Creature(FigureType figureType, Coordinate coordinate) {
        super(figureType, coordinate);
    }

    public abstract void makeMove(GameMap gameMap);

}
