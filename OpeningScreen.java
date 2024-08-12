/* 
 * This program builds the opening screen for our
 * fruit ninja game.
 */


 import java.awt.Color;
 import java.awt.Graphics2D;

 public class OpeningScreen {

    // setup drawing panel
    public static final int WIDTH = 500;
    public static final int HEIGHT = 400;
    public static DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);
    public static Graphics2D g = panel.getGraphics();

    public static void main(String[] args){

        welcomePanel();

    }

    public static void welcomePanel() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.GREEN);
        g.fillRect(WIDTH/4, HEIGHT/4, WIDTH/2, HEIGHT/4);
        String welcome = "Welcome to Fruit Ninja!";
        g.setColor(Color.MAGENTA);
        g.drawString(welcome, WIDTH/3, 3*HEIGHT/8);
    }
    
}
