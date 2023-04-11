public class Main {
    public static void main(String[] args) {
        Skyrim skyrim = new Skyrim();

        Village whiterun = new Village("Whiterun", 1, 0, 0);
        Village morthal = new Village("Morthal", 2, 1, 0);
        Village markath = new Village("Markath", 3, 0, 1);
        Village winterhold = new Village("Winterhold", 4, 1, 1);
        Village riften = new Village("Riften", 5, 2, 0);

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

        System.out.println("Calculando as distâncias a partir de " + whiterun.getName() + "...");
        int[] distancesFromWhiterun = skyrim.dijkstra(whiterun);
        System.out.println("Distâncias calculadas com sucesso!");

        System.out.println("Distâncias a partir de " + whiterun.getName() + ":");
        for (int i = 0; i < distancesFromWhiterun.length; i++) {
            System.out.println("- " + skyrim.getVillage(i).getName() + ": " + distancesFromWhiterun[i]);
        }


        System.out.println("O print de whiterun no mapa é: " + whiterun.toString());

    }
}