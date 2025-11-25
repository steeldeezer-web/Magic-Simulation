import java.util.UUID;
abstract class Entity {
    protected UUID ID;
    protected  Coordinate coordinate;

    public Entity(Coordinate coordinate){
        this.ID = UUID.randomUUID();
        this.coordinate = coordinate;
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

    @Override
    public String toString() {
        return "ID: " + ID + "\n" +
                coordinate;
    }
}
