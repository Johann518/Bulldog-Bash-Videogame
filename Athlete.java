/* 
 */

 import java.awt.*;
 import java.awt.image.BufferedImage;

public class Athlete extends OGFruit {

    public int x;
    public int moves;
    public boolean direction;
    
    public Athlete (int gameDifficulty) {
        super(gameDifficulty);
        x = (int)(Math.random() * super.getCanvasSize() * 9 / 10);
        moves = 0;
        direction = true;
    }

    @Override
    public int getX() {
        if(moves == getCanvasSize()/3){
            moves = 0;
            direction = !direction;
        } else {
            if (direction) x = Math.floorMod(x + 1, getCanvasSize());
            else x = Math.floorMod(x - 1, getCanvasSize());
        }
        moves++;
        return x;
    }

    @Override
    public void paintComponent(Graphics g){

        g.setColor(getColor());
        if(direction) {
            // handlebar
            g.fillRect(getX() + getHowBig()/2, getY(), getHowBig()/2, getHowBig()/10);
            // stem
            g.fillRect(getX() + getHowBig() * 7/10, getY() + getHowBig()/10, getHowBig()/10, getHowBig()/2);
            // footstop
            g.fillRect(getX(), getY() + getHowBig()*3/5, getHowBig()* 4/5, getHowBig()/10);
            // wheels
            g.fillOval(getX() + getHowBig()/10, getY() + getHowBig()*7/10, getHowBig()/5, getHowBig()/5);
            g.fillOval(getX() + 6*getHowBig()/10, getY() + getHowBig()*7/10, getHowBig()/5, getHowBig()/5);
        } else {
            // handlebar
            g.fillRect(getX(), getY(), getHowBig()/2, getHowBig()/10);
            // stem
            g.fillRect(getX() + getHowBig() * 2/10, getY() + getHowBig()/10, getHowBig()/10, getHowBig()/2);
            // footstop
            g.fillRect(getX() + getHowBig()/5, getY() + getHowBig()*3/5, getHowBig()* 4/5, getHowBig()/10);
            // wheels
            g.fillOval(getX() + 3 * getHowBig()/10, getY() + getHowBig()*7/10, getHowBig()/5, getHowBig()/5);
            g.fillOval(getX() + 8*getHowBig()/10, getY() + getHowBig()*7/10, getHowBig()/5, getHowBig()/5);
        }

        if(missed) {
            if (movesMade % 2 == 0) g.setColor(Color.WHITE);
            else g.setColor(Color.RED);
            g.fillOval(getX(), 9 * getCanvasSize()/ 10, getHowBig(), getHowBig());
        }
        
    }

    @Override
    public int getHowBig() {
        return super.getHowBig()/4;
    }

    @Override
    public int getPointValue() {
        return super.getPointValue()*2;
    }

    public Color getColor() {
        return Color.BLACK;
    }

}
