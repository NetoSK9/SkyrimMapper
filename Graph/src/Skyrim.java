import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Skyrim { //graph
    private List<Point> villages;
    private List<Route> routes;

    public Skyrim(){
        villages = new ArrayList<>();
        routes = new ArrayList<>();
    }

    public void addVillage(Point v){
        villages.add(v);
    }

    public void addRoute(Route r){
        routes.add(r);
    }
    public boolean removeRoute(Route r) {
        if (r == null || !routes.contains(r)) {
            return false;
        }
        routes.remove(r);

        return true;
    }
    public List<Point> getVillages(){
        return villages;
    }

    public List<Route> getRoutes(){
        return routes;
    }

    public void setVillages(List<Point> villages) {
        this.villages = villages;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public int getIndexVillage(Point v){
        return villages.indexOf(v);
    }

    public Point getVillage(int index){
        return villages.get(index);
    }

    public Route getRoute(Point originMarked, Point markedDestination){
        for (Route r : routes){
            if(r.getOrigin().equals(originMarked) && r.getDestiny().equals(markedDestination)){
                return r;
            }
        }
        return null;
    }

    public boolean containsVillage(Point p) {
        for (Point point : villages) {
            if (point.equals(p)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsRoute(Route r) {
        for (Route route : routes) {
            if (route.equals(r)) {
                return true;
            }
        }
        return false;
    }

    public int getNumberVillages(){
        return villages.size();
    }

    public int getNumberRoutes(){
        return routes.size();
    }

    public int[][] getMatrixAdj() {
        int [][] matrix = new int[villages.size()][routes.size()];

        for(int i = 0; i < villages.size(); i++){
            for(int j = 0; j < routes.size(); j++){
                matrix[i][j] = -1;
            }
        }
        for(Route r: routes){
            int i = getIndexVillage(r.getOrigin());
            int j = getIndexVillage(r.getDestiny());
            //matrix[i][j] = r.getWeight();
        }
        return matrix;
    }

    public void printMatrixAdj(int[][] matrix) {
        System.out.println("Matrix Adj:");

        System.out.print("\t");
        for (int i = 0; i < matrix[0].length; i++) {
            System.out.print("V" + (i + 1) + "\t");
        }
        System.out.println();

        for (int i = 0; i < matrix.length; i++) {
            System.out.print("V" + (i + 1) + ":\t");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public int[] dijkstra(Point origin) {
        int n = villages.size();
        int[] distance = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        int iOrigin = getIndexVillage(origin);
        distance[iOrigin] = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = minimumDistance(distance, visited);
            visited[u] = true;

            for (int j = 0; j < n; j++) {
                if (!visited[j] && getRoute(getVillage(u), getVillage(j)) != null) {
                    int peso = getRoute(getVillage(u), getVillage(j)).getWeight();

                    if (distance[u] != Integer.MAX_VALUE && distance[u] + peso < distance[j]) {
                        distance[j] = distance[u] + peso;
                    }
                }
            }
        }
        return distance;
    }

    private int minimumDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndice = -1;

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] <= min) {
                min = dist[i];
                minIndice = i;
            }
        }
        return minIndice;
    }
}
