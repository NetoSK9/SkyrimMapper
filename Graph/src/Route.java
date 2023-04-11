public class Route { //edge
    private Village origin;
    private Village destiny;
    private int weight;
    private int id;

    public Route(Village origin, Village destiny, int weight, int id){
        this.origin = origin;
        this.destiny = destiny;
        this.weight = weight;
        this.id = id;
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

    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }

}
