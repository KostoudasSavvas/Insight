package generategui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;

public class CustomeBorder extends AbstractBorder{
	    @Override
	    public void paintBorder(Component c, Graphics g, int x, int y,
	            int width, int height) {
	        super.paintBorder(c, g, x, y, width, height);
	        Graphics2D g2d = (Graphics2D)g;
	        g2d.setPaint(new Color(31,190,214));
	        Shape shape = new RoundRectangle2D.Float(0, 0, c.getWidth()-1, c.getHeight()-1,9, 9);
	        g2d.draw(shape);
	    }
}