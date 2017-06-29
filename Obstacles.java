import java.util.Random;

/**
 * Created by Administrator on 2017-06-27.
 */
public class Obstacles extends Thread{

    //static int counter = 65;

    int x;
    int y;
    char c;
    Random random = new Random();
    public Object lock;

    public Obstacles(Object lock) {
        x = 99;
        y = random.nextInt(21) + 5;
        c = '\u2B23';
        //c = (char)counter++;
        this.lock = lock;

    }
    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
    public void modifyX(int x) {
        this.x += x;
    }
    public char returnEnemyChar(){
        return c;
    }
}
