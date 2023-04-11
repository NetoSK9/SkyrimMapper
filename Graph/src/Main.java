public class Main {
    public static void main(String[] args) {
        Skyrim s = new Skyrim();

        Village v1 = new Village(1, 0, 0);
        Village v2 = new Village(2, 1, 0);
        Village v3 = new Village(3, 0, 1);
        Village v4 = new Village(4, 1, 1);
        Village v5 = new Village(5, 2, 0);

        s.addVillage(v1);
        s.addVillage(v2);
        s.addVillage(v3);
        s.addVillage(v4);
        s.addVillage(v5);

        s.addRoute(new Route(v1, v2, 1));
        s.addRoute(new Route(v1, v3, 3));
        s.addRoute(new Route(v2, v4, 2));
        s.addRoute(new Route(v3, v5, 5));

        MapPositionsDefiner mainMap = new MapPositionsDefiner();

    }
}