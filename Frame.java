import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Frame extends JPanel implements ActionListener {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    
    Particle particula = new Particle();
    ArrayList<Particle> particles = new ArrayList<Particle>();

    int delay = 16;
    Timer timer = new Timer(delay, this);


    public static void main(String[] args) {
        Frame frame = new Frame();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 17);
        g.setFont(font);

        g.drawString("Timer delay: " + delay + "ms", 10, 25);
        g.drawString("Stats: " + Particle.showStats + " [Bound to KeyEvent.VK_S]", 10, 45);
        
        for (Particle p: particles) {
            p.drawParticle(g);
        }
    }

    public Frame() {

        JFrame frame = new JFrame("Simulation");
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.addKeyListener(new Keyboard());
        this.setSize(new Dimension(WIDTH, HEIGHT));

        for (int i = 0; i < 15; i++) {
            particles.add(new Particle());
        }
        
        timer.restart();
        frame.add(this);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        repaint();

    }

    public class Keyboard extends KeyAdapter {
        @Override

        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {

                case KeyEvent.VK_UP:
                    if (!timer.isRunning()) {
                        timer.start();
                        System.out.println("Started the timer.");
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if (timer.isRunning()) {
                        timer.stop();
                        System.out.println("Stopped the timer.");
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if (timer.isRunning()) {
                        delay -= 1;
                        timer.setDelay(delay);
                        System.out.println("Increased the speed.");
                    }
                    break;

                case KeyEvent.VK_LEFT:
                    if (timer.isRunning()) {
                        delay += 1;
                        timer.setDelay(delay);
                        System.out.println("Decreased the  speed.");
                    }
                    break;

                case KeyEvent.VK_S:
                    if (timer.isRunning()) {
                        if (Particle.showStats == true) {
                            System.out.println("Stats hidden.");
                            Particle.showStats = false;
                        }else{
                            System.out.println("Stats shown.");
                            Particle.showStats = true;
    
                        }

                    }
                    break;

                case KeyEvent.VK_Q:
                    if (timer.isRunning()) {
                        System.out.println("Exited.");
                        System.exit(0);
                    }
                    break;

            }
        
        }

    }

}
