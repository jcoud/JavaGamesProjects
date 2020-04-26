package SnakeGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @author Andrew Swan (a.k.a jcoud)
 * @version %I%, %G%
 * @link https://github.com/jcoud
 */


public class SnakeGame {
    private Thread thread;
    private JPanel sd;
    private AtomicBoolean threadRunning = new AtomicBoolean(true);
    static String UBUNTU_MONO_FONT;

    public void init() {
        /*SETUP*/
        JFrame frame = new JFrame("Snake Game!");
        JFrame.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            InputStream imageInputStream = getClass().getResourceAsStream("/snake_icon.png");
            BufferedImage bufferedImage = ImageIO.read(imageInputStream);
            frame.setIconImage(bufferedImage);
        } catch (IOException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        frame.setJMenuBar(new GameMenuBar());
        frame.addKeyListener(new SnakeIO());
        SnakeIO.setModule(sd);
        GameMenuBar.setModule(sd);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().setPreferredSize(DrawSheet.getDim(sd.getPreferredSize(), 5));
        frame.getContentPane().add(sd);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println(frame.getSize());
    }

    public SnakeGame() {
        DrawSheet.makeRandomColor();
        UBUNTU_MONO_FONT = Font.decode("Ubuntu Mono").getFontName();
        GameLogic gl = GameLogic.getInstance();
        gl.startNewGame();
        sd = new SnakeDrawPanel();
        Runnable run = () -> {
            int tick = 0;
            while (threadRunning.get()) {
                long a = System.nanoTime();
                tick++;
//                double c = 1f - ((float) gl.speed/100f);
                if (tick > 20000000 - (gl.level * 1000000) * 0.5f) {
                    long b = System.nanoTime();
                    long l = b - a;
                    System.out.printf("t:%s, l:%s, s:%s\r", tick, l, gl.level);
                    if (gl.st_running) gl.snakeMove();
                    tick = 0;
                    gl.step = true;
                    sd.repaint();
                }
            }
        };
        thread = new Thread(run);
        thread.start();
    }

//    public static void main(String[] args) {
//        SnakeGame sng = new SnakeGame();
//        SwingUtilities.invokeLater(sng::init);
//    }
}