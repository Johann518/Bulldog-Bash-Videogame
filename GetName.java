
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;


public class GetName {

    private static JFrame frame;
    private static JPanel panel;    
    static String userName;
    private static int level;
    
    /// Trying to ask for name
    // DYLAN ADDING: TRY TO ASK FOR NAME FOR SCORE SAVING PURPOSES
    public static void main(int x) {
        level = x;

        frame = new JFrame();
        panel = new JPanel();
        JTextField nameBox = new JTextField(10);
        JButton nameButton = new JButton("Submit");
        JLabel outputName = new JLabel(" ");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(320,75));
        frame.setTitle("Name Entry");
        frame.setLayout(new FlowLayout());
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Type your name here"));
        panel.add(nameBox);
        panel.add(nameButton);
        frame.add(outputName);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        nameButton.addMouseListener(
            new MouseInputAdapter() {
                public void mouseClicked(MouseEvent e) {
                    userName = nameBox.getText();
                    //outputName.setText(userName);
                    // System.out.println("Name entered: " + userName);
                    panel.removeAll();
                    panel.revalidate();
                    panel.repaint();
                    panel.setVisible(false);
                    frame.setVisible(false);

                    new SampleGame(level);
                    // gameIsRunning = true;
            }
        });
        
    } //end of askName


    public static String name() { 
        return userName;
    }
}