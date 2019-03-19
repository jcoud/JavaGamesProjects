import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Drawing extends JComponent {
    private static Graphics2D g2;
    private Reference ref = new Reference();
    public void paintComponent(Graphics g){
        g2 = (Graphics2D)g;
        setLocation(ref.canvasSize);
        g2.setPaint(new GradientPaint(0,0, Color.decode("#295f48"),ref.canvasSize.width,ref.canvasSize.height, Color.decode("#18392b")));
        g2.fillRect(0,0,ref.canvasSize.width,ref.canvasSize.height);
        /*FIELD*/
        drawGrid(0,0, ref.canvasSize.width, ref.canvasSize.height, new Color(255,255,255,20));
        drawField();
        g2.setColor(Color.BLACK);
        g2.drawRect(0,0, ref.canvasSize.width, ref.canvasSize.height);
        /*CURRENT SHAPE SHADOW*/
//        new Shape(ref.current_rotated_shape, ref.current_shape_name)
//                .paintShape(g2, Logic.setShadow(ref.current_rotated_shape).x*ref.box, Logic.setShadow(ref.current_rotated_shape).y*ref.box, true);
        /*CURRENT SHAPE*/
        new Shape(ref.current_rotated_shape, ref.current_shape_name)
                .paintShape(g2, ref.dx*ref.box, ref.dy*ref.box, true);
        /*NEXT RANDOM SHAPE*/
        new Shape(ref.next_random_shape, ref.name_list.get(ref.shape_list.indexOf(ref.next_random_shape)))
                .paintShape(g2, ref.canvasSize.width + ref.box * 2 + ref.gap,ref.box, false);
        /*TEXT*/
        Point p =
                drawStatsInfo(ref.canvasSize.width + ref.gap,ref.box*5 + ref.gap*3);
        drawControlInfo(p.x, p.y + ref.box);
        draw_status();
    }
    private void drawGrid(int x0, int y0, int width, int height, Color color){
        g2.setColor(color);
        for (int i = 0; i <= width / ref.box - 1; i++)
            g2.drawLine(i * ref.box + x0,y0,i * ref.box + x0, height+y0);
        for (int i = 0; i <= height / ref.box - 1; i++)
            g2.drawLine(x0, i * ref.box+y0, width+x0, i * ref.box+y0);
    }
    private void draw_status(){
        if (ref.gameOver || ref.pause) {
            g2.setColor(new Color(86, 86, 86, 181));
            g2.fillRect(0, 0, ref.canvasSize.width, ref.canvasSize.height);
            g2.setColor(Color.WHITE);
            if (ref.gameOver) {
                Font font = new Font("Consolas", Font.BOLD, 30);
                g2.setFont(font);
                FontMetrics metrics = g2.getFontMetrics(font);
                String str = "Game Over";
                int fh = metrics.getHeight();
                int fw = metrics.stringWidth(str);
                g2.drawString(str, (ref.canvasSize.width - fw) / 2, (ref.canvasSize.height - fh) / 2);

                font = new Font("Consolas", Font.PLAIN, 17);
                g2.setFont(font);
                str = "Press N to start new";
                metrics = g2.getFontMetrics(font);
                fh = metrics.getHeight();
                fw = metrics.stringWidth(str);
                g2.drawString(str, (ref.canvasSize.width - fw) / 2, (ref.canvasSize.height - fh) / 2 + fh);
            } else {
                Font font = new Font("Consolas", Font.BOLD, 30);
                g2.setFont(font);
                FontMetrics metrics = g2.getFontMetrics(font);
                String str = "Pause";
                int fh = metrics.getHeight();
                int fw = metrics.stringWidth(str);
                g2.drawString(str, (ref.canvasSize.width - fw) / 2, (ref.canvasSize.height - fh) / 2);
            }
        }
    }

    private Point drawStatsInfo(int x, int y){
        Font font = new Font("Consolas", Font.PLAIN, 20);
        g2.setFont(font);
        ArrayList<String> k = new ArrayList<>();
        k.add("Next: " + ref.name_list.get(ref.shape_list.indexOf(ref.next_random_shape)));
        k.add("");
        k.add("Level: " + ref.level);
        k.add("Lines: " + ref.lines);
        k.add("");
        k.add("SCORE: " + ref.score);
        int _y = y;
        for (String s : k){
            if (k.indexOf(s) == k.size()-1) g2.setColor(Color.decode("#7FEED1"));
            else g2.setColor(Color.WHITE);
            _y += k.indexOf(s) + g2.getFontMetrics(font).getHeight()/1.5;
            g2.drawString(s, x, _y);
        }
        return new Point(x, _y);
    }
    private Point drawControlInfo(int x, int y){
        Font font = new Font("Consolas", Font.PLAIN, 15);
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        ArrayList<String> k = new ArrayList<>();
        k.add("Key control");
        k.add("N - new game");
        k.add("S - toggle pause");
        k.add("R - random shape");
        k.add("F - next shape");
        k.add("X - exit");
        int _y = y;
        for (String s : k){
            _y += k.indexOf(s) + g2.getFontMetrics(font).getHeight()/1.5;
            g2.drawString(s, x, _y);
        }
        return new Point(x, _y);
    }
    private void drawField(){
        for (int i = 2; i < ref.field[0].length-2; i++) {
            for (int j = 0; j < ref.field.length - 2; j++) {
                if (ref.field[j][i] != 0) {
                    g2.setColor(Color.BLACK);
                    g2.drawRect((i - 2) * ref.box - 1, j * ref.box - 1, ref.box + 1, ref.box + 1);
                }
            }
        }
        for (int i = 2; i < ref.field[0].length-2; i++) {
            for (int j = 0; j < ref.field.length-2; j++) {
                if (ref.field[j][i] != 0) {
                    switch (ref.field[j][i]){
                        case 1: {g2.setColor(Color.decode(ref.colorSheet[0])); break;}
                        case 2: {g2.setColor(Color.decode(ref.colorSheet[1])); break;}
                        case 3: {g2.setColor(Color.decode(ref.colorSheet[2])); break;}
                        case 4: {g2.setColor(Color.decode(ref.colorSheet[3])); break;}
                        case 5: {g2.setColor(Color.decode(ref.colorSheet[4])); break;}
                        case 6: {g2.setColor(Color.decode(ref.colorSheet[5])); break;}
                        case 7: {g2.setColor(Color.decode(ref.colorSheet[6])); break;}
                        case 8: {g2.setColor(Color.decode(ref.colorSheet[7])); break;}
                    }
                    g2.fillRect((i-2) * ref.box, j * ref.box, ref.box, ref.box);
                    g2.setColor(new Color(0,0,0,40));
                    g2.fillRect((i-2) * ref.box + ref.box/4 - 2, j * ref.box + ref.box/4 - 2, ref.box - ref.box/4 - 3, ref.box - ref.box/4 - 3 );
                }
            }
        }
    }
}

