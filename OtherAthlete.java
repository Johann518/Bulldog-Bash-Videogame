import java.awt.Color;

public class OtherAthlete extends Athlete {
    
    public OtherAthlete(int gameDifficulty) {
        super(gameDifficulty);
        direction = false;
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }

    @Override
    public int getX() {
        int i = (int)(Math.random()*getCanvasSize()/6);
        if(i == 0){
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
    public int getPointValue() {
        return (int)(super.getPointValue() * 1.25);
    }
}
