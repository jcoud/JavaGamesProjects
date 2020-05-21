package main.java;

import javax.swing.*;
import java.awt.*;

import static main.java.Reference.*;

public class TetrisMain extends JFrame {

    private TetrisMain() {
        super("The Tetris | " + VERSION);
        canvasMetrix = new ExtendPoint(10 * box, 24 * box);
        canvasMetrix.x = gap;
        canvasMetrix.y = gap;
        Logic.init();
        Drawing d = new Drawing();
        this.addKeyListener(new IOKeys());
        Container c = getContentPane();
        c.add(d);
//        c.setLayout(new FlowLayout(FlowLayout.CENTER));
        c.setPreferredSize(new Dimension(canvasMetrix.width + gap * 22, canvasMetrix.height + canvasMetrix.y + gap));
        c.setBackground(BACKGROUND_COLOR);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        setDefaultLookAndFeelDecorated(true);
        run();
    }

    private void run() {
        long lastTime = System.nanoTime();
        double unprocessed = 0;
        double nsPerTick = 1000000000.0 / 60.0;
        int frames = 0;
        int ticks = 0;
        long lt = System.currentTimeMillis();

        while (true) {
            long currentTime = System.nanoTime();
            unprocessed += (currentTime - lastTime) / nsPerTick;
            lastTime = currentTime;
            while (unprocessed >= 1) {
                ticks++;
                unprocessed -= 1;
            }
            {
                frames++;
                repaint();
            }

            try {
                Thread.sleep((int) ((1 - unprocessed) * 1000) / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long w = System.currentTimeMillis() - lt;
            if (w > 1000) {
                lt += 1000 / level;
                System.out.printf("ticks: %d, fps: %d\r", ticks, frames);
                frames = 0;
                ticks = 0;
                if (!gameOver && !pause) {
                    if (Logic.notCollideWithDir(Logic.EnumDir.DOWN)) dy++;
                    else {
                        Logic.addToField();
                        Logic.removeLine();
                        Logic.newShape();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new TetrisMain();
    }

}