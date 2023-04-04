public class Village { //vertices
    private int id;
    private int coordenadas_x;
    private int coordenadas_y;

    public Village(int id, int coordenadas_x, int coordenadas_y){
        this.id = id;
        this.coordenadas_x = coordenadas_x;
        this.coordenadas_y = coordenadas_y;

    }

    public int getId() {
        return id;
    }

    public int getX() {
        return coordenadas_x;
    }

    public int getY() {
        return coordenadas_y;
    }
}

