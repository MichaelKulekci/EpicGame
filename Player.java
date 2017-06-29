/**
 * Created by Administrator on 2017-06-27.
 */
public class Player {
    private double xCord, yCord;
    public Object lock;

    public Player(Object lock) {
        xCord = 25;
        yCord = 15;
        this.lock = lock;
    }
     public void setxCord(double xCord) {
        this.xCord = xCord;
    }
    public void modifyyCord(double yCord) {
        if(this.yCord+yCord<5)
            return;
        if (this.yCord+yCord>25)
            return;
        this.yCord += yCord;
    }
    public double getxCord() {
        return xCord;
    }
    public double getyCord() {
        return yCord;
    }
}// End of main
