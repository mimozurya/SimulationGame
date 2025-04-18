package org.example;

import org.example.entity.*;

import java.util.*;

public class GameMap {
    static Map<Coordinate, Entity> entities = new HashMap<>();
    private final Random random = new Random();
    private static int mapWidth;
    private static int mapHeight;
    private final int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1},
    };

    public void generateMap() {
        Render.inputMapSize();
        initEntities();
        Render.displayOnTheTerminal();
    }

    private void initEntities() {
        for (int width = 0; width < mapWidth; width++) {
            for (int height = 0; height < mapHeight; height++) {
                Coordinate coordinate = new Coordinate(width, height);
                setEntity(coordinate, random.nextInt(FigureType.values().length));
            }
        }
    }

    public void setEntity(Coordinate coordinate, int idTypeEntity) {
        switch (idTypeEntity) {
            case 0:
                entities.put(coordinate,
                        new Grass(FigureType.values()[idTypeEntity], coordinate));
                break;
            case 1:
                entities.put(coordinate,
                        new Rock(FigureType.values()[idTypeEntity], coordinate));
                break;
            case 2:
                entities.put(coordinate,
                        new Tree(FigureType.values()[idTypeEntity], coordinate));
                break;
            case 3:
                entities.put(coordinate,
                        new Herbivore(FigureType.values()[idTypeEntity], coordinate));
                break;
            case 4:
                entities.put(coordinate,
                        new Predator(FigureType.values()[idTypeEntity], coordinate));
                break;
            case 5:
                entities.put(coordinate,
                        new EmptyCell(FigureType.values()[idTypeEntity], coordinate));
                break;
        }
    }

    public static int getMapWidth() {
        return mapWidth;
    }

    public static int getMapHeight() {
        return mapHeight;
    }

    public static void setMapWidth(int mapWidth) {
        GameMap.mapWidth = mapWidth;
    }

    public static void setMapHeight(int mapHeight) {
        GameMap.mapHeight = mapHeight;
    }

    public Entity getEntity(Coordinate coordinate) {
        return entities.get(coordinate);
    }

    public void createEmptyCell (Coordinate coordinate) {
        entities.put(coordinate,
                new EmptyCell(FigureType.EMPTY_CELL, coordinate));
    }

    public FigureType getFigureType(Coordinate coordinate) {
        return entities.get(coordinate).figureType;
    }

    private int[][] getGridBarrier() {
        int[][] grid = new int[mapHeight][mapWidth];

        for (int height = 0; height < mapHeight; height++) {
            for (int width = 0; width < mapWidth; width++) {
                Coordinate coordinate = new Coordinate(width, height);
                FigureType figureTypeEntity = entities.get(coordinate).figureType;

                if (figureTypeEntity.equals(FigureType.ROCK) ||
                        figureTypeEntity.equals(FigureType.TREE)) {
                    grid[height][width] = 1;
                }
            }
        }

        return grid;
    }

    public List<Coordinate> findShortestPath(Coordinate start, Coordinate end) {

        int[][] grid = getGridBarrier();

        Queue<List<Coordinate>> queue = new LinkedList<>();
        queue.add(Collections.singletonList(start));

        boolean[][] visited = new boolean[mapWidth][mapHeight];
        visited[start.getWidth()][start.getHeight()] = true;

        while (!queue.isEmpty()) {
            List<Coordinate> currentPath = queue.poll();
            Coordinate currentPosition = currentPath.getLast();

            for (int i = 0; i < 4; i++) {
                int newWidth = currentPosition.getWidth() + directions[i][0];
                int newHeight = currentPosition.getHeight() + directions[i][1];

                if (newWidth >= 0 && newHeight >= 0
                        && newWidth < mapWidth && newHeight < mapHeight
                        && grid[newHeight][newWidth] == 0
                        && !visited[newWidth][newHeight]) {

                    visited[newWidth][newHeight] = true;
                    List<Coordinate> newPath = new ArrayList<>(currentPath);
                    newPath.add(new Coordinate(newWidth, newHeight));
                    queue.add(newPath);
                }
            }

            if (currentPosition.getWidth() == end.getWidth() &&
                    currentPosition.getHeight() == end.getHeight()) {
                return currentPath;
            }
        }

        return null;
    }

    public Coordinate findDesiredCell(Coordinate start, FigureType figureTypeEnemy) {
        Queue<Coordinate> queue = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            Entity currentEntity = getEntity(current);

            if (currentEntity != null && getFigureType(currentEntity.getCoordinate()) == figureTypeEnemy) {
                return current;
            }

            for (int[] dir : directions) {
                int newWidth = current.getWidth() + dir[0];
                int newHeight = current.getHeight() + dir[1];
                Coordinate newCoordinate = new Coordinate(newWidth, newHeight);

                if (!visited.contains(newCoordinate)) {
                    visited.add(newCoordinate);
                    queue.add(newCoordinate);
                }
            }
        }

        return null;
    }
}
