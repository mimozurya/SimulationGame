package org.example;

import java.util.List;

public class Predator extends Creature {
    public int firePower;
    private final FigureType figureTypeEnemy = FigureType.HERBIVORE;

    public Predator(FigureType figureType, Coordinate coordinate) {
        super(figureType, coordinate);
    }

    @Override
    protected void makeMove(Map map) {
        Coordinate desiredCell = map.findDesiredCell(coordinate, figureTypeEnemy);
        List<Coordinate> listShortestPath = map.findShortestPath(coordinate, desiredCell);

        if (listShortestPath != null && listShortestPath.size() >= 2) {

            Coordinate oldCoordinate = coordinate;
            coordinate = listShortestPath.get(1);
            map.setEntity(coordinate, FigureType.valueOf(figureType.toString()).ordinal());
            map.createEmptyCell(oldCoordinate);
        }

//        System.out.println(coordinate + " " + desiredCell);
//        System.out.println(listShortestPath + "путь");
    }

    // makeMove()
    // attackHerbivore()
}
