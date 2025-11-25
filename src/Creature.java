abstract class Creature extends Entity{
    protected int speed;
    protected int health;
    protected String name;
    protected WorldMap worldMap;
    public Creature(Coordinate coordinate, int speed, int health, String name, WorldMap worldmap){
        super(coordinate);
        this.speed = speed;
        this.health = health;
        this.name = name;
        this.worldMap = worldmap;
    }
    public boolean isAlive(){
        return health > 0;
    }
    public void reduceHealth(int damage){
        health = Math.max(0, health - damage);
    }
    abstract void makeMove();
    @Override
    public String toString(){
        return "name: " + name + "\n" +
                "health: " + health + "\n" +
                "speed: " + speed + "\n" +
                worldMap;
    }

}
