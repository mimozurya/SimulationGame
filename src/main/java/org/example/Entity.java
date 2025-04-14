package org.example;

public abstract class Entity {
    Coordinate coordinate;
    FigureType figureType;

    public Entity(FigureType figureType, Coordinate coordinate) {
        this.figureType = figureType;
        this.coordinate = coordinate;
    }
}
