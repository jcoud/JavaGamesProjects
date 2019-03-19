import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class DotsMain {
    final int bs = 20;
    final int gap = 10;
    final Point canvas = new Point(bs*30,bs*30);
    ArrayList<Point> field = new ArrayList<>();
    Point mouseCoords = new Point(0,0);
    {
        set();
        JFrame jFrame = new JFrame("Dots Game");
        JFrame.setDefaultLookAndFeelDecorated(true);
        jFrame.getContentPane().setPreferredSize(new Dimension(gap*2 + canvas.x,gap*2 + canvas.y));
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_X) System.exit(0);
            }
        });
        jFrame.add(new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                setBounds(gap,gap,canvas.x,canvas.y);
//                g.translate(gap,gap);
                Graphics2D g2 = (Graphics2D) g;
                setBackground(Color.GRAY);
                g2.fillRect(0,0, getWidth(), getHeight());
//                g2.drawRect();
                field.forEach(point -> {
//                    if (mouseCoords.x-bs*5/2 > point.x)
//                        g2.setColor(Color.GREEN);
                    g2.setColor(Color.CYAN);
                    g2.fillOval(point.x*bs, point.y*bs, 2, 2);
                });
                g2.setColor(Color.RED);
                g2.drawOval(mouseCoords.x, mouseCoords.y, bs+bs/2,bs+bs/2);
                g2.drawRect(mouseCoords.x, mouseCoords.y, bs+bs/2,bs+bs/2);
            }
        });
        jFrame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseCoords.setLocation(e.getX()-(bs+bs/2), e.getY()-(bs*2+bs/2));
                jFrame.getContentPane().repaint();
            }
        });
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);

    }
    void set(){
        for (int i = 0; i < canvas.x / bs; i++) {
            for (int j = 0; j < canvas.y / bs; j++) {
                field.add(new Point(i,j));
            }
        }
    }
    public static void main(String[] s){
        new DotsMain();
    }
}
