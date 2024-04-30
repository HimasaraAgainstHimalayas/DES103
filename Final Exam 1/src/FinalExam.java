import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FinalExam extends JPanel implements ActionListener{
    JButton startButton = new JButton("Start");
    JButton computeButton = new JButton("Compute");
    JTextField radiusTextField = new JTextField("10");
    JLabel areaLabel = new JLabel("Area of circle = ");
    Timer moveTimer = new Timer(20, this);
    int radius = 10;
    int X = 100;
    int initialY = 200;
    int Y = initialY;
    FinalExam(){
        super();
        setBackground(Color.BLACK);
        startButton.addActionListener(this);
        radiusTextField.addActionListener(this);
        computeButton.addActionListener(this);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillOval(X, Y, radius*2, radius*2);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == startButton){
            moveTimer.start();
        }
        if (e.getSource() == radiusTextField){
            radius = Integer.parseInt(radiusTextField.getText());
        }
        if (e.getSource() == moveTimer){
            Y -= 5;
            if (Y == (initialY-100)){
                initialY = Y;
                moveTimer.stop();
            }
        }
        if (e.getSource() == computeButton){
            areaLabel.setText("Area of circle = " + Math.PI*radius*radius);
        }
        repaint();
    }
}