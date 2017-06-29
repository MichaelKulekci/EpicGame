
import com.googlecode.lanterna.terminal.Terminal;


import com.googlecode.lanterna.input.Key;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2017-06-28.
 */
public class CreateObstacles extends Thread {
    Obstacles obstacles;
    List<Obstacles> enemies;
    Terminal terminal;
    private Object lock;
    Player player;


    public CreateObstacles(Terminal terminal, Object lock, Player player) {
        this.enemies = new ArrayList<>();
        this.terminal = terminal;
        this.lock = lock;
        this.player = player;
    }

    @Override //will get called when we call 'start()'
    public void run() {

        int counter = 30;

        while (true) {

            try {
                Thread.sleep(5);
                synchronized (lock) {
                    counter--;

                    if (counter == 0) {
                        obstacles = new Obstacles(lock);
                        enemies.add(obstacles);

                    }

                    for (int i = enemies.size() - 1; i >= 0; i--) {
                        Obstacles enemy = enemies.get(i);
                        if (((int) player.getxCord() == enemy.getX()) && ((int) player.getyCord() == enemy.getY())) {

                            Key key2;
                            terminal.clearScreen();
                            printText(44, 15, "GAME OVER", terminal, true);
                            playAgain(30, 20, terminal, "Press q to QUIT or enter to PLAY again");
                            do {
                                key2 = terminal.readInput();
                            }
                            while (key2 == null);
                            switch (key2.getKind().ordinal()) {
                                case 1:
                                    System.exit(0);

                                default:
                                    terminal.clearScreen();
                                    enemies.clear();

                                    Main.seconds = 0;
                                    Board board = new Board(terminal, lock);
                                    board.print();
                            }


                            //System.out.println("Press \'ENTER\' to quit...");


                        } else {

                            // move the enemies
                            if (counter == 0) {


                                terminal.moveCursor(enemy.getX(), enemy.getY());
                                terminal.putCharacter(' ');
                                enemy.modifyX(-1);
                                terminal.moveCursor(enemy.getX(), enemy.getY());
                                terminal.putCharacter(enemy.returnEnemyChar());


                                //check if the enemy is to the far left, then remove it
                                if (enemy.getX() == 0) {
                                    terminal.moveCursor(enemy.getX(), enemy.getY());
                                    terminal.putCharacter(' ');
                                    enemies.remove(i);
                                }
                            }
                        }
                    }
                    if (counter == 0) {
                        counter = 30;

                    }
                }

            } catch (Exception e) {
            }
        }
    } // end if run method (is called when we call 'start()'



    public static void printText(int x, int y, String message, Terminal terminal, boolean slow) {
        for (int i = 0; i < message.length(); i++) {
            try {
                if (slow)
                Thread.sleep(100);

                terminal.moveCursor(x++, y);
                terminal.putCharacter(message.charAt(i));
            } catch (Exception e) {
            }
        }
    }

    private void playAgain(int x, int y, Terminal terminal, String info) {
        for (int i = 0; i < info.length(); i++) {
            try {
                Thread.sleep(70);

                terminal.moveCursor(x++, y);
                terminal.putCharacter(info.charAt(i));
            } catch (Exception e) {
            }
        }

    }

} // end of class

