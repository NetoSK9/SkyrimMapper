import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MapPositionsDefiner extends JFrame implements MouseListener,ActionListener {

    private ArrayList<Village> villages;
    private ArrayList<Route> routes;
    private BufferedImage imageBG;
    private ArrayList<Button> buttons;
    private Button lastClickedButton = null;

    public MapPositionsDefiner() {
        super("Click Listener");
        addMouseListener(this);
        addImgBG("src/img/skyrin_map.png");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        villages = new ArrayList<>();
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
            Point p1 = lastClickedButton.getLocationOnScreen();
            p1.x += lastClickedButton.getWidth() / 2;
            p1.y += lastClickedButton.getHeight() / 2;
            Point p2 = ((Button) source).getLocationOnScreen();
            p2.x += ((Button) source).getWidth() / 2;
            p2.y += ((Button) source).getHeight() / 2;
            addRouteInMap(p1.x, p1.y, p2.x, p2.y);
            lastClickedButton.setBackground(Color.RED);
            lastClickedButton = null;
            ((Button) source).setBackground(Color.RED);
        }
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Village village = new Village("Teste",villages.size(), x, y);
        System.out.println("Clicked at (" + village.getX() + ", " + village.getY() + ")");
        villages.add(village);
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

    private void addRouteInMap(int village1X,int  village1Y, int village2X, int village2Y){
        Graphics g = getGraphics();
        g.drawLine(village1X, village1Y, village2X, village2Y);
    }

    private void drawLine(Village village1, Village village2) {
        Graphics g = getGraphics();
        g.drawLine(village1.getX(), village1.getY(), village2.getX(), village2.getY());
    }


    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}


    public ArrayList<Village> getVillages() {
        return villages;
    }

    public void setVillages(ArrayList<Village> villages) {
        this.villages = villages;
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
