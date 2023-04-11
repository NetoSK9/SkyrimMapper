import java.util.Objects;

public class Village { //vertex
    private String name;
    private int id;
    private int xAxisCoordinates;
    private int yAxisCoordinates;

    public Village(String nameVillage, int id, int x, int y){
        this.name = nameVillage;
        this.id = id;
        this.xAxisCoordinates = x;
        this.yAxisCoordinates = y;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return xAxisCoordinates;
    }

    public void setX(int x) {
        this.xAxisCoordinates = x;
    }

    public int getY() {
        return yAxisCoordinates;
    }

    public void setY(int y) {
        this.yAxisCoordinates = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Village village = (Village) obj;
        return id == village.id &&
                xAxisCoordinates == village.xAxisCoordinates &&
                yAxisCoordinates == village.yAxisCoordinates &&
                Objects.equals(name, village.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, xAxisCoordinates, yAxisCoordinates);
    }

    @Override
    public String toString() {
        return "Village{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", xAxisCoordinates=" + xAxisCoordinates +
                ", yAxisCoordinates=" + yAxisCoordinates +
                '}';
    }
}


