import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class input_system implements KeyListener {
    public void keyPressed(KeyEvent e) {
        if(
            (e.getKeyCode() == KeyEvent.VK_DOWN)    ||
            (e.getKeyCode() == KeyEvent.VK_UP)      ||
            (e.getKeyCode() == KeyEvent.VK_LEFT)    ||
            (e.getKeyCode() == KeyEvent.VK_RIGHT)
        ) {
            showControlInfo = false;
            st_newGame = false;
            if (!st_gameOver && !st_pause) st_running = true;
            if (toggle_keys && st_running) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT: {
                        if (!DIR_CURRENT.equals(DIR_RIGHT))  {
                            DIR_CURRENT = DIR_LEFT;
                            toggle_keys = false;
                        }
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        if (!DIR_CURRENT.equals(DIR_LEFT))  {
                            DIR_CURRENT = DIR_RIGHT;
                            toggle_keys = false;
                        }
                        break;
                    }
                    case KeyEvent.VK_UP: {
                        if (!DIR_CURRENT.equals(DIR_DOWN))  {
                            DIR_CURRENT = DIR_UP;
                            toggle_keys = false;
                        }
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        if (!DIR_CURRENT.equals(DIR_UP))  {
                            DIR_CURRENT = DIR_DOWN;
                            toggle_keys= false;
                        }
                        break;
                    }
                }
            }
        }
        else {
            switch (e.getKeyCode()){
                case KeyEvent.VK_S: {
                    if (!st_running) {
                        st_pause = !st_pause;
                        st_running = !st_running;
                        //if (reference.timer.isRunning()) reference.timer.stop();
                        //else reference.timer.start();
                        break;
                    }
                }
                case KeyEvent.VK_G: {showGrid = true; break;}
                case KeyEvent.VK_R: {randomizeSnakeColor(); colorType_random = true; colorType_default = false; break;}
                case KeyEvent.VK_D: {colorType_default = true; colorType_random = false; break;}
                case KeyEvent.VK_I: {showGameText = !showGameText; break;}
                case KeyEvent.VK_N: {start_game(); break;}
                case KeyEvent.VK_X: {System.exit(0); break;}
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
