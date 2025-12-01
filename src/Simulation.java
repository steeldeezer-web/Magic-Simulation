import java.util.ArrayList;
import java.util.List;

class Simulation {
    private WorldMap worldMap;
    private List<Entity> entityList = new ArrayList<>();
    public WorldMap getWorldMap(){
        return  worldMap;
    }
    public void removeEntity(Entity entity){
        entityList.remove(entity);
        worldMap.removeEntity(entity);
    }
    public void moveEntity(Entity entity, Coordinate newPos){
        worldMap.moveEntity(entity,newPos);
        entity.coordinate = newPos;
    }

}
