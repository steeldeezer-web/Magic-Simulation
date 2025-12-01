import java.util.*;

class WorldMap {
    private final int width, height;
    private Map<Coordinate, Entity> entities = new HashMap<>();

    public WorldMap(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    //Проверяем Координаты в границах карты?
    public boolean isWithInBounds(Coordinate coordinate){
        return coordinate.getX() >= 0 && coordinate.getX() < width && coordinate.getY() >= 0 && coordinate.getY() < height;
    }
    //Проверяем своодна ли точка
    public boolean isSpotFree(Coordinate coordinate){
        return isWithInBounds(coordinate) && !entities.containsKey(coordinate);
    }
    //Получение объекта по координате
    public Entity getEntityAt(Coordinate coordinate){
        return entities.get(coordinate);
    }
    //Добавление объекта
    public void addEntity(Entity entity){
        entities.put(entity.getCoordinate(), entity);
    }
    //Удаление объекта
    public void removeEntity(Entity entity){
        entities.remove(entity.getCoordinate());
    }
    //Выполенине движения по карте
    public void moveEntity(Entity entity, Coordinate newCoordinate){
        entities.remove(entity.getCoordinate());
        entity.coordinate = newCoordinate;
        entities.put(entity.getCoordinate(), entity);
    }

}
