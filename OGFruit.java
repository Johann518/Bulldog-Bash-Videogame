/* 
 * Basic fruit class
 */


 import java.awt.*;
 import java.awt.image.BufferedImage;
 import javax.imageio.ImageIO;
 import java.io.IOException;
 import java.io.File;

public class OGFruit {

    Font TimesNewRoman = new Font("Times New Roman",Font.BOLD, -1 * getHowBig()*50);
    private static final int CANVAS_SIZE = 1000;
    private int x;
    private int y;
    public BufferedImage image;
    private int gameLevel;
    public int clickNumber;
    public int movesMade;
    public boolean exploded;
    public int explosionTime;
    public boolean missed;

    public OGFruit(int gameDifficulty) {
        y = 0;
        x = (int)(Math.random() * CANVAS_SIZE * 9 / 10);
        gameLevel = gameDifficulty;
        movesMade = 0;
        clickNumber = 0;
        exploded = false;
        explosionTime = 0;
        missed = false;
    }

    public void move () {
        y += getSpeed();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHowBig() {
        return (int)(CANVAS_SIZE * (1 / Math.sqrt(gameLevel) / 5));
    }

    public void paintComponent(Graphics g) {
        if (getY() <= getCanvasSize()) {
            g.setColor(new Color(0, 53, 107));//Yale Blue
            g.fillOval(getX(), getY(), getHowBig(), getHowBig());

            g.setColor(Color.WHITE);
            g.setFont(TimesNewRoman);
            g.drawString("Yale", getX() + getHowBig()/4, getY() + 9 * getHowBig()/16);
        } else {
            if (movesMade % 2 == 0) g.setColor(Color.WHITE);
            else g.setColor(Color.RED);
            g.fillOval(getX(),9 * getCanvasSize()/10, getHowBig(), getHowBig());
        }
    }

    public int getPointValue () {
        return 50 * gameLevel;
    }

    public double getSpeed() {
        return (double) gameLevel;
    }

    public File getSoundFile() {
        return new File("laser.wav");
    }

    public int getCanvasSize() {
        return CANVAS_SIZE;
    }

    public String toRemove () {
        return "swipe";
    }

    public int clicksNeeded() {
        return 0;
    }

    public boolean clickToDie() {
        return false;
    }

    public String audioName() {
        return "laser.wav";
    }

    public boolean dontDie() {
        return false;
    }

}
