import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
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
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer u, Integer v) {
                return distances[u] - distances[v];
            }
        });
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
            int currentVertex = pq.poll();
            if (distances[currentVertex] == Integer.MAX_VALUE) {
                break;
            }

            for (Route route : graph.get(currentVertex)) {
                int nextVertex = verticesList.indexOf(getOtherVertex(currentVertex, route));
                int alternativeDistance = distances[currentVertex] + route.getWeight();
                if (alternativeDistance < distances[nextVertex]) {
                    distances[nextVertex] = alternativeDistance;
                    previousVertices[nextVertex] = currentVertex;
                    pq.remove(nextVertex);
                    pq.add(nextVertex);
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int currentVertex = endIndex;
        while (previousVertices[currentVertex] != -1) {
            path.add(0, getRouteIndex(previousVertices[currentVertex], currentVertex));
            currentVertex = previousVertices[currentVertex];
        }

        return path;
    }

    private Point getOtherVertex(int currentVertex, Route route) {
        if (route.getOrigin().equals(verticesList.get(currentVertex))) {
            return route.getDestiny();
        } else {
            return route.getOrigin();
        }
    }

    private int getRouteIndex(int currentVertex, int nextVertex) {
        for (int i = 0; i < routesList.size(); i++) {
            Route route = routesList.get(i);
            if ((route.getOrigin().equals(verticesList.get(currentVertex)) && route.getDestiny().equals(verticesList.get(nextVertex))) ||
                    (route.getOrigin().equals(verticesList.get(nextVertex)) && route.getDestiny().equals(verticesList.get(currentVertex)))) {
                return i;
            }
        }
        return -1;
    }

}
