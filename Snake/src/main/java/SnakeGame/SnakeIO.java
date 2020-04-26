package SnakeGame;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeIO extends KeyAdapter {
    private GameLogic gl;
    private static JComponent repaintModule;

    static void setModule(JComponent c) {
        repaintModule = c;
    }

    SnakeIO() {
        gl = GameLogic.getInstance();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (
            (e.getKeyCode() == KeyEvent.VK_DOWN) ||
            (e.getKeyCode() == KeyEvent.VK_UP) ||
            (e.getKeyCode() == KeyEvent.VK_LEFT) ||
            (e.getKeyCode() == KeyEvent.VK_RIGHT)
        ) {
            gl.show_newGame = false;
            gl.st_running = true;
            if (gl.st_gameOver) return;
            if (gl.step) gl.step = false; else return;

        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (gl.DIR_CURR != gl.DIR_RIGHT) {
                    gl.DIR_CURR = gl.DIR_LEFT;
                }
                break;

            case KeyEvent.VK_RIGHT:
                if (gl.DIR_CURR != gl.DIR_LEFT) {
                    gl.DIR_CURR = gl.DIR_RIGHT;
                }
                break;

            case KeyEvent.VK_UP:
                if (gl.DIR_CURR != gl.DIR_DOWN) {
                    gl.DIR_CURR = gl.DIR_UP;
                }
                break;

            case KeyEvent.VK_DOWN:
                if (gl.DIR_CURR != gl.DIR_UP) {
                    gl.DIR_CURR = gl.DIR_DOWN;
                }
                break;

            case KeyEvent.VK_S:
                if (!gl.st_gameOver) {
                    gl.st_running = !gl.st_running;
                    gl.show_newGame = false;
                }
                break;

            case KeyEvent.VK_G:
                gl.show_grid = !gl.show_grid;
                break;

            case KeyEvent.VK_R:
                DrawSheet.makeRandomColor();
                gl.colorNormal = false;
                break;

            case KeyEvent.VK_D:
                gl.colorNormal = true;
                break;

            case KeyEvent.VK_I:
                gl.show_gameStats = !gl.show_gameStats;
                break;

            case KeyEvent.VK_C:
                gl.show_controlInfo = !gl.show_controlInfo;
                break;

            case KeyEvent.VK_N:
                GameLogic.getInstance().startNewGame();
                break;

            case KeyEvent.VK_X:
                System.exit(0);
                break;

        }
        repaintModule.repaint();
    }
}
