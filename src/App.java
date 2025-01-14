import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class App extends JPanel implements KeyListener, Runnable,  MouseListener {

    public static String state = "start";

    public static Image start;
    public static Image game;
    public static Image rule;
    public static Image Ranking;

    public static Image pacmanImg;
    public static int pacmanX;
    public static int pacmanY;
    public static int pacmanVelocity;
    public static int pacmanDirection;

    public App() {
        setPreferredSize(new Dimension(1400, 800));
        // KeyListener
        this.setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        // Thread
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if ("start".equals(state)) {
            g.drawImage(start, 0, 0, this);
        } else if ("game".equals(state)) {
            g.drawImage(game, 0, 0, this);
        } else if ("rule".equals(state)) {
            g.drawImage(rule, 0, 0, this);
        } else if ("Ranking".equals(state)) {
            g.drawImage(Ranking,0,0, this);
        } else if ("win".equals(state)) {
            
        } else if ("lose".equals(state)) {
            
        }
        
    }

    public static void main(String[] args) throws IOException {
        game = ImageIO.read(new File("resources/game.png")).getScaledInstance(1400, 800, 0);
        start = ImageIO.read(new File("resources/start.png")).getScaledInstance(1400, 800, 0);
        rule = ImageIO.read(new File("resources/rule.png")).getScaledInstance(1400, 800, 0);
        Ranking = ImageIO.read(new File("resources/Ranking.png")).getScaledInstance(1400,  800,  0);



        pacmanImg = ImageIO.read(new File("resources/pacman.png")).getScaledInstance(25, 25, 0);
        pacmanX = 200;
        pacmanY = 200;
        pacmanVelocity = 25;

        JFrame frame = new JFrame("Pacman");
        JPanel panel = new App();
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
    }

    @Override
    public void run(){
        while(true){
            // Setting up Frame Rate
            try {
                Thread.sleep(20); //50 frames per second
            }
            catch(InterruptedException e) {}
            // Drawing the Screen
            repaint();
        }
    }
    


    
    public void keyPressed(KeyEvent e) {

    	if (e.getKeyChar() == 'a') {
    		state = "start";
    	}
    	else if (e.getKeyChar() == 'b') {
    		state = "Ranking";
    	}
    	else if (e.getKeyChar() == 'c') {
    		state = "rule";
    	}

    	

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {

        if (state.equals("start")) {

            if (340 <= e.getX() && e.getX() <= 500 &&
                564 <= e.getY() && e.getY() <= 600) {
                state = "game";
            }
            if (591 <= e.getX() && e.getX() <= 776 &&
                552 <= e.getY() && e.getY() <= 616) {
                    state = "rule";
            }
            if (883 <= e.getX() && e.getX() <= 1073 &&
            	553 <= e.getY() && e.getY() <= 617) {
            	state = "Ranking";
            	
            }
                

        }

        else if (state.equals("rule")){

            if (0 <= e.getX() && e.getX() <= 1400 &&
                0 <= e.getY() && e.getY() <= 800) {
                    state = "start";
            }
        }
        
        else if (state.equals("Ranking")){
        	
        	if (0 <= e.getX() && e.getX() <= 1400 &&
                0 <= e.getY() && e.getY() <= 800) {
                   state = "start";
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

