
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;

// ISSUES: 

public class HomeScreenClickListener implements ActionListener {

    private JButton[] buttons; // 4 buttons    
    
    public HomeScreenClickListener(JButton[] buttons) {
        this.buttons = buttons;
    }
    
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == buttons[0]) {
            GetName.main(1);
        }

        // to get this to work, kill the functionality of the game on its own 
        else if (event.getSource() == buttons[1]) {
            GetName.main(2);
        }

        else if (event.getSource() == buttons[2]) {
            GetName.main(3);
        }
        
        else if (event.getSource() == buttons[3]) {
            try {
                HighScoreMenu.main();
            } catch (IOException e) {
                System.out.print("Please make sure file \"highscores.png\" is in the folder");
            }
        }

        HomeScreen.closeHomeScreen();
    }
}