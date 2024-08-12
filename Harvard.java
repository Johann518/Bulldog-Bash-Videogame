/* bomb-like object */

import java.awt.*;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class Harvard extends OGFruit {

    Font Merriweather;//create the object for the Merriweather font
    Font TimesNewRoman = new Font("Times New Roman",Font.BOLD, 30);//alternative font

    public Harvard (int gameDifficulty) {
        super(gameDifficulty);
    }

    @Override
    public String toRemove() {
        return "click";
    }

    @Override
    public void paintComponent(Graphics g) {
        //initialize the font object and register it.
        g.setColor(new Color(230, 20, 60)); //Crimson Red
        g.fillOval(getX(), getY(), getHowBig(), getHowBig());
        try {
            Merriweather = Font.createFont(Font.TRUETYPE_FONT, new File("Merriweather-Light.ttf")).deriveFont(15.0f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Merriweather-Light.ttf")));
        } catch(FontFormatException | IOException e ) {
            //handle errors
        }

        g.setFont(Merriweather); //set the font to merriweather.
        g.setColor(Color.WHITE);
        g.drawString("Click Me!", getX() + getHowBig()/6, getY() + getHowBig()/2);

        if(missed) {
            if (movesMade % 2 == 0) g.setColor(Color.WHITE);
            else g.setColor(Color.RED);
            g.fillOval(getX(), 9 * getCanvasSize()/ 10, getHowBig(), getHowBig());
        }
    }

    @Override
    public int getHowBig() {
        return getCanvasSize()/ 10;
    }

    @Override
    public int clicksNeeded() {
        return 3;
    }

    @Override
    public int getPointValue() {
        return super.getPointValue()*4;
    }
    
}
