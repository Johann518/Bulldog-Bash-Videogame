import java.awt.*;

//direct dependencies: OGFruit.java

public class Cake extends OGFruit{
    int r;
    int g;
    int b;
    int x;

    int colorTimes;
    Color outer;
    Color middle;
    Color inner;
    int changeLength = 100;


    public Cake(int gameDifficulty) {
        super(gameDifficulty);
        outer = newColor();
        middle = newColor();
        inner = newColor();
        x = super.getX();
    }

    @Override
    public void paintComponent(Graphics g) {
        // outer
        g.setColor(outerColor());
        g.fillOval(getX(), getY(), getHowBig(), getHowBig());
        // middle
        g.setColor(middleColor());
        g.fillOval(getX() + getHowBig()/8, getY() + getHowBig()/8, 3*getHowBig()/4, 3*getHowBig()/4);
        // inner
        g.setColor(innerColor());
        g.fillOval(getX() + getHowBig()/4, getY() + getHowBig()/4, getHowBig()/2, getHowBig()/2);
        // draw "boola boola"
        Font TimesNewRoman = new Font("Times New Roman",Font.BOLD, getHowBig()/5);
        g.setFont(TimesNewRoman);
        g.setColor(Color.WHITE);
        g.drawString("boola", getX() + getHowBig()/3, getY() + 3* getHowBig()/8);
        g.drawString("boola", getX() + getHowBig()/3, getY() + 5 * getHowBig()/8);

        if(missed) {
            if (movesMade % 2 == 0) g.setColor(Color.WHITE);
            else g.setColor(Color.RED);
            g.fillOval(getX(), 9 * getCanvasSize()/ 10, getHowBig(), getHowBig());
        }

    }

    private Color outerColor(){
        colorTimes++;

        if (colorTimes % changeLength == 0) outer = newColor();
       
        return outer;
    }

    private Color middleColor(){
        colorTimes++;

        if (colorTimes % changeLength == 0) middle = newColor();
       
        return middle;
    }

    private Color innerColor(){
        colorTimes++;

        if (colorTimes % changeLength == 0) inner = newColor();
       
        return inner;
    }

    private Color newColor() {

        r = (int) (Math.random() * 255);
        g = (int) (Math.random() * 255);
        b = (int) (Math.random() * 255);
        return new Color(r,g,b);
       

    }

    @Override
    public int getPointValue() {
        return super.getPointValue()*5;
    }

    @Override
    public double getSpeed() {
        return (double) super.getSpeed() * 2.50;
    }

    @Override
    public int getHowBig() {
        return super.getHowBig()/5;
    }

    @Override
    public boolean dontDie() {
        return true;
    }

    @Override
    public int getX() {
        if (colorTimes % changeLength == 0) {
            x = (int)(Math.random() * getCanvasSize());
        }
        return x;
    }
}
