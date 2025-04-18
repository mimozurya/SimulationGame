package org.example.entity;

import org.example.Coordinate;
import org.example.FigureType;

public abstract class Entity {
    private Coordinate coordinate;
    public FigureType figureType;

    public Entity(FigureType figureType, Coordinate coordinate) {
        this.figureType = figureType;
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
