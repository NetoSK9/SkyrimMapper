import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class SkyrimTest {
    private Skyrim skyrim;
    private Point whiterun; //nao definindo no escopo pois está sendo usado em mais de 1 método
    private Point morthal;
    private Point markarth;
    private Point winterhold;
    private Point riften;

    @BeforeEach
    public void setup() {
        skyrim = new Skyrim();

        whiterun = new Point(0, 0);
        morthal = new Point(1, 0);
        markarth = new Point(0, 1);
        winterhold = new Point(1, 1);
        riften = new Point(2, 0);

        Route whiterunToMorthal = new Route(whiterun, morthal, 1, 0);
        Route whiterunToMarkarth = new Route(whiterun, markarth, 3, 1);
        Route morthalToWinterhold = new Route(morthal, winterhold, 2, 2);
        Route markarthToRiften = new Route(markarth, riften, 5, 3);

        skyrim.addVillage(whiterun);
        skyrim.addVillage(morthal);
        skyrim.addVillage(markarth);
        skyrim.addVillage(winterhold);
        skyrim.addVillage(riften);

        skyrim.addRoute(whiterunToMorthal);
        skyrim.addRoute(whiterunToMarkarth);
        skyrim.addRoute(morthalToWinterhold);
        skyrim.addRoute(markarthToRiften);
    }

    @Test
    public void testDijkstra() {
        int[] distancesFromWhiterun = skyrim.dijkstra(whiterun);

        Assertions.assertEquals(0, distancesFromWhiterun[0]); //Whiterun para whiterun = 0
        Assertions.assertEquals(1, distancesFromWhiterun[1]); //Whiterun para Morthal = 1
        Assertions.assertEquals(3, distancesFromWhiterun[2]); //Whiterun para Markarth = 3
        Assertions.assertEquals(3, distancesFromWhiterun[3]); //Whiterun para Winterhold = 1 + 2 = 3
        Assertions.assertEquals(8, distancesFromWhiterun[4]); //Whiterun para Riften = 3 + 5 = 8
    }

    @Test
    public void testRemoveRoute() {
        Route routeToRemove = skyrim.getRoute(whiterun, morthal);

        boolean success = skyrim.removeRoute(routeToRemove);

        Assertions.assertTrue(success);
        Assertions.assertEquals(3, skyrim.getRoutes().size());
    }
}
