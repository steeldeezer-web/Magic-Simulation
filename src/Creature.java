abstract class Creature extends Entity{
    protected int speed;
    protected String name;
    protected WorldMap worldMap;
    public Creature(Coordinate coordinate, int speed, int health, String name, WorldMap worldMap){
        super(coordinate, health);
        this.speed = speed;
        this.name = name;
        this.worldMap = worldMap;
    }
    abstract void makeMove(Simulation sim);
    @Override
    public String toString(){
        return "name: " + name + "\n" +
                "health: " + health + "\n" +
                "speed: " + speed + "\n" +
                worldMap;
    }

}
