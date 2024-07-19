import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paint extends JFrame {
    int y = 50;
    int x = 50;
    Color color = Color.BLACK;
    boolean erasing = false;
    int brushSize = 10;
    JPanel drawPanel;
    Font customFont = new Font("Italic", Font.BOLD, 24);
    JLabel title = new JLabel("PAINT");

    Paint() {
        JFrame jFrame = new JFrame("Paint");
        setLayout(null);
        setSize(600, 700);

        JButton colorButton = new JButton("Choose Color");
        colorButton.setBounds(0, 0, 200, 70);
        colorButton.setFont(customFont);
        add(colorButton);

        colorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Choose a color", color);
            if (newColor != null) {
                color = newColor;
            }
        });

        JButton rubbish = new JButton("Rubbish");
        rubbish.setBounds(200, 0, 200, 70);
        rubbish.setFont(customFont);
        add(rubbish);
        rubbish.addActionListener(e -> {
            erasing = !erasing;
            if (erasing) {
                rubbish.setText("Stop Erasing");
            } else {
                rubbish.setText("Rubbish");
            }
        });


        JSlider brushSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 50, brushSize);
        brushSizeSlider.setBounds(400, 0, 200, 70);
        brushSizeSlider.setBackground(Color.WHITE);
        brushSizeSlider.setFont(customFont);
        brushSizeSlider.setMajorTickSpacing(10);
        brushSizeSlider.setMinorTickSpacing(1);
        brushSizeSlider.setPaintTicks(true);
        brushSizeSlider.setPaintLabels(true);
        brushSizeSlider.addChangeListener(e -> brushSize = brushSizeSlider.getValue());
        add(brushSizeSlider);

        drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        drawPanel.setBounds(0, 70, 600, 570);
        drawPanel.setBackground(Color.WHITE);
        drawPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics g = drawPanel.getGraphics();
                if (erasing) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(color);
                }
                int halfBrushSize = brushSize / 2;
                g.fillOval(e.getX() - halfBrushSize, e.getY() - halfBrushSize, brushSize, brushSize);
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                x = 0;
                y = 0;
            }
        });
        add(drawPanel);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
