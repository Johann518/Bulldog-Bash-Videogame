/* listener object for mouse action for fruits */

import java.awt.event.*;


public class FruitListener implements MouseListener {
    public void mouseClicked (MouseEvent event) {
        System.out.println("mouse clicked");
    }
    public void mouseExited (MouseEvent event) {
        System.out.println("mouse exited");
    }
    public void mouseEntered (MouseEvent event) {
        System.out.println("mouse entered");
    }
    public void mouseReleased (MouseEvent event) {
        System.out.println("mouse released");
    }
    public void mousePressed (MouseEvent event) {
        System.out.println("mouse pressed");
    }
}
