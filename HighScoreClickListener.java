import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

public class HighScoreClickListener implements ActionListener {

    private JButton button; // button for high score

    public HighScoreClickListener(JButton button){
        this.button = button;
    }

    public void actionPerformed(ActionEvent event) {
    if (event.getSource() == button){
            try {
                HomeScreen.main(null);
                HighScoreMenu.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}