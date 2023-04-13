import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    private List<Route> routesList;
    private List<Point> verticesList;

    public Dijkstra(List<Route> routesList, List<Point> verticesList) {
        this.routesList = routesList;
        this.verticesList = verticesList;
    }

    public List<Integer> lowestCostRoute(Point origin, Point destiny) {
        int numVertices = verticesList.size();
        List<List<Route>> graph = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }

        for (Route route : routesList) {
            int indexOrigin = verticesList.indexOf(route.getOrigin());
            int indexDestiny = verticesList.indexOf(route.getDestiny());
            graph.get(indexOrigin).add(route);
            graph.get(indexDestiny).add(route);
        }

        int startIndex = verticesList.indexOf(origin);
        int endIndex = verticesList.indexOf(destiny);

        int[] distances = new int[numVertices];
        int[] previousVertices = new int[numVertices];
        PriorityQueue<Integer> pq = new PriorityQueue<>((u, v) -> distances[u] - distances[v]);
        for (int i = 0; i < numVertices; i++) {
            if (i == startIndex) {
                distances[i] = 0;
            } else {
                distances[i] = Integer.MAX_VALUE;
            }
            previousVertices[i] = -1;
            pq.add(i);
        }

        while (!pq.isEmpty()) {
            int u = pq.poll();
            if (distances[u] == Integer.MAX_VALUE) {
                break;
            }

            for (Route route : graph.get(u)) {
                int v = verticesList.indexOf(getOtherVertex(u, route));
                int alternativeDistance = distances[u] + route.getWeight();
                if (alternativeDistance < distances[v]) {
                    distances[v] = alternativeDistance;
                    previousVertices[v] = u;
                    pq.remove(v);
                    pq.add(v);
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int u = endIndex;
        while (previousVertices[u] != -1) {
            path.add(0, getRouteIndex(previousVertices[u], u));
            u = previousVertices[u];
        }

        return path;
    }

    private Point getOtherVertex(int u, Route route) {
        if (route.getOrigin().equals(verticesList.get(u))) {
            return route.getDestiny();
        } else {
            return route.getOrigin();
        }
    }

    private int getRouteIndex(int u, int v) {
        for (int i = 0; i < routesList.size(); i++) {
            Route route = routesList.get(i);
            if ((route.getOrigin().equals(verticesList.get(u)) && route.getDestiny().equals(verticesList.get(v))) ||
                    (route.getOrigin().equals(verticesList.get(v)) && route.getDestiny().equals(verticesList.get(u)))) {
                return i;
            }
        }
        return -1;
    }

}
