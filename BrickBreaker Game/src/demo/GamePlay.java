package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Callable;



public class GamePlay extends JPanel implements ActionListener, KeyListener {

    public boolean play = false;
    public int score = 0;
    public int totalBricks = 21;
    public Timer timer;
    public int delay = 0;
    public int ballPosX = 120;
    public int ballPosY = 350;
    public static int ballXDir;
    public static int ballYDir;

    //public static String s;

    public int playerX = 350;

    public MapGenerator mapGen;

    public GamePlay() {

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        timer = new Timer(delay, this);
        timer.start();

        mapGen = new MapGenerator(3, 7);

    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 684, 550);

        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, 684, 3);
        g.fillRect(0, 3, 3, 550);
        g.fillRect(683, 3, 3, 550);

        //paddle
        g.setColor(Color.GREEN);
        g.fillRect(playerX, 520, 100, 6);

        mapGen.draw((Graphics2D) g);

        //ball
        g.setColor(Color.RED);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        //score
        g.setColor(Color.green);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Score :" + score, 540, 40);


        //gameover
        if (ballPosY >= 550) {
            play = false;
            ballXDir = 0;
            ballYDir = 0;

            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.ITALIC, 40));
            g.drawString("Game Over..!! Score :" + score, 150, 350);

            g.setFont(new Font("serif", Font.PLAIN, 30));
            g.drawString("Press Enter to Restart", 210, 400);

        }

        if (totalBricks <= 0) {
            play = false;
            ballXDir = 0;
            ballYDir = 0;

            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.ITALIC, 40));
            g.drawString("You Won..!!! Score :" + score, 150, 350);

            g.setFont(new Font("serif", Font.PLAIN, 30));
            g.drawString("Press Enter to Restart", 210, 400);
        }

    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            play = true;
            if (playerX <= 0) playerX = 0;
            else playerX -= 20;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            play = true;
            if (playerX >= 600) playerX = 600;
            else playerX += 20;
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                Main.glassPane.setVisible(true);
                //play=true;
                score = 0;
                totalBricks = 21;
                ballPosX = 120;
                ballPosY = 350;
                playerX = 350;

                /*if (s.equals("easy")) {
                    ballXDir = -1;
                    ballYDir = -2;
                } else if (s.equals("medium")) {
                    ballXDir = -2;
                    ballYDir = -3;
                } else if (s.equals("hard")) {
                    ballXDir = -3;
                    ballYDir = -4;
                }*/

                mapGen = new MapGenerator(3, 7);
            }

        }

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play) {
            if (ballPosX <= 0 || ballPosX >= 666) {
                ballXDir = -ballXDir;
            }
            if (ballPosY <= 0){
                ballYDir = -ballYDir;
            }

            Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
            Rectangle paddleRect = new Rectangle(playerX, 520, 100, 6);

            if (ballRect.intersects(paddleRect)){
                ballYDir = -ballYDir;
            }

            A:
            for (int i = 0; i < mapGen.map.length; i++) {
                for (int j = 0; j < mapGen.map[0].length; j++) {
                    if (mapGen.map[i][j] > 0) {

                        int width = mapGen.brickWidth;
                        int height = mapGen.brickHeight;
                        int brickXPos = 80 + j * width;
                        int brickYPos = 70 + i * height;

                        Rectangle brickRect = new Rectangle(brickXPos, brickYPos, width, height);

                        if (ballRect.intersects(brickRect)) {
                            mapGen.setBrick(0, i, j);
                            totalBricks--;
                            score += 5;

                            if (ballPosX + 19 <= brickXPos || ballPosX + 1 >= brickXPos + width) {
                                ballXDir = -ballXDir;
                            } else {
                                ballYDir = -ballYDir;
                            }
                            break A;

                        }

                    }
                }
            }

            ballPosX += ballXDir;
            ballPosY += ballYDir;

        }

        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


}
