package org.example;

import java.util.Scanner;

public class Render extends GameMap {
    private static int counterOfMoves;
    private static final Scanner scanner = new Scanner(System.in);

    public static void inputMapSize () {
        System.out.println("Enter the map width ( --- ): ");
        setMapWidth(scanner.nextInt());

        System.out.println("Enter the map height ( | ): ");
        setMapHeight(scanner.nextInt());
    }

    public static void displayOnTheTerminal() {

        StringBuilder line = new StringBuilder();
        for (int height = 0; height < getMapHeight(); height++) {
            line.setLength(0);
            for (int width = 0; width < getMapWidth(); width++) {
                Coordinate coordinate = new Coordinate(width, height);
                line.append(entities.get(coordinate).figureType.getName());
            }
            System.out.println(line);
        }

        displayCounterOfMoves();
        System.out.println("------------------");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void displayCounterOfMoves() {
        counterOfMoves++;
        System.out.println("Количество прошедших ходов: " + counterOfMoves);
    }
}
