/*
* Basic fruit class
*/


import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.JPanel;
import java.io.File;


public class Fruit {

    private static final int CANVAS_SIZE = 600;
    private int x;
    private int y;
    private int SIZE;
    private double SPEED;
    private int pointVal;
    public BufferedImage image;
    private String imgName = "YaleLogo.png";
    private int timesCalled;
    private File soundFile;

    public Fruit(int gameDifficulty) {
        this.pointVal = 50;
        this.x = (int)(Math.random() * CANVAS_SIZE);
        y = 0;
        this.SIZE = 50 * (gameDifficulty * 1/2);
        this.SPEED = (double)(1.0 * gameDifficulty);
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imgName));
        } catch (IOException e) {
            System.out.println("Could not find image in current folders");
        }
        this.soundFile = new File("laser.wav");
        timesCalled = 0;
    }

    // public Fruit(int gameDifficulty, int pointVal) {
    //     this.pointVal = pointVal;
    // }

    // public int gameDifficulty(int userSelectedDifficulty){
    //     if (userSelectedDifficulty == 0) {
    //         return 1;
    //     }
    //     else if (userSelectedDifficulty == 1) {
    //         return 2;
    //     }
    //     else {
    //         return 3;
    //     }
    // }

    public void move () {
        y += SPEED;
        timesCalled++;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getHowBig() {
        return this.SIZE;
    }

    public void paintComponent(Graphics g) {
        g.drawImage(image, x, y, SIZE, SIZE, null);
    }

}
