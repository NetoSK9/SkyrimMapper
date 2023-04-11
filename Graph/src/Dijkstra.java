import java.awt.*;
import java.util.*;
import java.util.List;

public class Dijkstra {

    public static List<Integer> lowestCostRoute(List<Route> routes, Point origin, Point destination) {

        Map<Point, Integer> distance = new HashMap<>();
        Map<Point, Point> prev = new HashMap<>();
        Set<Point> unvisited = new HashSet<>();

        // inicializa as distâncias e os predecessores
        for (Route route : routes) {
            distance.put(route.getOrigin(), Integer.MAX_VALUE);
            distance.put(route.getDestiny(), Integer.MAX_VALUE);
            prev.put(route.getOrigin(), null);
            prev.put(route.getDestiny(), null);
            unvisited.add(route.getOrigin());
            unvisited.add(route.getDestiny());
        }

        distance.put(origin, 0);

        while (!unvisited.isEmpty()) {
            // encontra o vértice com a menor distância
            Point current = null;
            int minDistance = Integer.MAX_VALUE;
            for (Point point : unvisited) {
                int dist = distance.get(point);
                if (dist < minDistance) {
                    minDistance = dist;
                    current = point;
                }
            }

            unvisited.remove(current);

            // calcula a distância dos vizinhos
            for (Route route : routes) {
                if (route.getOrigin().equals(current)) {
                    int alt = distance.get(current) + route.getWeight();
                    if (alt < distance.get(route.getDestiny())) {
                        distance.put(route.getDestiny(), alt);
                        prev.put(route.getDestiny(), current);
                    }
                } else if (route.getDestiny().equals(current)) {
                    int alt = distance.get(current) + route.getWeight();
                    if (alt < distance.get(route.getOrigin())) {
                        distance.put(route.getOrigin(), alt);
                        prev.put(route.getOrigin(), current);
                    }
                }
            }
        }

        // constrói a menor rota
        List<Integer> shortestPath = new ArrayList<>();
        Point current = destination;
        while (prev.get(current) != null) {
            for (int i = 0; i < routes.size(); i++) {
                Route route = routes.get(i);
                if ((route.getOrigin().equals(prev.get(current)) && route.getDestiny().equals(current))
                        || (route.getDestiny().equals(prev.get(current)) && route.getOrigin().equals(current))) {
                    shortestPath.add(i);
                    break;
                }
            }
            current = prev.get(current);
        }

        Collections.reverse(shortestPath);

        return shortestPath;
    }
}
