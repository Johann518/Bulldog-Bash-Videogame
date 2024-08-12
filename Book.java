
import java.awt.*;

public class Book extends OGFruit {
    
    public Book(int gameDifficulty) {
        super(gameDifficulty);
    }

    @Override
    public int getPointValue () {
        return 100;
    }

    @Override
    public int getHowBig () {
        return super.getHowBig() * 4/5;
    }

    @Override
    public void paintComponent(Graphics g) {
        if (!missed){
            g.setColor(Color.YELLOW);
            g.fillRect(getX(), getY(), getHowBig() * 3/2, getHowBig());
            g.setColor(Color.WHITE);
            g.fillRect(getX() + getHowBig()/8, getY() + getHowBig()/8, getHowBig() * 5/4, getHowBig() * 3/4);
            g.setColor(Color.BLACK);

            // text lines
            g.setColor(Color.BLACK);
            for (int i = 0; i < 6; i++) {
                g.drawLine(getX() + getHowBig()/4, getY() + (i + 1)*getHowBig()/8, getX() + 5 * getHowBig() / 8, getY() + (i + 1)*getHowBig()/8);
                g.drawLine(getX() + 7 * getHowBig()/8, getY() + (i + 1)*getHowBig()/8, getX() + 5 * getHowBig() / 4, getY() + (i + 1)*getHowBig()/8);
            } 

            // flipping page
            int flipPageX = 0;
            flipPageX = (int)(((movesMade % 50) - 15) * (double)getHowBig()/40) + getX() + getHowBig()/2;
            System.out.println(flipPageX / ((double)getHowBig()/40));
            g.setColor(Color.WHITE);
            g.fillRect(flipPageX - getHowBig()/16, getY() + getHowBig()/8, getHowBig()/8, 3*getHowBig()/4);
            g.setColor(Color.BLACK);
            g.drawLine(flipPageX, getY() + getHowBig()/8, flipPageX, getY() + getHowBig() * 7/8);
            

            g.setColor(Color.BLACK);
            g.drawRect(getX(), getY(), getHowBig() * 3/2, getHowBig());
            g.drawRect(getX() + getHowBig()/8, getY() + getHowBig()/8, getHowBig() * 5/4, getHowBig() * 3/4);
            g.drawLine(getX() + getHowBig()*3/4, getY(), getX() + getHowBig()*3/4, getY() + getHowBig());
        } else {
            if (movesMade % 2 == 0) g.setColor(Color.WHITE);
            else g.setColor(Color.RED);
            g.fillOval(getX(), getCanvasSize()/2, getHowBig(), getHowBig());
        }
        
    }

    @Override
    public double getSpeed() {
        return (double) super.getSpeed() * 1.75;
    }

    
}
