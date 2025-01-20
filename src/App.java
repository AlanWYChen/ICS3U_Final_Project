import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class App extends JPanel implements KeyListener, Runnable, MouseListener {

    public static String state = "start";

    // top left = [100, 80] [155, 80]
    // distance between two squares = 55
    public char[][] mapArray = { {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                                 {' ',' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w', 'w'}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w'}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w', 'w'}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w', ' '}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w', ' '}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w', 'w'}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w', 'w'}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w', ' '},
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w', ' '}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w', 'w'}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
                                 {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '} };

    public static Image start;
    public static Image game;
    public static Image rule;
    public static Image ranking;
    public static Image win;
    public static Image lose;

    public static Image pacmanImg;
    public static Image pacmanOpenMouthImg;
    public static int pacmanX;
    public static int pacmanY;
    public static int pacmanGridX;
    public static int pacmanGridY;
    public static double pacmanVelocity;
    // 0 = up, 1 = right, 2 = down, 3 = left
    public static int pacmanDirection;
    public static boolean openMouth;

    public App() {
        setPreferredSize(new Dimension(1400, 875));
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

            // game logic
            if (pacmanDirection == 0) {
                pacmanY -= pacmanVelocity;
                pacmanGridY -= 1;
                if (mapArray[pacmanGridX][pacmanGridY] == 'w') {
                    pacmanY += pacmanVelocity;
                    pacmanGridY += 1;
                }
            } else if (pacmanDirection == 1) {
                pacmanX += pacmanVelocity;
                pacmanGridX += 1;
                if (mapArray[pacmanGridX][pacmanGridY] == 'w') {
                    pacmanX -= pacmanVelocity;
                    pacmanGridX -= 1;
                }
            } else if (pacmanDirection == 2) {
                pacmanY += pacmanVelocity;
                pacmanGridY += 1;
                if (mapArray[pacmanGridX][pacmanGridY] == 'w') {
                    pacmanY -= pacmanVelocity;
                    pacmanGridY -= 1;
                }
            } else if (pacmanDirection == 3) {
                pacmanX -= pacmanVelocity;
                pacmanGridX -= 1;
                if (mapArray[pacmanGridX][pacmanGridY] == 'w') {
                    pacmanX += pacmanVelocity;
                    pacmanGridX += 1;
                }
            }

            if (openMouth) {
                g.drawImage(pacmanOpenMouthImg, pacmanX, pacmanY, this);
            } else {
                g.drawImage(pacmanImg, pacmanX, pacmanY, this);
            }

            openMouth = !openMouth;

        } else if ("rule".equals(state)) {
            g.drawImage(rule, 0, 0, this);
        } else if ("ranking".equals(state)) {
            g.drawImage(ranking,0,0, this);
        } else if ("win".equals(state)) {
        	 g.drawImage(win,0,0, this);
        } else if ("lose".equals(state)) {
        	 g.drawImage(lose,0,0, this);
        }
        
    }

    public static void main(String[] args) throws IOException {
        game = ImageIO.read(new File("resources/background.png")).getScaledInstance(1400, 875, 0);
        start = ImageIO.read(new File("resources/start.png")).getScaledInstance(1400, 875, 0);
        rule = ImageIO.read(new File("resources/rule.png")).getScaledInstance(1400, 875, 0);
        ranking = ImageIO.read(new File("resources/ranking.png")).getScaledInstance(1400, 875, 0);
        win = ImageIO.read(new File("resources/win.png")).getScaledInstance(1400, 875, 0);
        lose = ImageIO.read(new File("resources/lose.png")).getScaledInstance(1400, 875, 0);

        openMouth = false;
        pacmanOpenMouthImg = ImageIO.read(new File("resources/pacman_open_mouth.png")).getScaledInstance(25, 25, 0);
        pacmanImg = ImageIO.read(new File("resources/pacman.png")).getScaledInstance(25, 25, 0);
        pacmanX = 90;
        pacmanY = 70;
        pacmanGridX = 0;
        pacmanGridY = 0;
        pacmanVelocity = 55;
        pacmanDirection = 1;

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
                Thread.sleep(1000); // 1 frames per second
            }
            catch(InterruptedException e) {}
            // Drawing the Screen
            repaint();
        }
    }
    


    
    @Override
    public void keyPressed(KeyEvent e) {
    	System.out.println(state);
    	if(state.equals("start")) {
    		if (e.getKeyChar() == 'a') {
    			state = "rule";
    		}
    		else if(e.getKeyChar() == 'b') {
    			state = "ranking";
    		}
    	} else if(state.equals("game")) {

            if (e.getKeyChar() == 'w') {
                pacmanDirection = 0;
            } else if (e.getKeyChar() == 'a') {
                pacmanDirection = 3;
            } else if (e.getKeyChar() == 's') {
                pacmanDirection = 2;
            } else if (e.getKeyChar() == 'd') {
                pacmanDirection = 1;
            } else if (e.getKeyChar() == '-') {
    			state = "win";
    		}
    		else if (e.getKeyChar() == '=') {
        		state = "lose";
        	}
    	}

    	

    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {

        if (state.equals("start")) {

            
            if (340 <= e.getX() && e.getX() <= 510 &&
                610 <= e.getY() && e.getY() <= 660) {
                state = "game";
            }
            if (600 <= e.getX() && e.getX() <= 770 &&
                610 <= e.getY() && e.getY() <= 660) {
                    state = "rule";
            }
            if (800 <= e.getX() && e.getX() <= 1070 &&
            	600 <= e.getY() && e.getY() <= 660) {
            	state = "ranking";
            	
            }                

        }

        else if (state.equals("rule")){

            if (0 <= e.getX() && e.getX() <= 1400 &&
                0 <= e.getY() && e.getY() <= 800) {
                    state = "start";
            }
        }
        
        else if (state.equals("ranking")){
        	
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

