import java.lang.foreign.Arena;
import java.util.List;
import java.util.UUID;

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
    public boolean getIsAoeAttack(){
        return isAoeAttack;
    }

    public void setAoeAttack(boolean aoeAttack) {
        isAoeAttack = aoeAttack;
    }

    public void setArtefactCount(int artefactCount) {
        this.artefactCount = artefactCount;
    }

    public int getArtefactCount() {
        return artefactCount;
    }

    @Override
    public Coordinate getCoordinate() {
        return super.getCoordinate();
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

    @Override
    public UUID getID() {
        return super.getID();
    }

    public int getAttackPower() {
        return attackPower;
    }

    @Override
    void makeMove(Simulation sim) {
        List<Coordinate> pathToPrey = PathFinder.findPathToNearestEntity(coordinate, sim.getMap(), Entity.class);
        if (pathToPrey != null && pathToPrey.size() > 1) {
            Coordinate nextStep = pathToPrey.get(1);
            Entity target = sim.getMap().getEntityAt(nextStep);
            if (target != null) {
                if (target instanceof Magic) {
                    target.reduceHealth(attackPower);
                    if (!target.isAlive()) {
                        if (target instanceof Creature) {  // проверка перед удалением
                            sim.removeCreature((Creature) target);
                        }
                    }
                    if (isAoeAttack) {
                        performAoeAttack(target.coordinate, sim);
                    }
                } else if (target instanceof Artefact) {
                    artefactCount++;
                    sim.getMap().removeEntity(target);  // Артефакт не Creature
                    if (artefactCount >= 3) {
                        isAoeAttack = true;
                    }
                    sim.moveCreature(this, nextStep);  // используем moveCreature
                } else {
                    target.reduceHealth(attackPower);
                    if (!target.isAlive()) {
                        if (target instanceof Creature) {
                            sim.removeCreature((Creature) target);
                        }
                    }
                    if (isAoeAttack) {
                        performAoeAttack(target.coordinate, sim);
                    }
                }
            } else if (sim.getMap().isSpotFree(nextStep)) {
                sim.moveCreature(this, nextStep);
            }
        }
    }
    private void performAoeAttack(Coordinate targetPosition, Simulation sim) {
        List<Coordinate> neighbors = sim.getMap().getNeighbors(targetPosition);  // sim.getMap()
        for (Coordinate neighbor : neighbors) {
            Entity entity = sim.getMap().getEntityAt(neighbor);  // sim.getMap()
            if (entity != null) {
                if (entity instanceof Artefact) {
                    artefactCount++;
                    sim.getMap().removeEntity(entity);
                    if (artefactCount >= 3) {
                        isAoeAttack = true;
                    }
                } else if (!(entity instanceof Magic)) {
                    entity.reduceHealth(attackPower);
                    if (!entity.isAlive() && entity instanceof Creature) {
                        sim.removeCreature((Creature) entity);
                    }
                }
            }
        }
    }




}
