import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class App extends JPanel implements KeyListener, Runnable {

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
        // Add Thread
        Thread thread = new Thread(this);
        thread.start();
    }

    // 每一帧都跑一次这个函数
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        pacmanX += pacmanVelocity;
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, 1400, 800);
        g.drawImage(pacmanImg, pacmanX, pacmanY, null);
    }

    public static void main(String[] args) throws Exception {
        // Image Importation
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
}

