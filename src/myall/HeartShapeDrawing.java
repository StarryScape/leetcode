package myall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class HeartShapeDrawing extends JPanel {
    private int fontSize = 20;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // 设置字体和颜色
        Font font = new Font("Serif", Font.BOLD, fontSize);
        g2d.setFont(font);
        g2d.setColor(Color.RED);

        // 绘制心形
        int x = getWidth() / 2;
        int y = getHeight() / 2 - 50;
        int width = 100;
        int height = 100;
        g2d.fill(new Ellipse2D.Double(x - width / 2, y, width, height));
        g2d.fillArc(x - width / 4, y - height / 2, width / 2, height, 0, 180);

        // 在心形内部绘制文字
        g2d.setColor(Color.BLACK);
        g2d.drawString("周静", x - 25, y + height / 2 + fontSize);

        // 绘制完成后释放资源
        g2d.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Heart Shape with Text");
            HeartShapeDrawing heartShape = new HeartShapeDrawing();
            frame.add(heartShape);
            frame.setSize(300, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
