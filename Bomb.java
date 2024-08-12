import java.awt.*;

public class Bomb extends OGFruit {

    public Bomb(int gameLevel) {
        super(gameLevel);
    }

    @Override
    public boolean clickToDie () {
        return true;
    }

    @Override
    public void paintComponent(Graphics g) {
        if(!exploded) {
            g.setColor(Color.GRAY);
            g.fillRect(getX() + getHowBig()/4, getY()-getHowBig()/4, getHowBig()/4, getHowBig());

            g.setColor(Color.BLACK);
            g.fillOval(getX(), getY(), getHowBig(), getHowBig());
            g.setColor(Color.WHITE);
            g.fillOval(getX(),getY()+getHowBig()/4,getHowBig()/2,getHowBig()/2);

            Font TimesNewRoman = new Font("Times New Roman",Font.BOLD, getHowBig()/2);
            g.setFont(TimesNewRoman);
            g.setColor(new Color(230, 20, 60)); //Crimson Red
            g.drawString("H", getX() + getHowBig()/2, getY() + 5 * getHowBig()/8);
        }
        if(exploded) {
            g.setColor(Color.GRAY);
            for (int i = 0; i < 8; i ++) {
                g.fillOval(getX() + (int)(Math.random()*getHowBig()), getY() + (int)(Math.random()*getHowBig()), 
                    (int)(Math.random()*2*getHowBig()), (int)(Math.random()*2*getHowBig()));
            }
            

        }
    }

    @Override
    public int getHowBig() {
        return (int)(getCanvasSize() * (double)movesMade / 2000);
    }

    @Override
    public boolean dontDie() {
        return true;
    }

    @Override
    public String audioName() {
        return "explosion.wav";
    }
    
}
