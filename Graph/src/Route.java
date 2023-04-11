import java.awt.*;

public class Route { //edge
    private Point origin;
    private Point destiny;
    private Color color;
    private int weight;
    private int id;

    public Route(Point origin, Point destiny, int weight, int id){
        this.origin = origin;
        this.destiny = destiny;
        this.weight = weight;
        this.id = id;
        color = Color.BLACK;
    }

    public Route(Point origin, Point destiny, int id){
        this.origin = origin;
        this.destiny = destiny;
        this.weight = calculateWeight(origin,destiny);
        this.id = id;
        color = Color.BLACK;
    }


    public Point getOrigin() {
        return origin;
    }

    public Point getDestiny() {
        return destiny;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int calculateWeight(Point pointOrigin, Point pointDestiny){
        double distanceX = pointDestiny.x - pointOrigin.x;
        double distanceY = pointDestiny.y - pointOrigin.y;
        int response =Double.valueOf( Math.sqrt( (distanceX*distanceX) + (distanceY * distanceY) ) ).intValue();
        return response;
    }
}
