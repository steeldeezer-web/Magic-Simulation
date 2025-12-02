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
    public List<Coordinate> getNeighbors(Coordinate coord){
        List<Coordinate> neigbors = new ArrayList<>();
        int x = coord.getX();
        int y = coord.getY();

        for(int dx = -1; dx <=1; dx++){
            for(int dy = -1; dy <=1; dy++){
                if(dx == 0 && dy == 0) continue; // пропуск базовой клетки с целью
                int nx  = x + dx;
                int ny = y + dy;
                if(isWithInBounds(new Coordinate(nx, ny))){
                    neigbors.add(new Coordinate(nx, ny));
                }
            }
        }
        return neigbors;
    }

}
