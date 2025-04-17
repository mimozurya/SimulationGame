package org.example;

import java.util.*;

public class Map {
    HashMap<Coordinate, Entity> entities = new HashMap<>();
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);
    private int WIDTH_MAP;
    private int HEIGHT_MAP;

    public void generateMap() {
        System.out.println("Enter the map width ( --- ): ");
        WIDTH_MAP = scanner.nextInt();
        System.out.println("Enter the map height ( | ): ");
        HEIGHT_MAP = scanner.nextInt();

        setupMapEntities();
        displayOnTheTerminal();
    }

    public void displayOnTheTerminal() {
        StringBuilder line = new StringBuilder();

        for (int height = 0; height < HEIGHT_MAP; height++) {
            line.setLength(0);
            for (int width = 0; width < WIDTH_MAP; width++) {
                Coordinate coordinate = new Coordinate(width, height);
                line.append(getEmojiFromString(entities.get(coordinate).figureType));
            }
            System.out.println(line.toString());
        }
        System.out.println("------------------");

    }

    private String getEmojiFromString(FigureType figureType) {
        return switch (figureType) {
            case FigureType.PREDATOR -> "\uD83D\uDE08";
            case FigureType.TREE -> "\uD83C\uDF33";
            case FigureType.GRASS -> "\uD83C\uDF3F";
            case FigureType.ROCK -> "\uD83D\uDDFB";
            case FigureType.HERBIVORE -> "\uD83D\uDC04";
            default -> "\uD83D\uDFE9";
        };
    }

    private void setupMapEntities() {
        for (int width = 0; width < WIDTH_MAP; width++) {
            for (int height = 0; height < HEIGHT_MAP; height++) {
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
        int[][] grid = new int[HEIGHT_MAP][WIDTH_MAP];

        for (int height = 0; height < HEIGHT_MAP; height++) {
            for (int width = 0; width < WIDTH_MAP; width++) {
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

        boolean[][] visited = new boolean[WIDTH_MAP][HEIGHT_MAP];
        visited[start.getWidth()][start.getHeight()] = true;

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!queue.isEmpty()) {
            List<Coordinate> currentPath = queue.poll();
            Coordinate currentPos = currentPath.getLast();

            if (currentPos.getWidth() == end.getWidth() &&
                    currentPos.getHeight() == end.getHeight()) {
                return currentPath;
            }

            for (int[] direction : directions) {
                int newWidth = currentPos.getWidth() + direction[0];
                int newHeight = currentPos.getHeight() + direction[1];

                if (newWidth >= 0 && newHeight >= 0
                        && newWidth < WIDTH_MAP && newHeight < HEIGHT_MAP
                        && grid[newWidth][newHeight] == 0
                        && !visited[newWidth][newHeight]) {

                    visited[newWidth][newHeight] = true;
                    List<Coordinate> newPath = new ArrayList<>(currentPath);
                    newPath.add(new Coordinate(newWidth, newHeight));
                    queue.add(newPath);
                }
            }
        }

        return null;
    }

    public Coordinate findDesiredCell(Coordinate start, FigureType figureTypeEnemy) {
        Queue<Coordinate> queue = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();

        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1},
        };

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            Entity currentEntity = getEntity(current);

            if (currentEntity != null && getFigureType(currentEntity.coordinate) == figureTypeEnemy) {
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

    public boolean makeNextTurn () {
        System.out.println("Делать следующий ход? (Y/N)");
        String answer = scanner.nextLine().toUpperCase();
        return answer.equals("Y");
    }
}
