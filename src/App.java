import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class App extends JPanel implements KeyListener, Runnable, MouseListener {

    public static String state = "start";

    // top left = [100, 80] [155, 80]
    // 25 x 16
    // distance between two squares = 55
    public static char[][] mapArray = 
        { 
            {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'}, 
            {'w', ' ', ' ', ' ', 'w', ' ', 'w', ' ', 'w', 'w', 'w', 'w', ' ', 'w', 'w', 'w', 'w', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w'}, 
            {'w', ' ', 'w', ' ', 'w', ' ', ' ', ' ', 'w', 'w', 'w', 'w', ' ', 'w', 'w', 'w', 'w', ' ', 'w', ' ', 'w', 'w', 'w', ' ', 'w'},  
            {'w', ' ', 'w', ' ', ' ', ' ', 'w', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w', ' ', 'w', 'w', 'w', ' ', 'w'}, 
            {'w', ' ', 'w', ' ', 'w', 'w', 'w', 'w', 'w', ' ', 'w', ' ', 'w', 'w', ' ', 'w', 'w', ' ', 'w', ' ', 'w', 'w', 'w', ' ', 'w'}, 
            {'w', ' ', 'w', ' ', ' ', ' ', ' ', ' ', 'w', ' ', 'w', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w', ' ', 'w', 'w', 'w', ' ', 'w'}, 
            {'w', ' ', 'w', 'w', 'w', 'w', 'w', ' ', 'w', ' ', 'w', 'w', 'w', 'w', 'w', ' ', 'w', ' ', 'w', ' ', ' ', ' ', ' ', ' ', 'w'}, 
            {'w', ' ', 'w', ' ', ' ', ' ', ' ', ' ', 'w', ' ', 'w', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w', 'w', 'w', ' ', 'w'}, 
            {'w', ' ', 'w', ' ', 'w', 'w', 'w', ' ', 'w', ' ', 'w', ' ', 'w', 'w', 'w', 'w', 'w', ' ', 'w', ' ', 'w', 'w', 'w', ' ', 'w'},
            {'w', ' ', ' ', ' ', 'w', 'w', 'w', ' ', ' ', ' ', ' ', ' ', ' ', 'w', 'w', 'w', ' ', ' ', 'w', ' ', 'w', 'w', ' ', ' ', 'w'}, 
            {'w', ' ', 'w', 'w', 'w', 'w', 'w', ' ', 'w', ' ', 'w', 'w', ' ', ' ', 'w', ' ', ' ', ' ', 'w', ' ', 'w', 'w', 'w', ' ', 'w'}, 
            {'w', ' ', 'w', ' ', 'w', 'w', 'w', ' ', 'w', ' ', 'w', 'w', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w', 'w', 'w', ' ', 'w'}, 
            {'w', ' ', 'w', ' ', 'w', 'w', 'w', ' ', ' ', ' ', ' ', ' ', ' ', 'w', 'w', 'w', 'w', ' ', 'w', ' ', ' ', ' ', ' ', ' ', 'w'},
            {'w', ' ', 'w', ' ', ' ', ' ', ' ', ' ', 'w', 'w', 'w', 'w', ' ', 'w', 'w', 'w', 'w', ' ', 'w', 'w', 'w', 'w', 'w', ' ', 'w'}, 
            {'w', ' ', ' ', ' ', 'w', 'w', 'w', ' ', ' ', ' ', ' ', ' ', ' ', 'w', 'w', 'w', 'w', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'w'}, 
            {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'} 
        };

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

    public static Image ghost1Img;
    public static int ghost1X;
    public static int ghost1Y;
    public static int ghost1GridX;
    public static int ghost1GridY;
    public static double ghost1Velocity;
    // 0 = up, 1 = right, 2 = down, 3 = left
    public static int ghost1Direction;

    public static Image ghost2Img;
    public static int ghost2X;
    public static int ghost2Y;
    public static int ghost2GridX;
    public static int ghost2GridY;
    public static double ghost2Velocity;
    // 0 = up, 1 = right, 2 = down, 3 = left
    public static int ghost2Direction;

    public static Image ghost3Img;
    public static int ghost3X;
    public static int ghost3Y;
    public static int ghost3GridX;
    public static int ghost3GridY;
    public static double ghost3Velocity;
    // 0 = up, 1 = right, 2 = down, 3 = left
    public static int ghost3Direction;

    public static Image fruitImg;
    public static int fruitX;
    public static int fruitY;
    public static int fruitGridX;
    public static int fruitGridY;
    public static int num = 0;

    public static int frameCount = 0;

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

            g.drawImage(fruitImg, fruitX, fruitY, this);

            if (openMouth) {
                g.drawImage(pacmanOpenMouthImg, pacmanX, pacmanY, this);
            } else {
                g.drawImage(pacmanImg, pacmanX, pacmanY, this);
            }

            g.drawImage(ghost1Img, ghost1X, ghost1Y, this);
            g.drawImage(ghost2Img, ghost2X, ghost2Y, this);
            g.drawImage(ghost3Img, ghost3X, ghost3Y, this);


        } else if ("rule".equals(state)) {
            g.drawImage(rule, 0, 0, this);
        } else if ("ranking".equals(state)) {
            g.drawImage(ranking,0,0, this);
            g.setColor(new Color(0, 0, 0));
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            ArrayList<Double> leaderboard = top5Leaderboard();

            if (leaderboard.size() > 0) {
                g.drawString(Double.toString(leaderboard.get(0)), 600, 300);
            }
            if (leaderboard.size() > 1) {
                g.drawString(Double.toString(leaderboard.get(1)), 600, 390);
            }
            if (leaderboard.size() > 2) {
                g.drawString(Double.toString(leaderboard.get(2)), 600, 480);
            }
            if (leaderboard.size() > 3) {
                g.drawString(Double.toString(leaderboard.get(3)), 600, 570);
            }
            if (leaderboard.size() > 4) {
                g.drawString(Double.toString(leaderboard.get(4)), 600, 660);
            }
            g.setColor(new Color(0, 0, 0));
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
        pacmanX = 80;
        pacmanY = 70;
        pacmanGridX = 1;
        pacmanGridY = 1;
        pacmanVelocity = 55;
        pacmanDirection = 1;

        ghost1Img = ImageIO.read(new File("resources/ghost1.png")).getScaledInstance(25, 25, 0);
        ghost1GridX = 23;
        ghost1GridY = 1;
        ghost1X = 80 + ((ghost1GridX - 1) * 55);
        ghost1Y = 70 + ((ghost1GridY - 1) * 55);
        ghost1Velocity = 55;
        ghost1Direction = 1;

        ghost2Img = ImageIO.read(new File("resources/ghost2.png")).getScaledInstance(25, 25, 0);
        ghost2GridX = 23;
        ghost2GridY = 14;
        ghost2X = 80 + ((ghost2GridX - 1) * 55);
        ghost2Y = 70 + ((ghost2GridY - 1) * 55);
        ghost2Velocity = 55;
        ghost2Direction = 1;

        ghost3Img = ImageIO.read(new File("resources/ghost3.png")).getScaledInstance(25, 25, 0);
        ghost3GridX = 1;
        ghost3GridY = 14;
        ghost3X = 80 + ((ghost3GridX - 1) * 55);
        ghost3Y = 70 + ((ghost3GridY - 1) * 55);
        ghost3Velocity = 55;
        ghost3Direction = 1;

        fruitImg = ImageIO.read(new File("resources/coconut.png")).getScaledInstance(25, 25, 0);
        fruitGridX = 1;
        fruitGridY = 3;
        fruitX = 80 + ((fruitGridX - 1) * 55);
        fruitY = 70 + ((fruitGridY - 1) * 55);

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
                
                if (state == "game") {

                    if (pacmanGridX > ghost1GridX && mapArray[ghost1GridY][ghost1GridX + 1] != 'w') {
                        ghost1Direction = 1;
                    } else if (pacmanGridX < ghost1GridX && mapArray[ghost1GridY][ghost1GridX - 1] != 'w') {
                        ghost1Direction = 3;
                    } else if (pacmanGridY < ghost1GridY && mapArray[ghost1GridY - 1][ghost1GridX] != 'w') {
                        ghost1Direction = 0;
                    } else {
                        ghost1Direction = 2;
                    }

                    if (pacmanGridX > ghost2GridX && mapArray[ghost2GridY][ghost2GridX + 1] != 'w') {
                        ghost2Direction = 1;
                    } else if (pacmanGridX < ghost2GridX && mapArray[ghost2GridY][ghost2GridX - 1] != 'w') {
                        ghost2Direction = 3;
                    } else if (pacmanGridY < ghost2GridY && mapArray[ghost2GridY - 1][ghost2GridX] != 'w') {
                        ghost2Direction = 0;
                    } else {
                        ghost2Direction = 2;
                    }

                    if (pacmanGridX > ghost3GridX && mapArray[ghost3GridY][ghost3GridX + 1] != 'w') {
                        ghost3Direction = 1;
                    } else if (pacmanGridX < ghost3GridX && mapArray[ghost3GridY][ghost3GridX - 1] != 'w') {
                        ghost3Direction = 3;
                    } else if (pacmanGridY < ghost3GridY && mapArray[ghost3GridY - 1][ghost3GridX] != 'w') {
                        ghost3Direction = 0;
                    } else {
                        ghost3Direction = 2;
                    }

                    // game logic
                    if (pacmanDirection == 0) {
                        pacmanY -= pacmanVelocity;
                        pacmanGridY -= 1;
                        if (mapArray[pacmanGridY][pacmanGridX] == 'w') {
                            pacmanY += pacmanVelocity;
                            pacmanGridY += 1;
                        }
                    } else if (pacmanDirection == 1) {
                        pacmanGridX += 1;
                        pacmanX += pacmanVelocity;

                        if (mapArray[pacmanGridY][pacmanGridX] == 'w') {
                            pacmanGridX -= 1;
                            pacmanX -= pacmanVelocity;
                        }
                    } else if (pacmanDirection == 2) {
                        pacmanGridY += 1;
                        pacmanY += pacmanVelocity;
                        if (mapArray[pacmanGridY][pacmanGridX] == 'w') {
                            pacmanY -= pacmanVelocity;
                            pacmanGridY -= 1;
                        }
                    } else if (pacmanDirection == 3) {
                        pacmanGridX -= 1;
                        pacmanX -= pacmanVelocity;
                        if (mapArray[pacmanGridY][pacmanGridX] == 'w') {
                            pacmanX += pacmanVelocity;
                            pacmanGridX += 1;
                        }
                    }

                    if (ghost1Direction == 0) {
                        ghost1GridY -= 1;
                        ghost1Y -= ghost1Velocity;
                        if (mapArray[ghost1GridY][ghost1GridX] == 'w') {
                            ghost1Y += ghost1Velocity;
                            ghost1GridY += 1;
                        }
                    } else if (ghost1Direction == 1) {
                        ghost1GridX += 1;
                        ghost1X += ghost1Velocity;
                        if (mapArray[ghost1GridY][ghost1GridX] == 'w') {
                            ghost1X -= ghost1Velocity;
                            ghost1GridX -= 1;
                        }
                    } else if (ghost1Direction == 2) {
                        ghost1GridY += 1;
                        ghost1Y += ghost1Velocity;
                        if (mapArray[ghost1GridY][ghost1GridX] == 'w') {
                            ghost1Y -= ghost1Velocity;
                            ghost1GridY -= 1;
                        }
                    } else if (ghost1Direction == 3) {
                        ghost1GridX -= 1;
                        ghost1X -= ghost1Velocity;
                        if (mapArray[ghost1GridY][ghost1GridX] == 'w') {
                            ghost1X += ghost1Velocity;
                            ghost1GridX += 1;
                        }
                    }

                    if (ghost2Direction == 0) {
                        ghost2GridY -= 1;
                        ghost2Y -= ghost2Velocity;
                        if (mapArray[ghost2GridY][ghost2GridX] == 'w') {
                            ghost2Y += ghost2Velocity;
                            ghost2GridY += 1;
                        }
                    } else if (ghost2Direction == 1) {
                        ghost2GridX += 1;
                        ghost2X += ghost2Velocity;
                        if (mapArray[ghost2GridY][ghost2GridX] == 'w') {
                            ghost2X -= ghost2Velocity;
                            ghost2GridX -= 1;
                        }
                    } else if (ghost2Direction == 2) {
                        ghost2GridY += 1;
                        ghost2Y += ghost2Velocity;
                        if (mapArray[ghost2GridY][ghost2GridX] == 'w') {
                            ghost2Y -= ghost2Velocity;
                            ghost2GridY -= 1;
                        }
                    } else if (ghost2Direction == 3) {
                        ghost2GridX -= 1;
                        ghost2X -= ghost2Velocity;
                        if (mapArray[ghost2GridY][ghost2GridX] == 'w') {
                            ghost2X += ghost2Velocity;
                            ghost2GridX += 1;
                        }
                    }

                    if (ghost3Direction == 0) {
                        ghost3GridY -= 1;
                        ghost3Y -= ghost3Velocity;
                        if (mapArray[ghost3GridY][ghost3GridX] == 'w') {
                            ghost3Y += ghost3Velocity;
                            ghost3GridY += 1;
                        }
                    } else if (ghost3Direction == 1) {
                        ghost3GridX += 1;
                        ghost3X += ghost3Velocity;
                        if (mapArray[ghost3GridY][ghost3GridX] == 'w') {
                            ghost3X -= ghost3Velocity;
                            ghost3GridX -= 1;
                        }
                    } else if (ghost3Direction == 2) {
                        ghost3GridY += 1;
                        ghost3Y += ghost3Velocity;
                        if (mapArray[ghost3GridY][ghost3GridX] == 'w') {
                            ghost3Y -= ghost3Velocity;
                            ghost3GridY -= 1;
                        }
                    } else if (ghost3Direction == 3) {
                        ghost3GridX -= 1;
                        ghost3X -= ghost3Velocity;
                        if (mapArray[ghost3GridY][ghost3GridX] == 'w') {
                            ghost3X += ghost3Velocity;
                            ghost3GridX += 1;
                        }
                    }

                    if (pacmanGridX == ghost1GridX && pacmanGridY == ghost1GridY) {
                        state = "lose";                    
                    }

                    if (pacmanGridX == ghost2GridX && pacmanGridY == ghost2GridY) {
                        state = "lose";                    
                    }

                    if (pacmanGridX == ghost3GridX && pacmanGridY == ghost3GridY) {
                        state = "lose";                    
                    }

                    if (pacmanGridX == fruitGridX && pacmanGridY == fruitGridY) {
                        spawnNewFruit();
                    }

                    openMouth = !openMouth;

                    if(num == 1) {
                        state = "win";
                        num = 0;
                        pacmanGridX = 1;
                        pacmanGridY = 1;
                        ghost1GridX = 23;
                        ghost1GridY = 1;
                        ghost3GridX = 1;
                        ghost3GridY = 14;
                        ghost2GridX = 23;
                        ghost2GridY = 14;
                        try {
                            writeScoreToLeaderboard();
                        } catch (IOException ex) {}
                        frameCount = 0;
                    }

                    frameCount++;

                }

                Thread.sleep(500);
            }
            catch(InterruptedException e) {}
            // Drawing the Screen
            repaint();
        }
    }
    


    
    @Override
    public void keyPressed(KeyEvent e) {
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
        else if(state.equals("win")){
            if(e.getKeyChar() == 'l'){
                state = "start";
            }
        }
        else if(state.equals("lose")){
            if(e.getKeyChar() == 'l'){
                state = "start";
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

    // return GridX,GridY
    public static void spawnNewFruit() {

        while (true) {
            int x = (int) (Math.random() * 25); // 0 - 24
            int y = (int) (Math.random() * 16); // 0 - 15

            if (mapArray[y][x] != 'w') {
                fruitGridX = x;
                fruitGridY = y;
                fruitX = 80 + ((fruitGridX - 1) * 55);
                fruitY = 70 + ((fruitGridY - 1) * 55);
                num += 1;
                break;
            }
        }

    }

    public static void writeScoreToLeaderboard() throws IOException {

        PrintWriter pw = new PrintWriter(new FileWriter("leaderboard.csv", true));

        pw.println(100 / ((double) Math.sqrt(frameCount)));

        pw.close();

    }

    public static ArrayList<Double> top5Leaderboard() {
        ArrayList<Double> values = new ArrayList<>();
        String csvFile = "leaderboard.csv"; 
        String line;
        ArrayList<Double> topfive = new ArrayList<>();

        try {
            
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        
        while ((line = br.readLine()) != null) {
            // Assuming the CSV has one double per line
            try {
                double value = Double.parseDouble(line.trim());
                values.add(value);
            } catch (NumberFormatException e) {}
        }

        // Sort in descending order
        values.sort(Collections.reverseOrder());

        for (int i = 0; i < Math.min(5, values.size()); i++) {
            topfive.add(values.get(i));
        }
        } catch (Exception e) {}

        return topfive;

    }
    

}

