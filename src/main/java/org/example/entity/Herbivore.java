package org.example.entity;

import org.example.*;

import java.util.List;

public class Herbivore extends Creature {
    private final FigureType targetFigure = FigureType.GRASS;
    private final FigureType dangerFigure = FigureType.PREDATOR;


    public Herbivore(FigureType figureType, Coordinate coordinate) {
        super(figureType, coordinate);
    }

    @Override
    public void makeMove(GameMap gameMap) {
        Coordinate coordinate = getCoordinate();

        Coordinate desiredCell = gameMap.findDesiredCell(coordinate, targetFigure);
        List<Coordinate> listShortestPath = gameMap.findShortestPath(coordinate, desiredCell);

        if (listShortestPath != null
                && listShortestPath.size() >= 2
                && gameMap.getFigureType(listShortestPath.get(1)) != FigureType.HERBIVORE
                && gameMap.getFigureType(listShortestPath.get(1)) != dangerFigure) {

            Coordinate oldCoordinate = coordinate;
            coordinate = listShortestPath.get(1);
            gameMap.setEntity(coordinate, FigureType.valueOf(figureType.toString()).ordinal());
            gameMap.createEmptyCell(oldCoordinate);
            Render.displayOnTheTerminal();
        }
    }
}
