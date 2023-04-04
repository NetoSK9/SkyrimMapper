public class Route { //edge
    private Village origin;
    private Village destiny;
    private int weight;

    public Route(Village origin, Village destiny, int weight){
        this.origin = origin;
        this.destiny = destiny;
        this.weight = weight;
    }

    public Village getOrigin() {
        return origin;
    }

    public Village getDestiny() {
        return destiny;
    }

    public int getWeight() {
        return weight;
    }
}
