public class Route {
    private Village origin;
    private Village destiny;
    private int weight;

    public Route(Village o, Village d, int weight){
        this.origin = o;
        this.destiny = d;
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
