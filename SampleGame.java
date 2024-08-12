/* 
 * for running one level of the game
 */


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SampleGame extends JPanel implements MouseListener {

    private ArrayList<OGFruit> fruits;
    private static final int SIZE = 1000;
    private int time;
    private int fallTime;
    private int enterX;
    private int enterY;
    private static int score;
    private JLabel scoreLabel;
    private JPanel scoreBoard;
    private JFrame frame;
    private int gameLevel;
    private Timer timer;
    private boolean clickedToDie;
    private static BufferedWriter writer;
    public int explosionTime;
    public int totalMoves;
    public int stopTime;
    
    public SampleGame(int gameLevel) {
        // initialize class-level variables
        this.gameLevel = gameLevel;
        score = 0;
        fruits = new ArrayList<>();
        time = 0;
        fallTime = (int)(100/Math.sqrt(gameLevel));
        clickedToDie = false;
        explosionTime = 0;
        totalMoves = 0;

        // add mouse listener to panel
        this.addMouseListener(this);

        // set up frames
        frame = new JFrame("Fruit Ninja");
        frame.setLayout(new BorderLayout());

        // create scoreBoard + scoreLabel
        scoreLabel = new JLabel("Your Score: " + score);
        scoreBoard = new JPanel();
        scoreBoard.add(scoreLabel);
        
        // add elements to + setup frame
        setPreferredSize(new Dimension(SIZE, SIZE));
        setBackground(new Color(140, 169, 217));
        frame.add(this, BorderLayout.CENTER);
        frame.add(scoreBoard, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        // setup timer + start
        timer = new Timer(17, new FruitAnimator());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (OGFruit fruit : fruits) {
            fruit.paintComponent(g2d);
        }
    }

    private class FruitAnimator implements ActionListener {
        public void actionPerformed(ActionEvent event){
            totalMoves++;
            for (OGFruit fruit : fruits) {
                if(!clickedToDie) fruit.move();
                fruit.movesMade++;
            }
            if (time % fallTime == 0){
                int i;
                if (gameLevel == 1) i = (int)(Math.random() * 3);
                else if (gameLevel == 2) i = (int)(Math.random() * 5);
                else i = (int)(Math.random() * 9);
                if (i == 0) fruits.add(new Book(gameLevel));
                if (i == 1) fruits.add(new OGFruit(gameLevel));
                if (i == 2) fruits.add(new Athlete(gameLevel));
                if (gameLevel >= 2) {
                    if (i == 3) fruits.add(new Bomb(gameLevel));
                    if (i == 4) fruits.add(new OtherAthlete(gameLevel));

                }
                if (gameLevel == 3) {
                    if (i == 6) fruits.add(new Cake(gameLevel));
                    if (i == 7) fruits.add(new Harvard(gameLevel));
                    if (i > 7) fruits.add(new Bomb(gameLevel));
                }
            }
            repaint();
            time ++;
            if (gameOver()){
                frame.dispose();
                timer.stop();
                try {
                    printMethod(GetName.name());
                    EndScreen.main(); // somehow get this on the same screen?
                } catch (IOException e) {
                    System.out.println("Endscreen image not found");
                }
            }
        }
    }

    public void mouseClicked (MouseEvent e) {
        System.out.println("click registered");
        int x = e.getX();
        int y = e.getY();
        for (OGFruit fruit : fruits) {
            boolean rightX = x <= (fruit.getX() + fruit.getHowBig()) && x >= fruit.getX();
            boolean rightY = y <= (fruit.getY() + fruit.getHowBig()) && y >= fruit.getY();
            if (rightX && rightY){
                if(fruit.clickToDie() == true) {
                    clickedToDie = true;
                    fruit.exploded = true;
                    explosionTime = totalMoves;
                    StdAudio.play(fruit.audioName());
                }
                else if(fruit.toRemove() == "click") {
                    System.out.println("click in right spot");
                    fruit.clickNumber++;
                    System.out.println(fruit.clicksNeeded() + " " + fruit.clickNumber);
                    if(fruit.clicksNeeded() == fruit.clickNumber){
                        removeFruit(fruit);
                        break;
                    }
                }
            }
        }
    }

    public void mouseEntered (MouseEvent e) {
        
    }

    public void mouseExited (MouseEvent e) {

    }

    public void mousePressed (MouseEvent e) {
        enterX = e.getX();
        enterY = e.getY();
    }

    public void mouseReleased (MouseEvent e) {
        int exitX = e.getX();
        int exitY = e.getY();
        for (OGFruit fruit : fruits) {
            int centerX = fruit.getX() + fruit.getHowBig()/2;
            int centerY = fruit.getY() + fruit.getHowBig()/2;
            boolean xRange = (centerX > enterX && centerX < exitX) || (centerX < enterX && centerX > exitX);
            boolean yRange = (centerY > enterY && centerY < exitY) || (centerY < enterY && centerY > exitY);
            if (xRange && yRange) {
                if (fruit.clickToDie()) {
                    clickedToDie = true;
                    fruit.exploded = true;
                    explosionTime = totalMoves;
                    StdAudio.play(fruit.audioName());
                }
                else if (fruit.toRemove() == "swipe"){
                    removeFruit(fruit);
                    break;
                }
            }
        }
    }

    private void removeFruit(OGFruit fruit) {
        StdAudio.play(fruit.audioName());
        fruits.remove(fruit); 
        score += fruit.getPointValue();
        scoreLabel.setText("Your Score: " + score);
    }

    public static int getScore () {
        return score;
    }

    public boolean gameOver () {
        boolean over = false;
        if (time >= 1150) over = true;
        for (OGFruit fruit: fruits) {
            if (fruit.getY() >= SIZE && !fruit.dontDie()) {
                System.out.println("missed");
                fruit.missed = true;
                over = true;
            }
        }
        if (clickedToDie && totalMoves >= (explosionTime + 100)) over = true;
        return over;
    }

    public static void main(String[] args) {
        new SampleGame(1);
    }

    // Learned at https://stackoverflow.com/questions/4614227/how-to-add-a-new-line-of-text-to-an-existing-file-in-java
    private void printMethod(String name) {
        try {
            writer = new BufferedWriter(new FileWriter("LocalHighScores.txt", true));
            writer.append(" " + name + " " + score + "\n");
            writer.close();
        }
        catch (IOException e) {
            System.out.print("Please make sure file \"LocalHighScores.txt\" is in the folder");
        }
    }

}
