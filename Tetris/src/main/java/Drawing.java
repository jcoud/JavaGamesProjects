package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static main.java.Reference.*;

class Drawing extends JPanel {

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        setLocation(canvasMetrix);
        g2.setPaint(new GradientPaint(0, 0, Color.decode("#295f48"), canvasMetrix.width, canvasMetrix.height, Color.decode("#18392b")));
        g2.fillRect(0, 0, canvasMetrix.width, canvasMetrix.height);
        /*FIELD*/
        drawGrid(g2, 0, 0, canvasMetrix.width, canvasMetrix.height, new Color(255, 255, 255, 20));
        drawField(g2);
        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, canvasMetrix.width, canvasMetrix.height);
        /*CURRENT SHAPE*/
        new Shape(current_rotated_shape, current_shape_name)
                .paintShape(g2, dx * box, dy * box, st_show_shape_projection);
        /*NEXT RANDOM SHAPE*/
        new Shape(next_random_shape, name_list.get(shape_list.indexOf(next_random_shape)))
                .paintShape(g2, canvasMetrix.width + box * 2 + gap, gap * 3, false);
        /*TEXT*/
        Point p = drawStatsInfo(g2, canvasMetrix.width + gap, 0);
        drawControlInfo(g2, p.x, p.y + box);
        draw_status(g2);
    }

    private void drawGrid(Graphics2D g2, int x0, int y0, int width, int height, Color color) {
        g2.setColor(color);
        for (int i = 0; i <= width / box - 1; i++)
            g2.drawLine(i * box + x0, y0, i * box + x0, height + y0);
        for (int i = 0; i <= height / box - 1; i++)
            g2.drawLine(x0, i * box + y0, width + x0, i * box + y0);
    }

    private void draw_status(Graphics2D g2) {
        if (gameOver || pause) {
            g2.setColor(new Color(86, 86, 86, 181));
            g2.fillRect(0, 0, canvasMetrix.width, canvasMetrix.height);
            g2.setColor(Color.WHITE);
            if (gameOver) {
                Font font = new Font("Consolas", Font.BOLD, 30);
                g2.setFont(font);
                FontMetrics metrics = g2.getFontMetrics(font);
                String str = "Game Over";
                int fh = metrics.getHeight();
                int fw = metrics.stringWidth(str);
                g2.drawString(str, (canvasMetrix.width - fw) / 2, (canvasMetrix.height - fh) / 2);

                font = new Font("Consolas", Font.PLAIN, 17);
                g2.setFont(font);
                str = "Press N to start new";
                metrics = g2.getFontMetrics(font);
                fh = metrics.getHeight();
                fw = metrics.stringWidth(str);
                g2.drawString(str, (canvasMetrix.width - fw) / 2, (canvasMetrix.height - fh) / 2 + fh);
            } else {
                Font font = new Font("Consolas", Font.BOLD, 30);
                g2.setFont(font);
                FontMetrics metrics = g2.getFontMetrics(font);
                String str = "Pause";
                int fh = metrics.getHeight();
                int fw = metrics.stringWidth(str);
                g2.drawString(str, (canvasMetrix.width - fw) / 2, (canvasMetrix.height - fh) / 2);
            }
        }
    }

    private Point drawStatsInfo(Graphics2D g2, int x, int y) {
        Font font = new Font("Consolas", Font.PLAIN, 20);
        g2.setFont(font);
        ArrayList<String> k = new ArrayList<>();
        k.add("Next: " + name_list.get(shape_list.indexOf(next_random_shape)));
        k.add("");
        k.add("");
        k.add("");
        k.add("");
        k.add("");
        k.add("");
        k.add("");
        k.add("Level: " + level);
        k.add("Lines: " + lines);
        k.add("");
        k.add("SCORE: " + score);
        int _y = y;
        for (String s : k) {
            if (k.indexOf(s) == k.size() - 1) g2.setColor(Color.decode("#7FEED1"));
            else g2.setColor(Color.WHITE);
            _y += k.indexOf(s) + g2.getFontMetrics(font).getHeight() / 1.5;
            g2.drawString(s, x, _y);
        }
        return new Point(x, _y);
    }

    private void drawControlInfo(Graphics2D g2, int x, int y) {
        Font font = new Font("Consolas", Font.PLAIN, 15);
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        ArrayList<String> k = new ArrayList<>();
        k.add("Key control:");
        k.add("N - new game");
        k.add("S - toggle pause");
        k.add("G - show projection");
        k.add("R - randomize shape");
        k.add("C - randomize shape color");
        k.add("D - default shape color");
        k.add("F - cycle current shape");
        k.add("X - exit");
        k.add("Use arrows to move");
        k.add("shape");
        int _y = y;
        for (String s : k) {
            _y += k.indexOf(s) + g2.getFontMetrics(font).getHeight() / 1.5;
            g2.drawString(s, x, _y);
        }
        new Point(x, _y);
    }

    private void drawField(Graphics2D g2) {
        for (int i = 2; i < field[0].length - 2; i++) {
            for (int j = 0; j < field.length - 2; j++) {
                if (field[j][i] != 0) {
                    g2.setColor(Color.BLACK);
                    g2.drawRect((i - 2) * box - 1, j * box - 1, box + 1, box + 1);
                }
            }
        }
        for (int i = 2; i < field[0].length - 2; i++) {
            for (int j = 0; j < field.length - 2; j++) {
                if (field[j][i] != 0) {
                    String s = "0x000000";
                    switch (field[j][i]) {
                        case -1: {s = "0x000000"; break;}
                        case 1: {
                            s = st_show_default_shape_color ? defaultColorSheet[0] : randomColor[0];
                            break;
                        }
                        case 2: {
                            s = st_show_default_shape_color ? defaultColorSheet[1] : randomColor[1];
                            break;
                        }
                        case 3: {
                            s = st_show_default_shape_color ? defaultColorSheet[2] : randomColor[2];
                            break;
                        }
                        case 4: {
                            s = st_show_default_shape_color ? defaultColorSheet[3] : randomColor[3];
                            break;
                        }
                        case 5: {
                            s = st_show_default_shape_color ? defaultColorSheet[4] : randomColor[4];
                            break;
                        }
                        case 6: {
                            s = st_show_default_shape_color ? defaultColorSheet[5] : randomColor[5];
                            break;
                        }
                        case 7: {
                            s = st_show_default_shape_color ? defaultColorSheet[6] : randomColor[6];
                            break;
                        }
                        case 8: {
                            s = st_show_default_shape_color ? defaultColorSheet[7] : randomColor[7];
                            break;
                        }
                    }
                    g2.setColor(Color.decode(s));
                    g2.fillRect((i - 2) * box, j * box, box, box);
                    g2.setColor(new Color(0, 0, 0, 40));
                    g2.fillRect((i - 2) * box + box / 4 - 2, j * box + box / 4 - 2, box - box / 4 - 3, box - box / 4 - 3);
                }
            }
        }
    }
}

