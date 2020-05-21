package main.java;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static main.java.Reference.*;

class IOKeys extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: {
                if (Logic.canRotate() && !gameOver && !pause)
                    current_rotated_shape = Logic.rotateShape(current_rotated_shape);
                break;
            }
            case KeyEvent.VK_DOWN: {
                if (Logic.notCollideWithDir(Logic.EnumDir.DOWN) && !gameOver && !pause) dy++;
                break;
            }
            case KeyEvent.VK_LEFT: {
                if (Logic.notCollideWithDir(Logic.EnumDir.LEFT) && !gameOver && !pause) dx--;
                break;
            }
            case KeyEvent.VK_RIGHT: {
                if (Logic.notCollideWithDir(Logic.EnumDir.RIGHT) && !gameOver && !pause) dx++;
                break;
            }
            case KeyEvent.VK_R: {
                if (!gameOver && !pause) {
                    Logic.newShape();
                }
                break;
            }
            case KeyEvent.VK_F: {
                if (!gameOver && !pause) {
                    Logic.nextShape();
                }
                break;
            }
//            case KeyEvent.VK_P: {
//                System.out.println("l++");
//                level++;
//                break;
//            }
            case KeyEvent.VK_S: {
                if (!gameOver) {
                    pause = !pause;
                }
                break;
            }
            case KeyEvent.VK_G: {
                st_show_shape_projection = !st_show_shape_projection;
                break;
            }
            case KeyEvent.VK_X:
                System.exit(0);
            case KeyEvent.VK_N: {
                Logic.newGame();
                break;
            }
            case KeyEvent.VK_C: {
                st_show_default_shape_color = false;
                randomizeShapeColor();
                break;
            }
            case KeyEvent.VK_D: {
                st_show_default_shape_color = true;
                break;
            }
        }
    }
}
