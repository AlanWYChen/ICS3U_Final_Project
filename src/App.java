import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class App extends JPanel implements KeyListener, Runnable {

    public static class Entity {
        public BufferedImage img;
        public int x = 0;
        public int y = 0;
        public int velocity = 0;
        // 0 = up, 1 = right, 2 = down, 3 = left
        public int direction = 0;
    }

    public static class Interactable {
        public BufferedImage img;
        public int x = 0;
        public int y = 0;
        public int type = 0;
    }

    public static Entity pacman;
    public static Entity[] ghosts = new Entity[4];

    // JPanel Settings
    public App() {
        setPreferredSize(new Dimension(800, 400));
        // Add KeyListener
        this.setFocusable(true);
        addKeyListener(this);
        // Add Thread
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        pacman.x += pacman.velocity;

        g.drawImage(pacman.img, pacman.x, pacman.y, null);
    }

    public static void main(String[] args) throws Exception {
        // Image Importation
        App.pacman = new Entity();
        pacman.img = ImageIO.read(new File("resources/pacman.png"));
        pacman.x = 100;
        pacman.y = 100;
        pacman.velocity = 1;
        

        JFrame frame = new JFrame("Pacman");
        App panel = new App();
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
    }

    // Thread Method
    @Override
    public void run(){
        while(true){
            // Setting up Frame Rate
            try {
                Thread.sleep(20);
            }
            catch(InterruptedException e) {}
            // Drawing the Screen
            repaint();
        }
    }

    // Key Listener Methods
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}

