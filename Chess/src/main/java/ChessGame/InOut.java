package ChessGame;

import ChessGame.board.Square;
import ChessGame.utils.DrawUtils;
import ChessGame.utils.GameUtils;

import javax.swing.*;
import java.awt.Point;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

class InOut {
    private static JPanel repaintComponent;
    static void setRepaintComponent(JPanel repaintComponent) {
        InOut.repaintComponent = repaintComponent;
    }
    static int[] keyEventList = new int[] {
            KeyEvent.VK_X, KeyEvent.VK_E, KeyEvent.VK_N,
            KeyEvent.VK_R, KeyEvent.VK_L, KeyEvent.VK_H,
            KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D,
            KeyEvent.VK_P, KeyEvent.VK_O, KeyEvent.VK_Q,
            KeyEvent.VK_F3, KeyEvent.VK_F4
    };
    static class KeysAction extends AbstractAction {
        private GameLogic gl = GameLogic.getInstance();
        private int keyCode;

        KeysAction(int keyCode) {
            this.keyCode = keyCode;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            getActionForKey(keyCode);
        }

        private void getActionForKey(int keyCode){
            switch (keyCode) {
                case KeyEvent.VK_X : System.exit(0);
                case KeyEvent.VK_E :
                    gl.boardData.removeFigure(gl.currIndex);
                    gl.st_moving = false;
                    break;
                case KeyEvent.VK_N :
                    gl.newGame();
                    break;
                case KeyEvent.VK_R :
                    DrawUtils.nextRandom();
                    break;
                case KeyEvent.VK_L :
                    gl.sw_show_connectionLines = !gl.sw_show_connectionLines;
                    break;
                case KeyEvent.VK_H :
                    gl.sw_show_squareHLType = !gl.sw_show_squareHLType;
                    break;
                case KeyEvent.VK_A :
                    gl.sw_show_rulerMaster = !gl.sw_show_rulerMaster;
                    break;
                case KeyEvent.VK_Q :
                    gl.sw_show_rulerSquares = !gl.sw_show_rulerSquares;
                    break;
                case KeyEvent.VK_D :
                    gl.sw_show_rulerDots = !gl.sw_show_rulerDots;
                    break;
                 case KeyEvent.VK_S :
                    gl.sw_show_rulerSimpleColor = !gl.sw_show_rulerSimpleColor;
                    break;
                case KeyEvent.VK_P :
                    gl.sw_gl_ignorePlayerColor = !gl.sw_gl_ignorePlayerColor;
                    break;
                case KeyEvent.VK_O :
                    gl.sw_gl_freeMove = !gl.sw_gl_freeMove;
                    break;
                case KeyEvent.VK_F3 :
                    gl.sw_sys_show_info = !gl.sw_sys_show_info;
                    break;
                case KeyEvent.VK_F4 :
                    gl.sw_sys_full_info = !gl.sw_sys_full_info;
                    break;

            }
            repaintComponent.repaint();
        }
    }

    static class MouseIOAdapter extends MouseAdapter {
        static Thread thread;
        static AtomicBoolean aBoolean;
        static AtomicBoolean aBooleanP;
        private static int X, Y;
        GameLogic gl = GameLogic.getInstance();
        {
            aBoolean = new AtomicBoolean(false);
            aBooleanP = new AtomicBoolean(true);
            thread = new Thread(()->{
                while (true) {
                    if (aBoolean.get()) {
                        int index = GameUtils.pointToIndex(new Point(X, Y));
                        gl.prevIndex = gl.currIndex;
                        gl.currIndex = index;

                        Square square = gl.boardData.getSquare(index);
                        if (gl.st_moving) gl.move();
                        else square.markSelectedAs(!square.isSelected());
                        gl.st_moving = square.isSelected() && !square.isEmpty();
                        if (gl.prevIndex != 0 && gl.prevIndex != gl.currIndex) {
                            gl.boardData.getSquare(gl.prevIndex).markSelectedAs(false);
                            gl.prevIndex = 0;
                        }
                        repaintComponent.repaint();
                        aBoolean.set(false);
                    }
                }
            });
            thread.start();

        }
        @Override
        public void mousePressed(MouseEvent mouse) {
            if (aBooleanP.get()) {
                X = (int) Math.floor(mouse.getX() / DrawUtils.BS);
                Y = (int) Math.floor(mouse.getY() / DrawUtils.BS);
                aBoolean.set(true);
                synchronized (thread) {
                    thread.notify();
                }
            }

//            System.out.printf("%s\r",FigureTracker.toString_());
//            System.out.println(gl.boardData.print());
        }
    }
}
