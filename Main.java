import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;

import com.googlecode.lanterna.input.Key;

/**
 * Created by Administrator on 2017-06-27.
 */
public class Main {

    public static int seconds =0;

    public static void main(String[] args) throws InterruptedException {
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.setCursorVisible(false);
        terminal.enterPrivateMode();
        char P = '\u27A4';
        int counterKeyStroke =0;
        Object lock = new Object();
        Player hero = new Player(lock);
        Board board = new Board(terminal, lock);
        CreateObstacles mt = new CreateObstacles(terminal, lock, hero);

        mt.start(); // startar tråden mt

        terminal.moveCursor((int) hero.getxCord(), (int) hero.getyCord());
        terminal.putCharacter(P);

        board.print();
        int counter = 0;


        Key key;
        while (true) {

            do {
                Thread.sleep(5);
                counterKeyStroke++;

                    if (counterKeyStroke >= 200) {
                        seconds++;
                        System.out.println("Score: " + seconds);
                        CreateObstacles.printText(50, 3, "Score: " + seconds, terminal, false);
                        counterKeyStroke = 0;
                    }

                synchronized (lock) {
                    key = terminal.readInput();
                }
                counter++;

                if (counter == 100) {
                    synchronized (lock) {
                        //remove hero's current position
                        terminal.moveCursor((int) hero.getxCord(), (int) hero.getyCord());
                        terminal.putCharacter(' ');
                    }

                    hero.modifyyCord(1);

                    synchronized (lock) {
                        terminal.moveCursor((int) hero.getxCord(), (int) hero.getyCord());
                        terminal.putCharacter(P);
                    }

                    counter = 0;
                }
            }
            while (key == null);

            if (key.getCharacter() == ' ' && key.getKind() == Key.Kind.NormalKey) {

                synchronized (lock) {
                    //remove hero's current position
                    terminal.moveCursor((int) hero.getxCord(), (int) hero.getyCord());
                    terminal.putCharacter(' ');
                }

                hero.modifyyCord(-1);

                synchronized (lock) {
                    terminal.moveCursor((int) hero.getxCord(), (int) hero.getyCord());
                    terminal.putCharacter(P);
                }
            }


        }
    }
}

