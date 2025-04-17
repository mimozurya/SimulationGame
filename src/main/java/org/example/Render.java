package org.example;

public class Render extends GameMap {
    private static int counterOfMoves;

    public static void displayOnTheTerminal() {

        StringBuilder line = new StringBuilder();
        for (int height = 0; height < HEIGHT_MAP; height++) {
            line.setLength(0);
            for (int width = 0; width < WIDTH_MAP; width++) {
                Coordinate coordinate = new Coordinate(width, height);
                line.append(getEmojiFromString(entities.get(coordinate).figureType));
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

    private static String getEmojiFromString(FigureType figureType) {
        return switch (figureType) {
            case FigureType.PREDATOR -> "\uD83D\uDE08";
            case FigureType.TREE -> "\uD83C\uDF33";
            case FigureType.GRASS -> "\uD83C\uDF3F";
            case FigureType.ROCK -> "\uD83D\uDDFB";
            case FigureType.HERBIVORE -> "\uD83D\uDC04";
            default -> "\uD83D\uDFE9";
        };
    }
}
