import java.util.UUID;
abstract class Entity {
    protected UUID ID;
    protected  Coordinate coordinate;
    protected int health;


    public Entity(Coordinate coordinate, int health){
        this.ID = UUID.randomUUID();
        this.coordinate = coordinate;
        this.health = health;
    }

    public UUID getID() {
        return ID;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
    public boolean isAlive(){
        return health > 0;
    }
    public void reduceHealth(int damage){
        health = Math.max(0, health - damage);
    }

    @Override
    public String toString() {
        return "ID: " + ID + "\n" +
                "Health: " + health + "\n" +
                coordinate;
    }
}
