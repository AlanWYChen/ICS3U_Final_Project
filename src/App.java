import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class App extends JPanel implements KeyListener, Runnable,  MouseListener {

    // start, game, rule, ranking, win, lose
    public static String state = "start";

    public static Image start;
    public static Image game;

    public static Image pacmanImg;
    public static int pacmanX;
    public static int pacmanY;
    public static int pacmanVelocity;
    public static int pacmanDirection;
    // 0 = up, 1 = right, 2 = down, 3 = left

    // JPanel Settings
    public App() {
        setPreferredSize(new Dimension(1400, 800));
        // Add KeyListener
        this.setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        // Add Thread
        Thread thread = new Thread(this);
        thread.start();
    }

    // 每一帧都跑一次这个函数
    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if ("start".equals(state)) {
            g.drawImage(start, 0, 0, this);
        } else if ("game".equals(state)) {
            g.drawImage(game, 0, 0, this);
        } else if ("rule".equals(state)) {
            
        } else if ("ranking".equals(state)) {
            
        } else if ("win".equals(state)) {
            
        } else if ("lose".equals(state)) {
            
        }
        
    }

    public static void main(String[] args) throws Exception {
        // Image Importation
        game = ImageIO.read(new File("resources/game.png")).getScaledInstance(1400, 800, 0);
        start = ImageIO.read(new File("resources/start.png")).getScaledInstance(1400, 800, 0);



        pacmanImg = ImageIO.read(new File("resources/pacman.png")).getScaledInstance(25, 25, 0);
        pacmanX = 200;
        pacmanY = 200;
        pacmanVelocity = 25;

        JFrame frame = new JFrame("Pacman");
        App panel = new App();
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
    }

    // 控制帧数
    // Thread.sleep(1000) = 1秒1帧
    // Thread.sleep(100) = 1秒10帧
    // Thread Method
    @Override
    public void run(){
        while(true){
            // Setting up Frame Rate
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {}
            // Drawing the Screen
            repaint();
        }
    }

    // Key Listener Methods
    @Override
    public void keyPressed(KeyEvent e) {
        // 按下

        if (e.getKeyChar() == 'a') {
            // 按下A键跑 XXX
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        // 松开

    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {

        if (state.equals("start")) {

            // 341, 564
            // 499, 602
            if (340 <= e.getX() && e.getX() <= 500 &&
                564 <= e.getY() && e.getY() <= 600) {
                state = "game";
            }

        }

        System.out.println(e.getX() + ", " + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}

