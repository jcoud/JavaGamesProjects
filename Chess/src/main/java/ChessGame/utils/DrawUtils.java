package ChessGame.utils;


import java.awt.*;
import java.util.Random;

public final class DrawUtils {
    private static Color[] c;
    public static final int BS = 80, GAP = 15;
    public static Dimension Canvas  = new Dimension(BS * 8, BS * 8);
    public static Dimension MoveList  = new Dimension(BS * 4, BS * 8);

    public static void nextRandom() {
        c = new Color[8];
        Random ran = new Random();
        for (int n= 0; n < 8; n ++) {
            int r = 80 + ran.nextInt(155);
            int g = 80 + ran.nextInt(155);
            int b = 80 + ran.nextInt(155);
            c[n] = new Color(r, g, b);
        }
    }
    public static Color getColor(int index) {
        return index < c.length ? c[index] : Color.BLACK;
    }

    public static void drawLineWithOutline(Graphics2D g, Color c, int thickness, int x1, int y1, int x2, int y2) {
        g.setStroke(new BasicStroke((int) (thickness * 1.5)));
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
        g.setStroke(new BasicStroke(thickness));
        g.setColor(c);
        g.drawLine(x1, y1, x2, y2);
        g.setStroke(new BasicStroke(1));
    }
    public static void drawArrowLine(Graphics2D g, Color c, int thickness, int x1, int y1, int x2, int y2) {
        g.setColor(c);
        drawLineWithOutline(g, c, thickness, x1, y1, x2, y2);
        double a = Math.atan2(y2 - y1, x2 - x1);
        int l1_x1 = (int) (x2 - 10 * Math.cos(a + Math.toRadians(45)));
        int l1_y1 = (int) (y2 - 10 * Math.sin(a + Math.toRadians(45)));

        int l2_x1 = (int) (x2 - 10 * Math.cos(a - Math.toRadians(45)));
        int l2_y1 = (int) (y2 - 10 * Math.sin(a - Math.toRadians(45)));

        drawLineWithOutline(g, c, thickness, l1_x1, l1_y1, x2, y2);
        drawLineWithOutline(g, c, thickness, l2_x1, l2_y1, x2, y2);
    }
}
