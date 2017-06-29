import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by Administrator on 2017-06-27.
 */
public class Board {
    private Terminal terminal;
    private Object lock;

    Board(Terminal terminal, Object lock){
        this.terminal = terminal;
        this.lock = lock;
    }

    public void print() throws InterruptedException {

        synchronized (lock) {
            for (int i = 0; i < 100; i++) {
                terminal.moveCursor(i, 4);
                terminal.putCharacter('_');
                terminal.moveCursor(i, 26);
                terminal.putCharacter('\u00AF');
            }
        }
    }
}
