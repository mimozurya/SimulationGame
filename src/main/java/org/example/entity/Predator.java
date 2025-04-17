package org.example.entity;

import org.example.*;

import java.util.List;

public class Predator extends Creature {
    private final FigureType figureTypeEnemy = FigureType.HERBIVORE;

    public Predator(FigureType figureType, Coordinate coordinate) {
        super(figureType, coordinate);
    }

    @Override
    public void makeMove(GameMap gameMap) {
        Coordinate desiredCell = gameMap.findDesiredCell(coordinate, figureTypeEnemy);
        List<Coordinate> listShortestPath = gameMap.findShortestPath(coordinate, desiredCell);

        if (listShortestPath != null && listShortestPath.size() >= 2
                && gameMap.getFigureType(listShortestPath.get(1)) != FigureType.PREDATOR) {

            Coordinate oldCoordinate = coordinate;
            coordinate = listShortestPath.get(1);
            gameMap.setEntity(coordinate, FigureType.valueOf(figureType.toString()).ordinal());
            gameMap.createEmptyCell(oldCoordinate);
            Render.displayOnTheTerminal();
        }
    }
}
