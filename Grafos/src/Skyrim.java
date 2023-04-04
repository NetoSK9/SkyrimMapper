import java.util.ArrayList;
import java.util.List;

public class Skyrim { //graph
    private List<Village> villages;
    private List<Route> routes;

    public Skyrim(){
        villages = new ArrayList<>();
        routes = new ArrayList<>();
    }

    public void addVillage(Village v){
        villages.add(v);
    }

    public void addRoute(Route r){
        routes.add(r);
    }

    public List<Village> getVillages(){
        return villages;
    }

    public List<Route> getRoutes(){
        return routes;
    }

    public int getIndexVillage(Village v){
        return villages.indexOf(v);
    }

    public Village getVillage(int index){
        return villages.get(index);
    }

    public Route getRoute(Village originMarked, Village markedDestination){
        for (Route r : routes){
            if(r.getOrigin().equals(originMarked) && r.getDestiny().equals(markedDestination)){
                return r;
            }
        }
        return null;
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
            matrix[i][j] = r.getWeight();
        }

        return matrix;
    }
}
