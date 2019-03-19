import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class IOKeys extends KeyAdapter {
    private static Drawing draw = Tetris_main.d;
    private Reference ref = new Reference();
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP : {
                if (Logic.canRotate() && !ref.gameOver && !ref.pause)
                    ref.current_rotated_shape = Logic.rotateShape(ref.current_rotated_shape);
                draw.repaint();
                break;
            }
            case KeyEvent.VK_DOWN:{
                if (Logic.notCollideWithDir("down") && !ref.gameOver && !ref.pause)
                    ref.dy++;
                draw.repaint();
                break;
            }
            case KeyEvent.VK_LEFT: {
                if (Logic.notCollideWithDir("left") && !ref.gameOver && !ref.pause)
                    ref.dx--;
                draw.repaint();
                break;
            }
            case KeyEvent.VK_RIGHT: {
                if (Logic.notCollideWithDir("right") && !ref.gameOver && !ref.pause)
                    ref.dx++;
                draw.repaint();
                break;
            }
            case KeyEvent.VK_R: if (!ref.gameOver && !ref.pause) {Logic.newShape(); break;}
            case KeyEvent.VK_F: if (!ref.gameOver && !ref.pause) {Logic.nextShape(); break;}
            case KeyEvent.VK_S: if (!ref.gameOver) {ref.pause = !ref.pause; break;}
            case KeyEvent.VK_N: {Logic.newGame(); break;}
            case KeyEvent.VK_X: System.exit(0);
        }
    }
}
