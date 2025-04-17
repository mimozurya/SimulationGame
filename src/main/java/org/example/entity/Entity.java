package org.example.entity;

import org.example.Coordinate;
import org.example.FigureType;

public abstract class Entity {
    public Coordinate coordinate;
    public FigureType figureType;

    public Entity(FigureType figureType, Coordinate coordinate) {
        this.figureType = figureType;
        this.coordinate = coordinate;
    }
}
