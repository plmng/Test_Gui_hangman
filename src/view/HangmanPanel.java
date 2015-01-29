package view;
 
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
 
import javax.swing.JPanel;
 
import model.HangmanModel;
 
public class HangmanPanel extends JPanel {
     
    private static final long   serialVersionUID    = -4531686346612357752L;
     
    public static final int     maximumWrongGuesses = 10;
     
    private int baseWidth = 200;
    private int baseHeight = 20;
    private int poleWidth = 20;
     
    private HangmanModel model;
     
    public HangmanPanel(HangmanModel model) {
        this.model = model;
        this.setPreferredSize(new Dimension(300, 400));
        this.setBackground(Color.black);
    }
     
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
         
        Color color1 = Color.lightGray;
        Color color2 = Color.lightGray;
        g.setColor(color1);
         
        switch (model.getWrongGuesses()) {
        case 10:
            color2 = Color.red;
            drawNoose(g, color2);
        case 9:
            drawRightLeg(g, color2);
        case 8:
            drawLeftLeg(g, color2);
        case 7:
            drawRightArm(g, color2);
        case 6:
            drawLeftArm(g, color2);
        case 5:
            drawBody(g, color2);
        case 4:
            drawHead(g, color2);
        case 3:
            drawHeader(g, color1);
        case 2:
            drawPole(g, color1);
        case 1:
            drawBase(g, color1);
        }
    }
     
    private void drawNoose(Graphics g, Color color) {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(8.0f);
        g2d.setStroke(stroke);
        int x = getWidth() / 2;
        int y = baseHeight;
        g2d.drawLine(x, y, x, y + 40);
        stroke = new BasicStroke(20.0f);
        g2d.setStroke(stroke);
        g2d.drawLine(x, y + 120, x, y + 128);
    }
     
    private void drawRightLeg(Graphics g, Color color) {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(4.0f);
        g2d.setStroke(stroke);
        int x = getWidth() / 2;
        int y = 240;
        g2d.drawLine(x, y, x + 50, y + 50);
    }
 
    private void drawLeftLeg(Graphics g, Color color) {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(4.0f);
        g2d.setStroke(stroke);
        int x = getWidth() / 2;
        int y = 240;
        g2d.drawLine(x, y, x - 50, y + 50);
    }
         
    private void drawRightArm(Graphics g, Color color) {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(4.0f);
        g2d.setStroke(stroke);
        int x = getWidth() / 2;
        int y = 200;
        g2d.drawLine(x, y, x + 50, y - 50);
    }
 
    private void drawLeftArm(Graphics g, Color color) {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(4.0f);
        g2d.setStroke(stroke);
        int x = getWidth() / 2;
        int y = 200;
        g2d.drawLine(x, y, x - 50, y - 50);
    }
     
    private void drawBody(Graphics g, Color color) {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(4.0f);
        g2d.setStroke(stroke);
        int x = getWidth() / 2;
        int y = 140;
        g2d.drawLine(x, y, x, y + 100);
    }
     
    private void drawHead(Graphics g, Color color) {
        int width = 30;
        int x = (getWidth() - baseWidth) / 2 + baseWidth / 2 - width;
        int y = 100 - width;
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(4.0f);
        g2d.setStroke(stroke);
        g2d.drawOval(x, y, width + width, width + width);
    }
     
    private void drawHeader(Graphics g, Color color) {
        int width = baseWidth / 2 + 20;
        int x = (getWidth() - baseWidth) / 2 + baseWidth - width;
        int y = 10;
        g.setColor(color);
        g.fill3DRect(x, y, width, baseHeight, true);
    }
     
    private void drawPole(Graphics g, Color color) {
        int height = getHeight() - 20;
        int x = (getWidth() - baseWidth) / 2 + baseWidth;
        int y = 10;
        g.setColor(color);
        g.fill3DRect(x, y, poleWidth, height, true);
    }
     
    private void drawBase(Graphics g, Color color) {
        int x = (getWidth() - baseWidth) / 2;
        int y = getHeight() - baseHeight - 10;
        g.setColor(color);
        g.fill3DRect(x, y, baseWidth, baseHeight, true);
    }
 
}