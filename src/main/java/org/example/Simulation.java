package org.example;

public class Simulation {
    public void startSimulation() {
        Map map = new Map();
        map.generateMap();

        for (Entity entity : map.entities.values()) {
            if (entity instanceof Creature creature) {
                creature.makeMove(map);
            }
        }
        map.displayOnTheTerminal();

//        while (true) {
//            for (Entity entity : map.entities.values()) {
//                if (entity instanceof Creature creature) {
//                    creature.makeMove(map);
//                }
//            }
//            map.displayOnTheTerminal();
//
//            if (map.makeNextTurn()) {
//                System.out.println("Да, он может сделать следующий ход");
//            }
//        }
    }

    // карта
    // счетчик ходов
    // рендерер поля
    // actions

    // nextTurn()
    // startSimulation()
    // pauseSimulation()

    // Actions:
    // initActions - действия перед стартом симуляции
    // turnActions - действия каждый ход (передвижение, добавить травы или травоядных)
}
