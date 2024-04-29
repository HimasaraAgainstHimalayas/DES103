import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class PingPong extends JPanel implements ActionListener, KeyListener {
    boolean isMoving = false;
    boolean moveUp = false;
    boolean moveDown = false;
    boolean moveLeft = false;
    boolean moveRight = false;
    boolean leftBoundable = true;
    boolean rightBoundable = true;
    boolean leftBoundMoveUp = false;
    boolean leftBoundMoveDown = false;
    boolean rightBoundMoveUp = false;
    boolean rightBoundMoveDown = false;
    boolean leftWin = false;
    boolean rightWin = false;
    int[] LeftBoundY = {200, 400};
    int[] RightBoundY = {200, 400};
    Random random = new Random();
    Timer moveTimer = new Timer(10, this);
    Timer boundTimer = new Timer(10, this);
    int x = 475;
    int y = 275;
    int radius = 25;
    int xMoveRate = 5;
    int yMoveRate = 5;
    float multiplier = 1;
    
    PingPong() {
        super();
        boundTimer.start();
        firstMove();
        setFocusable(true);
        addKeyListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.drawLine(1, 0, 1000, 0);
        g.setColor(new Color(255, 255, 0));
        g.fillRect(100, LeftBoundY[0], 20, LeftBoundY[1] - LeftBoundY[0]);
        g.setColor(new Color(255, 0 , 0));
        g.fillRect(880, RightBoundY[0], 20, RightBoundY[1] - RightBoundY[0]);
        g.setColor(new Color(88, 57, 39));
        g.fillOval(x, y, 2*radius, 2*radius);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == moveTimer) {
            if (isMoving) {
                if (moveUp) {
                    y -= yMoveRate * multiplier;
                }
                if (moveDown) {
                    y += yMoveRate * multiplier;
                }
                if (moveLeft) {
                    x -= xMoveRate * multiplier;
                }
                if (moveRight) {
                    x += xMoveRate * multiplier;
                }
                reachBound();
                gameIsDone();
            }
        }
        if (e.getSource() == boundTimer) {
            if (leftBoundMoveUp && LeftBoundY[0] >= 0) {
                LeftBoundY[0] -= 5;
                LeftBoundY[1] -= 5;
            }
            if (leftBoundMoveDown && LeftBoundY[1] <= 660){
                LeftBoundY[0] += 5;
                LeftBoundY[1] += 5;
            }
            if (rightBoundMoveUp && RightBoundY[0] >= 0) {
                RightBoundY[0] -= 5;
                RightBoundY[1] -= 5;
            }
            if (rightBoundMoveDown && RightBoundY[1] <= 660) {
                RightBoundY[0] += 5;
                RightBoundY[1] += 5;
            }
        }
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'W' || e.getKeyChar() == 'w') {
            leftBoundMoveUp = true;
        }
        if (e.getKeyChar() == 'S' || e.getKeyChar() == 's') {
            leftBoundMoveDown = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            rightBoundMoveUp = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            rightBoundMoveDown = true;
        }
    }
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'W' || e.getKeyChar() == 'w') {
            leftBoundMoveUp = false;
        }
        if (e.getKeyChar() == 'S' || e.getKeyChar() == 's') {
            leftBoundMoveDown = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            rightBoundMoveUp = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            rightBoundMoveDown = false;
        }
    }
    public void keyTyped(KeyEvent e) {}

    public void firstMove() {
        int randTemp;
        randTemp = random.nextInt(2);
        if (randTemp == 0) 
            moveUp = true;
        else 
            moveDown = true;
            
        randTemp = random.nextInt(2);
        if (randTemp == 0) 
            moveLeft = true;
        else 
            moveRight = true;
    }

    public void reachBound() {
        if ((x+radius >= 100 && x+radius <= 120) && (y+radius >= LeftBoundY[0] && y+radius <= LeftBoundY[1]) && leftBoundable) {
            leftBoundable = false;
            rightBoundable = true;
            multiplier += 0.1;
            rebound();
        }
        if (x+radius >= 840 && x+radius <= 860 && (y+radius >= RightBoundY[0] && y+radius <= RightBoundY[1]) && rightBoundable) {
            rightBoundable = false;
            leftBoundable = true;
            multiplier += 0.1;
            rebound();
        }
        if (y <= 0) {
            moveUp = false;
            moveDown = true;
        }
        if (y >= 600) {
            moveUp = true;
            moveDown = false;
        }
    }

    public void rebound() {
        if (moveDown) {
            moveUp = true;
            moveDown = false;
        }
        else {
            moveDown = true;
            moveUp = false;
        }
        if (moveLeft) {
            moveRight = true;
            moveLeft = false;
        }
        else {
            moveLeft = true;
            moveRight = false;
        }
        xMoveRate = 2 + random.nextInt(4);
        yMoveRate = (int) Math.sqrt(50 - xMoveRate*xMoveRate);
        System.out.println(xMoveRate + ", " + yMoveRate);
    }

    public void gameIsDone() {
        if (x >= (1000 - 2*radius)) {
            isMoving = false;
            moveTimer.stop();
            leftWin = true;
        }
        if (x <= 0) {
            isMoving = false;
            moveTimer.stop();
            rightWin = true;
        }
    }
}