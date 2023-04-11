import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Skyrim skyrim = new Skyrim();

        Point whiterun = new Point( 0, 0);
        Point morthal = new Point( 1, 0);
        Point markath = new Point( 0, 1);
        Point winterhold = new Point(1, 1);
        Point riften = new Point(2, 0);

        System.out.println("Adicionando vilas e rotas...");
        skyrim.addVillage(whiterun);
        skyrim.addVillage(morthal);
        skyrim.addVillage(markath);
        skyrim.addVillage(winterhold);
        skyrim.addVillage(riften);

        final int WHITERUN_MORTHAL_DISTANCE = 1;
        final int WHITERUN_MARKATH_DISTANCE = 3;
        final int MORTHAL_WINTERHOLD_DISTANCE = 2;
        final int MARKATH_RIFTEN_DISTANCE = 5;

        Route whiterunToMorthal = new Route(whiterun, morthal, WHITERUN_MORTHAL_DISTANCE, 0);
        Route whiterunToMarkath = new Route(whiterun, markath, WHITERUN_MARKATH_DISTANCE, 1);
        Route morthalToWinterhold = new Route(morthal, winterhold, MORTHAL_WINTERHOLD_DISTANCE, 2);
        Route markathToRiften = new Route(markath, riften, MARKATH_RIFTEN_DISTANCE, 3);


        skyrim.addRoute(whiterunToMorthal);
        skyrim.addRoute(whiterunToMarkath);
        skyrim.addRoute(morthalToWinterhold);
        skyrim.addRoute(markathToRiften);
        System.out.println("Vilas e rotas adicionadas com sucesso!");


        int[] distancesFromWhiterun = skyrim.dijkstra(whiterun);
        System.out.println("Distâncias calculadas com sucesso!");

        System.out.println("Distâncias a partir de " + whiterun.getLocation() + ":");
        for (int i = 0; i < distancesFromWhiterun.length; i++) {
            System.out.println("- " + skyrim.getVillage(i).getLocation() + ": " + distancesFromWhiterun[i]);
        }

        MapPositionsDefiner mainMap = new MapPositionsDefiner();

    }
}