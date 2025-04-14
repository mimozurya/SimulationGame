package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Map {
    HashMap<Coordinate, Entity> entities = new HashMap<>();
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);

    public void generateMap() {
        System.out.println("Enter the map width: ");
        int width = scanner.nextInt();
        System.out.println("Enter the map height: ");
        int height = scanner.nextInt();

        setupMapEntities(width, height);
        displayOnTheTerminal(width,height);
    }

    private void displayOnTheTerminal (int width, int height) {
        ArrayList<String> emojiList = new ArrayList<>();
        for (Entity entity : entities.values()) {
            emojiList.add(getEmojiFromString(entity.figureType.toString()));
        }
    }

    private String getEmojiFromString(String emojiChar) {
        return switch (emojiChar) {
            case "PREDATOR" -> "\uD83D\uDE08";
            case "TREE" -> "\uD83C\uDF33";
            case "GRASS" -> "\uD83C\uDF3F";
            case "ROCK" -> "\uD83E\uDEA8";
            case "HERBIVORE" -> "\uD83D\uDC04";
            default -> "";
        };
    }

    private void setupMapEntities (int widthMap, int heightMap) {
        for (int width = 0; width < widthMap; width++) {
            for (int height = 0; height < heightMap; height++) {
                Coordinate coordinate = new Coordinate(width, height);
                setEntity(coordinate, random.nextInt(FigureType.values().length));
            }
        }
    }

    private void setEntity (Coordinate coordinate, int randomInt) {
        switch (randomInt) {
            case 0:
                entities.put(coordinate,
                        new Grass(FigureType.values()[randomInt], coordinate));
                break;
            case 1:
                entities.put(coordinate,
                        new Rock(FigureType.values()[randomInt], coordinate));
                break;
            case 2:
                entities.put(coordinate,
                        new Tree(FigureType.values()[randomInt], coordinate));
                break;
            case 3:
                entities.put(coordinate,
                        new Herbivore(FigureType.values()[randomInt], coordinate));
                break;
            case 4:
                entities.put(coordinate,
                        new Predator(FigureType.values()[randomInt], coordinate));
                break;
        }
    }

    public Entity getEntity(Coordinate coordinate) {
        return entities.get(coordinate);
    }
}
