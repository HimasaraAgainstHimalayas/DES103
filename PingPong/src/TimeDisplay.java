import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class TimeDisplay extends JPanel implements ActionListener {
    Timer timer = new Timer(1000, this);
    PingPong pingPongPanel = new PingPong();
    boolean started = false;
    int countDown = 3;
    int leftScore = 0;
    int rightScore = 0;
    TimeDisplay(){
        timer.start();
    }
    protected void paintComponent(Graphics g) {
        if(leftScore == 3) {
            super.paintComponent(g);
            g.setColor(new Color(255, 255, 0));
            g.setFont(new Font("SansSerif", Font.BOLD, 75));
            g.drawString("Left Player WIN !!!", 200, 75);
            timer.stop();
        }
        else if (rightScore == 3) {
            super.paintComponent(g);
            g.setColor(new Color(255, 0, 0));
            g.setFont(new Font("SansSerif", Font.BOLD, 75));
            g.drawString("Right Player WIN !!!", 200, 75);
            timer.stop();
        }
        else {
            super.paintComponent(g);
            if (!started) {
                g.setFont(new Font("SansSerif", Font.BOLD, 100));
                g.drawString(String.valueOf(countDown), 480, 75);
            }
            else {
                g.setFont(new Font("SansSerif", Font.BOLD, 75));
                g.drawString(String.format("%02d", leftScore) + ":" + String.format("%02d", rightScore), 420, 75);
            }
        }
    }
    public void actionPerformed(ActionEvent e) {
        if (countDown != 1) {
            countDown--;
            repaint();
        }
        else {
            started = true;
            pingPongPanel.isMoving = true;
            pingPongPanel.moveTimer.start();
            repaint();
            if (pingPongPanel.leftWin) {
                pingPongPanel.leftWin = false;
                leftScore ++ ;
                countDown = 3;
                timer.restart();
                pingPongPanel.moveTimer.stop();
                started = false;
                pingPongPanel.x = 475;
                pingPongPanel.y = 275;
                pingPongPanel.multiplier = 1;
                pingPongPanel.repaint();
            }
            if (pingPongPanel.rightWin) {
                pingPongPanel.rightWin = false;
                countDown = 3;
                rightScore ++;
                timer.restart();
                pingPongPanel.moveTimer.stop();
                started = false;
                pingPongPanel.x = 475;
                pingPongPanel.y = 275;
                pingPongPanel.multiplier = 1;
                pingPongPanel.repaint();
            }
        }
    }
}
