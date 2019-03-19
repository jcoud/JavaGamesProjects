import javax.swing.*;
import java.awt.*;

import static main.Reference.*;

public class Tetris_main extends JFrame{
    static Drawing d;

    private Tetris_main(){
        super("The Tetris | " + Reference.VERSION);
        canvasSize = new extendPoint(10 * box,24 * box);
        canvasSize.x = gap;
        canvasSize.y = gap;
        Logic.init();
        d = new Drawing();
        add(d);
        addKeyListener(new IOKeys());
        getContentPane()
            .setPreferredSize(
                new Dimension(
                    canvasSize.width + gap*15,
                    canvasSize.height + canvasSize.y
                )
            );
        getContentPane().setBackground(BACKGROUND_COLOR);
        pack();
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        run();
    }

    private void run() {
        while (true) {
            if (!gameOver) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }

                if (!pause) {
                    if (Logic.notCollideWithDir("down")) dy++;
                    else {
                        Logic.addToField();
                        Logic.removeLine();
                        Logic.newShape();
                    }
                }
            }
            repaint();
        }
    }
    public static void main(String[] args){
        new Tetris_main();
    }

}