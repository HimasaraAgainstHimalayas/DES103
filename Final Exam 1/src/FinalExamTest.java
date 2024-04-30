import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class FinalExamTest {
    public static void main(String[] args){
        JFrame frame = new JFrame("Final Exam");
        FinalExam panel = new FinalExam();
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(2, 2));
        subPanel.add(panel.startButton);
        subPanel.add(panel.radiusTextField);
        subPanel.add(panel.computeButton);
        subPanel.add(panel.areaLabel);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(subPanel, BorderLayout.SOUTH);
        frame.setSize(800, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
