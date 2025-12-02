class Renderer {
    private final WorldMap map;
    public Renderer(WorldMap map){this.map = map;}
    public void render(){
        for(int y = 0; y < map.getHeight(); y++){
            for (int x = 0; x < map.getWidth(); x++){
                Coordinate c = new Coordinate(x,y);
                Entity e = map.getEntityAt(c);
                if(e == null) System.out.print(". ");
                else if(e instanceof Magic) System.out.print("M ");
                else if(e instanceof Artefact) System.out.print("A ");
                else System.out.print("? ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
