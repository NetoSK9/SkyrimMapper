public class Village { //vertex
    private int id;
    private int xAxisCoordinates;
    private int yAxisCoordinates;

    public Village(int id, int x, int y){
        this.id = id;
        this.xAxisCoordinates = x;
        this.yAxisCoordinates = y;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return xAxisCoordinates;
    }

    public int getY() {
        return yAxisCoordinates;
    }
}

