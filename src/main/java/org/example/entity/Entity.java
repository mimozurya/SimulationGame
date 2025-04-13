package org.example;

public abstract class Entity {
    Coordinate coordinate;
    FigureType figureType;

    public Entity(Coordinate coordinate, FigureType figureType) {
        this.coordinate = coordinate;
        this.figureType = figureType;
    }
}
