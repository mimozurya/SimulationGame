package org.example;

import java.util.List;

public class Herbivore extends Creature {
    private final FigureType figureTypeEnemy = FigureType.GRASS;
    private final FigureType figureTypeDeath = FigureType.PREDATOR;


    public Herbivore(FigureType figureType, Coordinate coordinate) {
        super(figureType, coordinate);
    }

    @Override
    protected void makeMove(Map map) {
        Coordinate desiredCell = map.findDesiredCell(coordinate, figureTypeEnemy);
        List<Coordinate> listShortestPath = map.findShortestPath(coordinate, desiredCell);

        if (listShortestPath != null
                && listShortestPath.size() >= 2
                && map.getFigureType(listShortestPath.get(1)) != figureTypeDeath) {

            Coordinate oldCoordinate = coordinate;
            coordinate = listShortestPath.get(1);
            map.setEntity(coordinate, FigureType.valueOf(figureType.toString()).ordinal());
            System.out.println(FigureType.valueOf(figureType.toString()).ordinal() + "ID FIGURETYPE HERBIVORE");
            map.createEmptyCell(oldCoordinate);

            System.out.println(listShortestPath);
            System.out.println(oldCoordinate + " " + coordinate + " " + figureType + listShortestPath.get(1));
        }

//        System.out.println(coordinate + " " + desiredCell);
//        System.out.println(listShortestPath + "путь");
    }

    // makeMove()
    // findFood ()
    // eatFood ()
}
