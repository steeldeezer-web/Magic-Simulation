import java.util.*;

class Simulation {
    private WorldMap map;
    private List<Creature> creatures = new ArrayList<>();
    private Renderer renderer;
    private int turnCount = 0;
    private boolean running = false;

    public Simulation(int width, int height) {
        this.map = new WorldMap(width, height);
        this.renderer = new Renderer(map);
    }

    public WorldMap getMap() {
        return map;
    }

    // Добавить существо в симуляцию
    public void addCreature(Creature creature) {
        creatures.add(creature);
        map.addEntity(creature);
    }

    // Убрать существо
    public void removeCreature(Creature creature) {
        creatures.remove(creature);
        map.removeEntity(creature);
    }

    // Перемещение существа (обновление карты и позиции)
    public void moveCreature(Creature creature, Coordinate newPos) {
        map.moveEntity(creature, newPos);
        creature.coordinate = newPos;
    }

    // Один шаг симуляции
    public void nextTurn() {
        turnCount++;
        System.out.println("Turn " + turnCount);

        for (Creature c : new ArrayList<>(creatures)) {
            if (c.isAlive()) {
                c.makeMove(this);
            } else {
                removeCreature(c);
            }
        }
        renderer.render();
    }

    // Запуск симуляции с количеством шагов
    public void startSimulation(int maxTurns) {
        running = true;
        for (int i = 0; i < maxTurns && running; i++) {
            nextTurn();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    // Остановка симуляции
    public void pauseSimulation() {
        running = false;
    }
}