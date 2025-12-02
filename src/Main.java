//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(10,10);

        simulation.addCreature(new Magic(new Coordinate(3,3), 1, 10, 1, simulation.getMap()));
        simulation.addCreature(new Magic(new Coordinate(0,0), 1, 10, 1, simulation.getMap()));

        simulation.getMap().addEntity(new Artefact(new Coordinate(7,7)));
        simulation.getMap().addEntity(new Artefact(new Coordinate(5,5)));

        simulation.startSimulation(20);
    }
}