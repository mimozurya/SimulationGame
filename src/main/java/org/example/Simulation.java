package org.example;

import org.example.entity.Creature;
import org.example.entity.Entity;

public class Simulation {
    public void startSimulation() {
        GameMap gameMap = new GameMap();
        gameMap.generateMap();

        while (true) {
            for (Entity entity : GameMap.entities.values()) {
                if (entity instanceof Creature creature) {
                    creature.makeMove(gameMap);
                }
            }
        }
    }
}
