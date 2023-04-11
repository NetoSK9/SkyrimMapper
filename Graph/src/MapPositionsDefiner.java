import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MapPositionsDefiner extends JFrame implements MouseListener,ActionListener {

    private ArrayList<Route> routes;
    private ArrayList<Point> points;
    private ArrayList<Button> buttons;
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


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (lastClickedButton == null) {
            lastClickedButton = (Button) source;
            lastClickedButton.setBackground(Color.GREEN);
        } else {
            Point pointStart = lastClickedButton.getLocationOnScreen();
            pointStart.x += lastClickedButton.getWidth() / 2;
            pointStart.y += lastClickedButton.getHeight() / 2;
            Point pointEnd = ((Button) source).getLocationOnScreen();
            pointEnd.x += ((Button) source).getWidth() / 2;
            pointEnd.y += ((Button) source).getHeight() / 2;
            addRouteInMap(pointStart, pointEnd);
            lastClickedButton.setBackground(Color.RED);
            lastClickedButton = null;
            ((Button) source).setBackground(Color.RED);
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
        g.drawLine(route.getOrigin().x, route.getOrigin().y, route.getDestiny().x, route.getDestiny().y);
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
