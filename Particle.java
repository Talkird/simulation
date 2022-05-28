import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class Particle {
    
    private int x, vx;
    private int y, vy;
    private int n;
    private Color color;

    public static boolean showStats = false;

    public Particle() {

        //Min + (int)(Math.random() * ((Max - Min) + 1))
        x = 50 + (int)(Math.random() * (450 - 50));
        y = 50 + (int)(Math.random() * (350 - 50));

        vx = -8 + (int)(Math.random() * (8 - (-8)));
        vy = -5 + (int)(Math.random() * (5 - (-5)));

        n = 0 + (int)(Math.random() * (5 - 0));
        
    }

    public void setPosition(int xPos, int yPos) {
        x = xPos;
        y = yPos;
    }

    public int getXPosition() {
        return x;
    }

    public int getYPosition() {
        return y;
    }

    private void detectCollision() {

        if (x < 0 || x >= (Frame.WIDTH - 30)) {
            vx *= -1;
        }

        if (y < 0 || y >= (Frame.HEIGHT - 50)) {
            vy *= -1;
        }
    }

    private void randomizeColor() {
        switch (n) {
            case 1:
                color = Color.red;
                break;
            
            case 2:
                color = Color.blue;
                break;
            
            case 3:
                color = Color.green;
                break;
            
            case 4:
                color = Color.pink;
                break;
            
            case 5:
                color = Color.yellow;
                break;
            
        }
    }
    private void initMovement() {
        
        if (y < 450) {
            vy +=  1;
        }

        x += vx;
        y += vy;
    }

    public void setColor(Color newColor) {
        color = newColor;
    }

    public void drawParticle(Graphics g) {

        randomizeColor();
        initMovement();
        detectCollision();

        //Texture
        g.setColor(color);
        g.fillOval(x, y, 30, 30);

        if (showStats == true) {
            
            Font font = new Font(Font.MONOSPACED, Font.PLAIN, 17);
            g.setFont(font);

            //Vector
            g.setColor(Color.black);
            g.drawLine(x + 15, y + 15, x + (vx * 15), y + (vy * 15));

            //Position
            g.drawString("("+ x + "," + y  + ")", x - 25, y - 10);
            
        }
        
    }

}
