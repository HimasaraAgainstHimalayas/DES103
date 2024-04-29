import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class PingPongTest {
    public static void main(String[] args){
        JFrame frame = new JFrame("Ping Pong Game");
        TimeDisplay timeDisplay = new TimeDisplay();
        frame.setSize(1020, 800);
        frame.setLayout(new BorderLayout());
        frame.add(timeDisplay, BorderLayout.NORTH);
        timeDisplay.setPreferredSize(new Dimension(1000, 100));
        frame.add(timeDisplay.pingPongPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}