import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class App extends JPanel implements KeyListener, Runnable, MouseListener {

    // start, game, rule, ranking, win, lose
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

    // state: backgrounds
    public static BufferedImage start;
    public static BufferedImage game;
    public static BufferedImage rule;
    public static BufferedImage ranking;
    public static BufferedImage win;
    public static BufferedImage lose;
    
    // 糖豆人的图片，屏幕上的位置，mapArray里的位置，速度，朝向，张不张嘴
    public static BufferedImage pacmanImg;
    public static BufferedImage pacmanOpenMouthImg;
    public static int pacmanX;
    public static int pacmanY;
    public static int pacmanGridX;
    public static int pacmanGridY;
    public static double pacmanVelocity;
    // 0 = up, 1 = right, 2 = down, 3 = left
    public static int pacmanDirection;
    public static boolean openMouth;

    // 鬼1的图片，屏幕上的位置，mapArray里的位置，速度，朝向
    public static BufferedImage ghost1Img;
    public static int ghost1X;
    public static int ghost1Y;
    public static int ghost1GridX;
    public static int ghost1GridY;
    public static double ghost1Velocity;
    // 0 = up, 1 = right, 2 = down, 3 = left
    public static int ghost1Direction;

    // 鬼2的图片，屏幕上的位置，mapArray里的位置，速度，朝向
    public static BufferedImage ghost2Img;
    public static int ghost2X;
    public static int ghost2Y;
    public static int ghost2GridX;
    public static int ghost2GridY;
    public static double ghost2Velocity;
    // 0 = up, 1 = right, 2 = down, 3 = left
    public static int ghost2Direction;

    // 鬼3的图片，屏幕上的位置，mapArray里的位置，速度，朝向
    public static BufferedImage ghost3Img;
    public static int ghost3X;
    public static int ghost3Y;
    public static int ghost3GridX;
    public static int ghost3GridY;
    public static double ghost3Velocity;
    // 0 = up, 1 = right, 2 = down, 3 = left
    public static int ghost3Direction;

    // 椰子的图片，屏幕上的位置，mapArray里的位置
    public static BufferedImage fruitImg;
    public static int fruitX;
    public static int fruitY;
    public static int fruitGridX;
    public static int fruitGridY;

    // 吃了几个椰子
    public static int num = 0;

    // 时间跨度：游戏时长
    public static int frameCount = 0;

    // 设置基础参数
    // 比如键盘要不要听
    // 鼠标要不要听
    // 最后开始游戏
    // this == 本体
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

    // 画画面
    // 根据你现在的state，去画不同的东西
    // 比如start，就只画开始界面/背景
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

            double[] leaderboard = top5Leaderboard();

            g.drawString(Double.toString(leaderboard[0]), 600, 300);
            g.drawString(Double.toString(leaderboard[1]), 600, 390);
            g.drawString(Double.toString(leaderboard[2]), 600, 480);
            g.drawString(Double.toString(leaderboard[3]), 600, 570);
            g.drawString(Double.toString(leaderboard[4]), 600, 660);
            
            g.setColor(new Color(0, 0, 0));
        } else if ("win".equals(state)) {
        	 g.drawImage(win,0,0, this);
        } else if ("lose".equals(state)) {
        	 g.drawImage(lose,0,0, this);
        }
        
    }

    // 游戏的进入点：这里设置所有资源，例如图片和起始位置
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
        pacmanGridX = 1;
        pacmanGridY = 1;
        pacmanVelocity = 55;
        pacmanDirection = 1;

        ghost1Img = ImageIO.read(new File("resources/ghost1.png"));
        ghost1GridX = 23;
        ghost1GridY = 1;
        ghost1X = 80 + ((ghost1GridX - 1) * 55);
        ghost1Y = 70 + ((ghost1GridY - 1) * 55);
        ghost1Velocity = 55;
        ghost1Direction = 1;

        ghost2Img = ImageIO.read(new File("resources/ghost2.png"));
        ghost2GridX = 23;
        ghost2GridY = 14;
        ghost2X = 80 + ((ghost2GridX - 1) * 55);
        ghost2Y = 70 + ((ghost2GridY - 1) * 55);
        ghost2Velocity = 55;
        ghost2Direction = 1;

        ghost3Img = ImageIO.read(new File("resources/ghost3.png"));
        ghost3GridX = 1;
        ghost3GridY = 14;
        ghost3X = 80 + ((ghost3GridX - 1) * 55);
        ghost3Y = 70 + ((ghost3GridY - 1) * 55);
        ghost3Velocity = 55;
        ghost3Direction = 1;

        fruitImg = ImageIO.read(new File("resources/coconut.png"));
        fruitGridX = 1;
        fruitGridY = 3;
        fruitX = 80 + ((fruitGridX - 1) * 55);
        fruitY = 70 + ((fruitGridY - 1) * 55);

        // 设置完资源之后，开始跑游戏
        JFrame frame = new JFrame("Pacman");
        JPanel panel = new App();
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();

    }

    // 每一帧都会跑一次的核心逻辑
    public void run() {
        while(true){                
            if (state == "game") {

                // 鬼1的朝向逻辑
                // 始终往糖豆人的方向移动
                if (pacmanGridX > ghost1GridX && mapArray[ghost1GridY][ghost1GridX + 1] != 'w') {
                    ghost1Direction = 1;
                } else if (pacmanGridX < ghost1GridX && mapArray[ghost1GridY][ghost1GridX - 1] != 'w') {
                    ghost1Direction = 3;
                } else if (pacmanGridY < ghost1GridY && mapArray[ghost1GridY - 1][ghost1GridX] != 'w') {
                    ghost1Direction = 0;
                } else {
                    ghost1Direction = 2;
                }
                
                // 鬼2的朝向逻辑
                // 始终往糖豆人的方向移动
                if (pacmanGridX > ghost2GridX && mapArray[ghost2GridY][ghost2GridX + 1] != 'w') {
                    ghost2Direction = 1;
                } else if (pacmanGridX < ghost2GridX && mapArray[ghost2GridY][ghost2GridX - 1] != 'w') {
                    ghost2Direction = 3;
                } else if (pacmanGridY < ghost2GridY && mapArray[ghost2GridY - 1][ghost2GridX] != 'w') {
                    ghost2Direction = 0;
                } else {
                    ghost2Direction = 2;
                }

                // 鬼3的朝向逻辑
                // 始终往糖豆人的方向移动
                if (pacmanGridX > ghost3GridX && mapArray[ghost3GridY][ghost3GridX + 1] != 'w') {
                    ghost3Direction = 1;
                } else if (pacmanGridX < ghost3GridX && mapArray[ghost3GridY][ghost3GridX - 1] != 'w') {
                    ghost3Direction = 3;
                } else if (pacmanGridY < ghost3GridY && mapArray[ghost3GridY - 1][ghost3GridX] != 'w') {
                    ghost3Direction = 0;
                } else {
                    ghost3Direction = 2;
                }

                // 糖豆人的移动逻辑
                // 利用面朝来决定下一帧的位置
                // 包括撞墙就不动
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

                // 鬼1的移动逻辑
                // 利用面朝来决定下一帧的位置
                // 包括撞墙就不动
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

                
                // 鬼2的移动逻辑
                // 利用面朝来决定下一帧的位置
                // 包括撞墙就不动
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


                // 鬼3的移动逻辑
                // 利用面朝来决定下一帧的位置
                // 包括撞墙就不动
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

                // 糖豆人被鬼1撞到
                if (pacmanGridX == ghost1GridX && pacmanGridY == ghost1GridY) {
                    state = "lose";
                    num = 0;
                    frameCount = 0;
                    pacmanGridX = 1;
                    pacmanGridY = 1;
                    pacmanX = 80;
                    pacmanY = 70;


                    ghost1GridX = 23;
                    ghost1GridY = 1;
                    ghost1X = 80 + ((ghost1GridX - 1) * 55);
                    ghost1Y = 70 + ((ghost1GridY - 1) * 55);


                    ghost2GridX = 23;
                    ghost2GridY = 14;
                    ghost2X = 80 + ((ghost2GridX - 1) * 55);
                    ghost2Y = 70 + ((ghost2GridY - 1) * 55);


                    ghost3GridX = 1;
                    ghost3GridY = 14;
                    ghost3X = 80 + ((ghost3GridX - 1) * 55);
                    ghost3Y = 70 + ((ghost3GridY - 1) * 55);                    
                }

                // 糖豆人被鬼2撞到
                if (pacmanGridX == ghost2GridX && pacmanGridY == ghost2GridY) {
                    state = "lose"; 
                    num = 0;
                    frameCount = 0;
                    pacmanGridX = 1;
                    pacmanGridY = 1;
                    pacmanX = 80;
                    pacmanY = 70;


                    ghost1GridX = 23;
                    ghost1GridY = 1;
                    ghost1X = 80 + ((ghost1GridX - 1) * 55);
                    ghost1Y = 70 + ((ghost1GridY - 1) * 55);


                    ghost2GridX = 23;
                    ghost2GridY = 14;
                    ghost2X = 80 + ((ghost2GridX - 1) * 55);
                    ghost2Y = 70 + ((ghost2GridY - 1) * 55);


                    ghost3GridX = 1;
                    ghost3GridY = 14;
                    ghost3X = 80 + ((ghost3GridX - 1) * 55);
                    ghost3Y = 70 + ((ghost3GridY - 1) * 55);                   
                }

                // 糖豆人被鬼3撞到
                if (pacmanGridX == ghost3GridX && pacmanGridY == ghost3GridY) {
                    state = "lose";   
                    num = 0;
                    frameCount = 0;
                    pacmanGridX = 1;
                    pacmanGridY = 1;
                    pacmanX = 80;
                    pacmanY = 70;


                    ghost1GridX = 23;
                    ghost1GridY = 1;
                    ghost1X = 80 + ((ghost1GridX - 1) * 55);
                    ghost1Y = 70 + ((ghost1GridY - 1) * 55);


                    ghost2GridX = 23;
                    ghost2GridY = 14;
                    ghost2X = 80 + ((ghost2GridX - 1) * 55);
                    ghost2Y = 70 + ((ghost2GridY - 1) * 55);


                    ghost3GridX = 1;
                    ghost3GridY = 14;
                    ghost3X = 80 + ((ghost3GridX - 1) * 55);
                    ghost3Y = 70 + ((ghost3GridY - 1) * 55);                 
                }

                // 吃到椰子的逻辑：刷新新的椰子
                if (pacmanGridX == fruitGridX && pacmanGridY == fruitGridY) {
                    spawnNewFruit();
                }

                // 每一帧糖豆人的张嘴闭嘴
                openMouth = !openMouth;

                // 吃到足够的椰子就赢赢赢的逻辑
                if(num == 2) {
                    state = "win";
                    num = 0;

                    // 分数写进leaderboard.txt
                    writeScoreToLeaderboard(1000 / frameCount);
                    
                    frameCount = 0;
                    pacmanGridX = 1;
                    pacmanGridY = 1;
                    pacmanX = 80;
                    pacmanY = 70;


                    ghost1GridX = 23;
                    ghost1GridY = 1;
                    ghost1X = 80 + ((ghost1GridX - 1) * 55);
                    ghost1Y = 70 + ((ghost1GridY - 1) * 55);


                    ghost2GridX = 23;
                    ghost2GridY = 14;
                    ghost2X = 80 + ((ghost2GridX - 1) * 55);
                    ghost2Y = 70 + ((ghost2GridY - 1) * 55);


                    ghost3GridX = 1;
                    ghost3GridY = 14;
                    ghost3X = 80 + ((ghost3GridX - 1) * 55);
                    ghost3Y = 70 + ((ghost3GridY - 1) * 55);
                }

                // 数帧数：赢了之后的分数是按跑了多少帧来算的
                frameCount++;

            }

            try {
                Thread.sleep(500);

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

    // 听鼠标按键，主要是鼠标按键按到屏幕的哪里
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

    // return GridX,GridY
    // 新的椰子的位置
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

    // 写分数到leaderboard.txt
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

    // 从leaderboard.txt里面读取5个最高分数，然后换回去一个double array
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

