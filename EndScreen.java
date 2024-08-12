// This implements the end Screen for Bulldog Blitz

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class EndScreen {

    final static int HEIGHT = 480;
    final static int WIDTH = 720;
    static JFrame frame;
    static JButton[] buttons;
    static JLabel scoreLabel;


    public static void main() throws IOException {
        
        setupFrame();

        getImage();

        createButtons();

        createListener();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private static void setupFrame() {

        // setup frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setTitle("Thank you for playing Bulldog Blitz! Please select an option below:");

        // setup layout
        frame.setLayout(new BorderLayout());

    }


    private static void getImage() throws IOException {

        // get image for layout
        BufferedImage myPicture = ImageIO.read(new File("gameover.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        frame.add(picLabel, BorderLayout.NORTH);

    }


    private static void createButtons() {

        // create array of the buttons
        buttons = new JButton[4];
        buttons[0] = new JButton("Easy Mode");
        buttons[1] = new JButton("Medium Mode");
        buttons[2] = new JButton("Hard Mode");
        buttons[3] = new JButton("Your Score Is " + SampleGame.getScore() + "! See how you stack up.");

        // button sizing
        buttons[0].setPreferredSize(new Dimension((int) (0.33 * WIDTH), (int) (0.18 * HEIGHT)));
        buttons[1].setPreferredSize(new Dimension((int) (0.33 * WIDTH), (int) (0.18 * HEIGHT)));
        buttons[2].setPreferredSize(new Dimension((int) (0.33 * WIDTH), (int) (0.18 * HEIGHT)));
        buttons[3].setPreferredSize(new Dimension((int) (WIDTH), (int) (0.07 * HEIGHT)));

        // set up buttons
        frame.add(buttons[0], BorderLayout.WEST);
        frame.add(buttons[1], BorderLayout.CENTER);
        frame.add(buttons[2], BorderLayout.EAST);
        frame.add(buttons[3], BorderLayout.PAGE_END);

    }


    private static void createListener() { 
        // setup action listener
        ActionListener listener = new EndScreenClickListener(buttons);

        // add buttons to the listener
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(listener);
        }
    }


    public static void closeEndScreen() {
        frame.setVisible(false);
    }

}
