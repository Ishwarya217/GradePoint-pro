package cgpa_gpa_calculator;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
 private int cornerRadius;

 public RoundedPanel(int radius) {
     super();
     this.cornerRadius = radius;
     setOpaque(false);
 }

 @Override
 protected void paintComponent(Graphics g) {
     super.paintComponent(g);
     Dimension arcs = new Dimension(cornerRadius, cornerRadius);
     int width = getWidth();
     int height = getHeight();
     Graphics2D graphics = (Graphics2D) g;
     graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

     // Background color
     graphics.setColor(Color.WHITE);
     graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
 }
}
