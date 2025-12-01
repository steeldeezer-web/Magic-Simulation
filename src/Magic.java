import java.util.List;

class Magic extends Creature{
    private int attackPower;
    private int artefactCount;
    private boolean isAoeAttack;

    public Magic(Coordinate coordinate, int speed, int health, int attackPower, WorldMap worldMap){
        super(coordinate, speed, health, "Magic", worldMap);
        this.attackPower = attackPower;
        this.artefactCount = 0;
        this.isAoeAttack = false;
    }

    @Override
    void makeMove(Simulation sim) {
        // Ищем ближайшего соперника или артефакт
        List<Coordinate> pathToPrey = PathFinder.findPathToNearestEntity(coordinate, sim.getWorldMap(), Entity.class);
        if(pathToPrey != null && pathToPrey.size() > 1){
            Coordinate nextStep = pathToPrey.get(1);
            Entity target = sim.getWorldMap().getEntityAt(nextStep);
            if(target instanceof Magic){
                //Если в соседней клетке есть Маг атакуем
                target.reduceHealth(attackPower);
                if (!target.isAlive()){
                    sim.removeEntity(target);
                    sim.getWorldMap().removeEntity(target);
                } else if (sim.getWorldMap().isSpotFree(nextStep)) {
                    //Если точка пуста идем туда
                    sim.moveEntity(this, nextStep);
                }
            } else if (target instanceof Artefact) {
                artefactCount++;
                sim.removeEntity(target);
                sim.getWorldMap().removeEntity(target);
                if(artefactCount >= 3){
                    isAoeAttack = true;
                }

            }
        }
    }
}
