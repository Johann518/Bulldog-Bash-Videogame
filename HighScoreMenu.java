// displays the highscore menu for the game

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

// ISSUES:
    // Is there a way to not allow them to resize the panel by pulling the edges?

public class HighScoreMenu {

    static final int HEIGHT = 373;
    static final int WIDTH = 720;
    static JFrame frame;
    static JButton button;
    static ActionListener listener;

    public static void main () throws IOException {

        setupPanel();

        displayScores();

        backButton();

        setImage();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void setupPanel() throws IOException {

        // setup frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT + ((int) (0.07 * HEIGHT))));
        frame.setTitle("Viewing High Scores for BulldogBlitz (Top 20)");

        // set layout type
        frame.setLayout(new BorderLayout());

    }


    private static void displayScores(){

        // get name and score arrays
        String[] nameArray = HighScores.getNameArray();
        int[] scoreArray = HighScores.getScoreArray();

        // create data
        String[] columnNames = {"Player", "Score"};
        int displayNScores = 20;
        Object[][] data = new Object[displayNScores + 1][2]; // needs to be #of scores + 1 to account for headers

        // headers for the columns
        data[0][0] = "PLAYER";
        data[0][1] = "SCORE";

        // add scores to the data
        for (int i = 1; i <= displayNScores; i++) { 
            data[i][0] = nameArray[i - 1];
            data[i][1] = scoreArray[i - 1];
        }

        // create and show the table
        JTable topScores = new JTable(data, columnNames);
        frame.add(topScores, BorderLayout.WEST);
    }


    private static void setImage() throws IOException {
        // get image for layout
        // note, this image is 560x360 pixels
        BufferedImage myPicture = ImageIO.read(new File("highscores.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        frame.add(picLabel, BorderLayout.CENTER);
    }

    private static void backButton () {
        // create button
        button = new JButton("Return to Home Screen");

        // button sizing
        button.setPreferredSize(new Dimension((int) (WIDTH), (int) (0.07 * HEIGHT)));

        // set up button
        frame.add(button, BorderLayout.PAGE_END);
        
        // setup action listener
        listener = new HighScoreClickListener(button);
        button.addActionListener(listener);
    }   

    public static void close() {
        frame.setVisible(false);
    }
}


