import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class App extends JPanel implements KeyListener, Runnable, MouseListener {

    public static String state = "start";

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

    // state: backgrounds
    public static BufferedImage start;
    public static BufferedImage game;
    public static BufferedImage rule;
    public static BufferedImage ranking;
    public static BufferedImage win;
    public static BufferedImage lose;
    
    public static BufferedImage pacmanImg;
    public static BufferedImage pacmanOpenMouthImg;
    public static int pacmanX;
    public static int pacmanY;
    public static int pacmanGridX() {

        // <**
        if (pacmanX < 70) {
            return 0;
        } else if (pacmanX > 1300) {
            return 24;
        }
        return ((int) ((((double) pacmanX - 80) / 56) + 0.5)) + 1;
        // **>
    }
    public static int pacmanGridY() {

        // <**
        if (pacmanY < 60) {
            return 0;
        } else if (pacmanY > 795) {
            return 15;
        }

        return ((int) ((((double) pacmanY - 70) / 54) + 0.5)) + 1;
        // **>
    }
    public static double pacmanVelocity;
    public static int pacmanDirection;
    public static boolean openMouth;

    public static BufferedImage ghost1Img;
    public static int ghost1X;
    public static int ghost1Y;
    public static int ghost1GridX() {
        if (ghost1X < 70) {
            return 0;
        } else if (ghost1X > 1300) {
            return 24;
        }
        return ((int) ((((double) ghost1X - 80) / 56) + 0.5)) + 1;
         // round like normal
    }
    public static int ghost1GridY() {
        if (ghost1Y < 60) {
            return 0;
        } else if (ghost1Y > 795) {
            return 15;
        }

        return ((int) ((((double) ghost1Y - 70) / 54) + 0.5)) + 1;
        
        // round like normal
    }
    public static double ghost1Velocity;
    public static int ghost1Direction;

    public static BufferedImage ghost2Img;
    public static int ghost2X;
    public static int ghost2Y;
    public static int ghost2GridX() {
        if (ghost2X < 70) {
            return 0;
        } else if (ghost2X > 1300) {
            return 24;
        }
        return ((int) ((((double) ghost2X - 80) / 56) + 0.5)) + 1;
        
        // round like normal
    }
    public static int ghost2GridY() {
        if (ghost2Y < 60) {
            return 0;
        } else if (ghost2Y > 795) {
            return 15;
        }

        return ((int) ((((double) ghost2Y - 70) / 54) + 0.5)) + 1;
        
        // round like normal
    }
    public static double ghost2Velocity;
    public static int ghost2Direction;

    public static BufferedImage ghost3Img;
    public static int ghost3X;
    public static int ghost3Y;
    public static int ghost3GridX() {
        if (ghost3X < 70) {
            return 0;
        } else if (ghost3X > 1300) {
            return 24;
        }
        return ((int) ((((double) ghost3X - 80) / 56) + 0.5)) + 1;
        
        // round like normal
    }
    public static int ghost3GridY() {
        if (ghost3Y < 60) {
            return 0;
        } else if (ghost3Y > 795) {
            return 15;
        }

        return ((int) ((((double) ghost3Y - 70) / 54) + 0.5)) + 1;
        
        // round like normal
    }
    public static double ghost3Velocity;
    public static int ghost3Direction;

    public static BufferedImage fruitImg;
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if ("start".equals(state)) {
            g.drawImage(start, 0, 0, this);
        } else if ("game".equals(state)) {

            System.out.printf("(%d, %d)%n", pacmanX, pacmanY);
            System.out.printf("(%d, %d)%n", pacmanGridX(), pacmanGridY());

            if (pacmanGridX() > ghost1GridX() && mapArray[ghost1GridY()][ghost1GridX() + 1] != 'w') {
                ghost1Direction = 1;
            } else if (pacmanGridX() < ghost1GridX() && mapArray[ghost1GridY()][ghost1GridX() - 1] != 'w') {
                ghost1Direction = 3;
            } else if (pacmanGridY() < ghost1GridY() && mapArray[ghost1GridY() - 1][ghost1GridX()] != 'w') {
                ghost1Direction = 0;
            } else {
                ghost1Direction = 2;
            }
            
            if (pacmanGridX() > ghost2GridX() && mapArray[ghost2GridY()][ghost2GridX() + 1] != 'w') {
                ghost2Direction = 1;
            } else if (pacmanGridX() < ghost2GridX() && mapArray[ghost2GridY()][ghost2GridX() - 1] != 'w') {
                ghost2Direction = 3;
            } else if (pacmanGridY() < ghost2GridY() && mapArray[ghost2GridY() - 1][ghost2GridX()] != 'w') {
                ghost2Direction = 0;
            } else {
                ghost2Direction = 2;
            }

            if (pacmanGridX() > ghost3GridX() && mapArray[ghost3GridY()][ghost3GridX() + 1] != 'w') {
                ghost3Direction = 1;
            } else if (pacmanGridX() < ghost3GridX() && mapArray[ghost3GridY()][ghost3GridX() - 1] != 'w') {
                ghost3Direction = 3;
            } else if (pacmanGridY() < ghost3GridY() && mapArray[ghost3GridY() - 1][ghost3GridX()] != 'w') {
                ghost3Direction = 0;
            } else {
                ghost3Direction = 2;
            }

            if (pacmanDirection == 0) {
                pacmanY -= pacmanVelocity;

                // <**
                if ((pacmanX - 80) % 56 != 0) {
                    pacmanX = (pacmanGridX() - 1) * 56 + 80;
                }

                if (mapArray[pacmanGridY()][pacmanGridX()] == 'w') {
                    pacmanY += 25;
                }
                // **>

            } else if (pacmanDirection == 1) {
                pacmanX += pacmanVelocity;

                // <**
                if ((pacmanY - 70) % 54 != 0) {
                    pacmanY = (pacmanGridY() - 1) * 54 + 70;
                }

                if (mapArray[pacmanGridY()][pacmanGridX()] == 'w') {
                    pacmanX -= 25;
                }
                // **>

            } else if (pacmanDirection == 2) {
                pacmanY += pacmanVelocity;

                // <** 
                if ((pacmanX - 80) % 56 != 0) {
                    pacmanX = (pacmanGridX() - 1) * 56 + 80;
                }

                if (mapArray[pacmanGridY()][pacmanGridX()] == 'w') {
                    pacmanY -= 25;
                }
                // **>

            } else if (pacmanDirection == 3) {
                pacmanX -= pacmanVelocity;

                // <**
                if ((pacmanY - 70) % 54 != 0) {
                    pacmanY = (pacmanGridY() - 1) * 54 + 70;
                }

                if (mapArray[pacmanGridY()][pacmanGridX()] == 'w') {
                    pacmanX += 25;
                }
                // **>

            }

            if (ghost1Direction == 0) {

                ghost1Y -= ghost1Velocity;

                if ((ghost1X - 80) % 56 != 0) {
                    ghost1X = (ghost1GridX() - 1) * 56 + 80;
                }

                if (mapArray[ghost1GridY()][ghost1GridX()] == 'w') {
                    ghost1Y += 25;
                }
            } else if (ghost1Direction == 1) {

                ghost1X += ghost1Velocity;

                if ((ghost1Y - 70) % 54 != 0) {
                    ghost1Y = (ghost1GridY() - 1) * 54 + 70;
                }

                if (mapArray[ghost1GridY()][ghost1GridX()] == 'w') {
                    ghost1X -= 25;
                }
            } else if (ghost1Direction == 2) {
                ghost1Y += ghost1Velocity;

                if ((ghost1X - 80) % 56 != 0) {
                    ghost1X = (ghost1GridX() - 1) * 56 + 80;
                }

                if (mapArray[ghost1GridY()][ghost1GridX()] == 'w') {
                    ghost1Y -= 25;
                }
            } else if (ghost1Direction == 3) {
                ghost1X -= ghost1Velocity;

                if ((ghost1Y - 70) % 54 != 0) {
                    ghost1Y = (ghost1GridY() - 1) * 54 + 70;
                }

                if (mapArray[ghost1GridY()][ghost1GridX()] == 'w') {
                    ghost1X += 25;
                }
            }

            
            if (ghost2Direction == 0) {
                ghost2Y -= ghost2Velocity;
                if ((ghost2X - 80) % 56 != 0) {
                    ghost2X = (ghost2GridX() - 1) * 56 + 80;
                }

                if (mapArray[ghost2GridY()][ghost2GridX()] == 'w') {
                    ghost2Y += 25;
                }
            } else if (ghost2Direction == 1) {
                ghost2X += ghost2Velocity;
                if ((ghost2Y - 70) % 54 != 0) {
                    ghost2Y = (ghost2GridY() - 1) * 54 + 70;
                }

                if (mapArray[ghost2GridY()][ghost2GridX()] == 'w') {
                    ghost2X -= 25;
                }
            } else if (ghost2Direction == 2) {
                ghost2Y += ghost2Velocity;

                if ((ghost2X - 80) % 56 != 0) {
                    ghost2X = (ghost2GridX() - 1) * 56 + 80;
                }

                if (mapArray[ghost2GridY()][ghost2GridX()] == 'w') {
                    ghost2Y -= 25;
                }
            } else if (ghost2Direction == 3) {
                ghost2X -= ghost2Velocity;
                if ((ghost2Y - 70) % 54 != 0) {
                    ghost2Y = (ghost2GridY() - 1) * 54 + 70;
                }

                if (mapArray[ghost2GridY()][ghost2GridX()] == 'w') {
                    ghost2X += 25;
                }
            }


            // 鬼3的移动逻辑
            // 利用面朝来决定下一帧的位置
            // 包括撞墙就不动
            if (ghost3Direction == 0) {
                ghost3Y -= ghost3Velocity;
                if ((ghost3X - 80) % 56 != 0) {
                    ghost3X = (ghost3GridX() - 1) * 56 + 80;
                }

                if (mapArray[ghost3GridY()][ghost3GridX()] == 'w') {
                    ghost3Y += 25;
                }
            } else if (ghost3Direction == 1) {
                ghost3X += ghost3Velocity;
                if ((ghost3Y - 70) % 54 != 0) {
                    ghost3Y = (ghost3GridY() - 1) * 54 + 70;
                }

                if (mapArray[ghost3GridY()][ghost3GridX()] == 'w') {
                    ghost3X -= 25;
                }
            } else if (ghost3Direction == 2) {
                ghost3Y += ghost3Velocity;

                if ((ghost3X - 80) % 56 != 0) {
                    ghost3X = (ghost3GridX() - 1) * 56 + 80;
                }

                if (mapArray[ghost3GridY()][ghost3GridX()] == 'w') {
                    ghost3Y -= 25;
                }
            } else if (ghost3Direction == 3) {
                ghost3X -= ghost3Velocity;
                if ((ghost3Y - 70) % 54 != 0) {
                    ghost3Y = (ghost3GridY() - 1) * 54 + 70;
                }

                if (mapArray[ghost3GridY()][ghost3GridX()] == 'w') {
                    ghost3X += 25;
                }
            }

            if (pacmanGridX() == ghost1GridX() && pacmanGridY() == ghost1GridY()) {
                state = "lose";
                num = 0;
                frameCount = 0;
                pacmanX = 80;
                pacmanY = 70;

                ghost1X = 1290;
                ghost1Y = 70;

                ghost2X = 1290;
                ghost2Y = 785;

                ghost3X = 80;
                ghost3Y = 785;                         
            }

            if (pacmanGridX() == ghost2GridX() && pacmanGridY() == ghost2GridY()) {
                state = "lose"; 
                num = 0;
                frameCount = 0;

                pacmanX = 80;
                pacmanY = 70;

                ghost1X = 1290;
                ghost1Y = 70;

                ghost2X = 1290;
                ghost2Y = 785;

                ghost3X = 80;
                ghost3Y = 785;                        
            }

            if (pacmanGridX() == ghost2GridX() && pacmanGridY() == ghost2GridY()) {
                state = "lose"; 
                num = 0;
                frameCount = 0;

                pacmanX = 80;
                pacmanY = 70;

                ghost1X = 1290;
                ghost1Y = 70;

                ghost2X = 1290;
                ghost2Y = 785;

                ghost3X = 80;
                ghost3Y = 785;                        
            }

                ghost3Y = 785;
                ghost3Y = 785;
            if (pacmanGridX() == fruitGridX && pacmanGridY() == fruitGridY) {
                spawnNewFruit();
            }

            openMouth = !openMouth;

            if(num == 2) {
                state = "win";
                num = 0;

                writeScoreToLeaderboard(frameCount);
                pacmanX = 80;
                pacmanY = 70;

                ghost1X = 1290;
                ghost1Y = 70;

                ghost2X = 1290;
                ghost2Y = 785;

                ghost3X = 80;
                ghost3Y = 785;       
                ghost3X = 80;
                ghost3Y = 785;       
                ghost3X = 80;
                ghost3Y = 785; 
            }      
            // 数帧数：赢了之后的分数是按跑了多少帧来算的
            frameCount++;

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

            double[] leaderboard = top5Leaderboard();

            g.drawString(Double.toString(leaderboard[0]), 600, 300);
            g.drawString(Double.toString(leaderboard[1]), 600, 390);
            g.drawString(Double.toString(leaderboard[2]), 600, 480);
            g.drawString(Double.toString(leaderboard[3]), 600, 570);
            g.drawString(Double.toString(leaderboard[4]), 600, 660);
            
            g.setColor(new Color(0, 0, 0));
        } else if ("win".equals(state)) {
        	 g.drawImage(win,0,0, this);
        } else {
            g.drawImage(lose,0,0, this);
        }
    }

    public static void main(String[] args) throws IOException {
        game = ImageIO.read(new File("resources/background.png"));
        start = ImageIO.read(new File("resources/start.png"));
        rule = ImageIO.read(new File("resources/rule.png"));
        ranking = ImageIO.read(new File("resources/ranking.png"));
        win = ImageIO.read(new File("resources/win.png"));
        lose = ImageIO.read(new File("resources/lose.png"));
        openMouth = false;
        pacmanOpenMouthImg = ImageIO.read(new File("resources/pacman_open_mouth.png"));
        pacmanImg = ImageIO.read(new File("resources/pacman.png"));
        pacmanX = 80;
        pacmanY = 70;
        pacmanVelocity = 1;
        pacmanDirection = 1;

        ghost1Img = ImageIO.read(new File("resources/ghost1.png"));
        ghost1X = 1290;
        ghost1Y = 70;
        ghost1Velocity = 1;
        ghost1Direction = 1;

        ghost2Img = ImageIO.read(new File("resources/ghost2.png"));
        ghost2X = 1290;
        ghost2Y = 785;
        ghost2Velocity = 1;
        ghost2Direction = 1;

        ghost3Img = ImageIO.read(new File("resources/ghost3.png"));
        ghost3X = 80;
        ghost3Y = 785;
        ghost3Velocity = 1;
        ghost3Direction = 1;

        fruitImg = ImageIO.read(new File("resources/coconut.png"));
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

    public void run() {
        while(true){                

            try {
                Thread.sleep(20);   // 50 frames per second

            } catch (InterruptedException e) {}
            
            // Drawing the Screen
            repaint();
        }
    }
    
    // 听键盘
    public void keyPressed(KeyEvent e) {
    	if(state.equals("start")) {
            // 如果在开始界面，听键盘发出了a键
    		if (e.getKeyChar() == 'a') {
    			state = "rule";
    		}
            // 如果在开始界面，听键盘发出了b键
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

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}

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
        	    
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

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
    public static void writeScoreToLeaderboard(double score) {

        try {

            double[] top5 = top5Leaderboard();

            PrintWriter pw = new PrintWriter(new FileWriter("leaderboard.txt"));

            for (int k = 0; k < 5; k++) {
                System.out.println(top5[k]);
            }

            double[] top6 = new double[6];

            int i = 0, j = 0;

            while (i < top5.length && top5[i] > score) {
                top6[j++] = top5[i++];
            }

            top6[j++] = score;

            while (i < top5.length) {
                top6[j++] = top5[i++];
            }

            for (int k = 0; k < 5; k++) {
                pw.println(top6[k]);
            }

            pw.close();
        } catch (IOException ex) {}

    }


    public static double[] top5Leaderboard() {

        double[] topfive = new double[5];

        try {
            Scanner sc = new Scanner(new File("leaderboard.txt"));
                        
            for (int i = 0; i < 5; i++) {
                if (sc.hasNextLine()) {
                    topfive[i] = Double.parseDouble(sc.nextLine());
                }
            }
            
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println("leaderboard.txt not found");
        }

        return topfive;

    }
    

}

