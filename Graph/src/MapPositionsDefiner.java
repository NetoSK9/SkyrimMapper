import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapPositionsDefiner extends JFrame implements MouseListener,ActionListener {

    private ArrayList<Route> routes;
    private ArrayList<Point> points;
    private ArrayList<Button> buttons;
    private int clickCount;
    private Button lastClickedButton = null;
    private BufferedImage imageBG;

    public MapPositionsDefiner() {
        super("Click Listener");
        addMouseListener(this);
        addImgBG("src/img/skyrin_map.png");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        points = new ArrayList<>();
        routes = new ArrayList<>();
        buttons = new ArrayList<>();

    }

    public void addImgBG(String imgPath){
        try {
            imageBG = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Configurar as dimensões da janela
        setSize(imageBG.getWidth(), imageBG.getHeight());

        // Configurar a imagem como componente
        setLayout(new BorderLayout());
        add(new BackgroundComponent(imageBG), BorderLayout.CENTER);

        setVisible(true);
    }

    public void showLowestCostRoute(Point origin, Point destiny){

        Dijkstra dijkistra = new Dijkstra();
        List<Integer> lowestCostRoute =  dijkistra.lowestCostRoute(routes,origin,destiny);

        System.out.println("Tamanho do vetor das rotas: " + lowestCostRoute.size());
        for (int indice : lowestCostRoute ) {
            routes.get(indice).setColor(Color.RED);
        }

        drawAllLines();
    }

    public Point calculateMidpointButton(Button button){
        Point point = button.getLocationOnScreen();
        point.x += lastClickedButton.getWidth() / 2;
        point.y += lastClickedButton.getHeight() / 2;
        return point;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        clickCount++;
        if (lastClickedButton == null) {
            lastClickedButton = (Button) source;
            lastClickedButton.setBackground(Color.GREEN);
        } else {
            if (lastClickedButton!=((Button) source)){
                if (lastClickedButton.getBackground() == Color.GREEN){
                    Point pointStart = calculateMidpointButton(lastClickedButton);
                    Point pointEnd = calculateMidpointButton( ((Button) source) );
                    addRouteInMap(pointStart, pointEnd);
                    lastClickedButton.setBackground(Color.RED);
                    lastClickedButton = null;
                    ((Button) source).setBackground(Color.RED);
                    clickCount=0;
                } else if(lastClickedButton.getBackground() == Color.BLUE){//calcular e mostrar menor rota

                    //Seleciona os pontos de origem e destino.
                    Point origin = calculateMidpointButton(lastClickedButton);
                    Point destiny = calculateMidpointButton( ((Button) source) );

                    System.out.println("Vai mostrar as linhas vermelhas");
                    showLowestCostRoute(origin,destiny);

                    lastClickedButton.setBackground(Color.RED);
                    lastClickedButton = null;
                    ((Button) source).setBackground(Color.RED);
                    clickCount=0;
                } else if(lastClickedButton.getBackground() == Color.RED){
                    setAllLinesToBlack();
                    drawAllLines();
                    lastClickedButton.setBackground(Color.RED);
                    lastClickedButton = null;
                    ((Button) source).setBackground(Color.RED);
                    clickCount = 0;
                }
            }else{
                if (clickCount == 1) {
                    lastClickedButton.setBackground(Color.GREEN);
                } else if (clickCount == 2) {
                    lastClickedButton.setBackground(Color.BLUE);
                } else {
                    lastClickedButton.setBackground(Color.RED);
                    clickCount=0;
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Point village = new Point( x, y);
        System.out.println("Clicked at (" + village.x + ", " + village.y + ")");
        points.add(village);
        addPointButtonRed(x,y);
    }

    public void addPointButtonRed(int positionX, int positionY){
        Button button = new Button();
        button.setVisible(true);
        button.setLocation(positionX-10,positionY-30);
        button.setSize(10,10);
        button.setBackground(Color.RED);
        button.addActionListener(this);

        buttons.add(button);
        this.add(button);
    }

    private void addRouteInMap(Point pointStart,Point  pointEnd){
        Route route = new Route(pointStart, pointEnd,routes.size()-1 );
        routes.add(route);
        drawLine(routes.get(routes.size()-1));
        System.out.println("Value of New Route (" + routes.get(routes.size()-1).getWeight() + ")");
    }

    private void drawLine(Route route) {
        Graphics g = getGraphics();
        g.setColor(route.getColor());

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(route.getOrigin().x, route.getOrigin().y, route.getDestiny().x, route.getDestiny().y);
        g2.dispose();
    }

    private void selectLineByPoints(Point startPoint, Point endPoint){
        for (Route route : routes) {
            if( (startPoint.getLocation()==route.getOrigin().getLocation()) && (endPoint.getLocation()==route.getDestiny().getLocation()) ){
                System.out.println("Achou!!!!! A rota: "+ route.getWeight());
                route.setColor(Color.RED);
            }
        }
    }

    private void drawAllLines(){
        for (Route route : routes) {
            drawLine(route);
        }

    }
    private void setAllLinesToBlack(){
        for (Route route : routes) {
            route.setColor(Color.BLACK);
        }

    }


    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}


    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<Button> buttons) {
        this.buttons = buttons;
    }

    public Button getLastClickedButton() {
        return lastClickedButton;
    }

    public void setLastClickedButton(Button lastClickedButton) {
        this.lastClickedButton = lastClickedButton;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    private class BackgroundComponent extends Component {

        private BufferedImage image;

        public BackgroundComponent(BufferedImage image) {
            this.image = image;
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(image.getWidth(), image.getHeight());
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(image, 0, 0, null);
        }

    }
}
