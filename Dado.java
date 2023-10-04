import java.util.Random;
import java.io.Serializable;

public class Dado implements Serializable{
    private int sideUp;

    public Dado(){
        sideUp = 1;
    }

    public void roll(){
        Random x = new Random();
        sideUp = x.nextInt(6) + 1;
    }

    public int getSideUp(){
        return sideUp;
    }

    public String toString(){
        return sideUp + "-";
    }
}
