package cgpa_gpa_calculator;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    private float opacity = 0f;
    private int cornerRadius;

    public CardPanel(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        setOpaque(false);
        setLayout(new BorderLayout());

        Timer timer = new Timer(15, e -> {
            opacity += 0.05f;
            if (opacity >= 1f) {
                opacity = 1f;
                ((Timer) e.getSource()).stop();
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // Anti-aliasing for smooth edges
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Shadow effect (optional enhancement)
        for (int i = 5; i >= 1; i--) {
            g2.setColor(new Color(0, 0, 0, 10 * i)); // Layered shadows
            g2.fillRoundRect(i, i, width - i * 2, height - i * 2, 30, 30);
        }

        //  Gradient fill (HERE’s where you add it)
        GradientPaint gp = new GradientPaint(
                0, 0, new Color(255, 255, 255, 220),
                width, height, new Color(240, 240, 240, 220)
        );
        g2.setPaint(gp);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2.fillRoundRect(0, 0, width - 20, height - 20, 30, 30);

        g2.dispose();
        super.paintComponent(g);
    }
}
