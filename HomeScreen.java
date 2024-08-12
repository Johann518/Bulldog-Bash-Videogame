// This should implement the Home Screen for Bulldog Blitz

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.JTextField;

// ISSUES:
    // How to center in the user's screen?
    // Dimensions of the game
    // ADD THE DIFFICULTY INTO THE GAME 
        // should have three buttons, easy medium and hard on the home screen
            // buttons get read, depending on what is pressed you pass a certain difficulty into the sample game main method
                // the sample game passes it through to the fruit, who have the information about the difficulty

public class HomeScreen {

    final static int HEIGHT = 480;
    final static int WIDTH = 720;
    static JFrame frame;
    static JButton[] buttons;


    public static void main(String[] args) throws IOException {

        // setup the frame
        createFrame();

        // setup the buttons
        createButtons();

        // create listener
        createListener();

        // set frame to be visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private static void createFrame() throws IOException {

        // setup frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setTitle("Welcome to Bulldog Blitz! Please select an option below:");

        // setup layout
        frame.setLayout(new BorderLayout());

        // get image for layout
        BufferedImage myPicture = ImageIO.read(new File("bulldogblitz.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        frame.add(picLabel, BorderLayout.NORTH);

    }

    private static void createButtons(){

        // create array of buttons
        buttons = new JButton[4];
        buttons[0] = new JButton("Easy Mode");
        buttons[1] = new JButton("Medium Mode");
        buttons[2] = new JButton("Hard Mode");
        buttons[3] = new JButton("View High Scores");

        // change button size
        buttons[0].setPreferredSize(new Dimension((int) (0.33 * WIDTH), (int) (0.18 * HEIGHT)));
        buttons[1].setPreferredSize(new Dimension((int) (0.33 * WIDTH), (int) (0.18 * HEIGHT)));
        buttons[2].setPreferredSize(new Dimension((int) (0.33 * WIDTH), (int) (0.18 * HEIGHT)));
        buttons[3].setPreferredSize(new Dimension((int) (WIDTH), (int) (0.07 * HEIGHT)));

        // set up buttons, left to right on bottom
        frame.add(buttons[0], BorderLayout.WEST);
        frame.add(buttons[1], BorderLayout.CENTER);
        frame.add(buttons[2], BorderLayout.EAST);
        frame.add(buttons[3], BorderLayout.PAGE_END);

    }

    private static void createListener() {
        // setup action listener
        ActionListener listener = new HomeScreenClickListener(buttons);

        // add buttons to the listener
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(listener);
        }

    }

    public static void closeHomeScreen(){
        frame.setVisible(false);
    }

}
