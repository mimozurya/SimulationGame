package org.example;

public class Simulation {
    public void startSimulation() {
        Map map = new Map();
        map.generateMap();

        for (Entity entity : map.entities.values()) {
            if (entity.figureType == FigureType.HERBIVORE) {
                Creature creature = (Creature) entity;
                creature.makeMove(map, FigureType.GRASS);
            }
        }
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
