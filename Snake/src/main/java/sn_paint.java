import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static main_test.reference.*;


class sn_paint extends JComponent {
    @Override
    public void paintComponent(Graphics g) {
        setLocation(canvas_startingPoint);
        Graphics2D g2 = (Graphics2D) g;
        Font font_game_info     = new Font("Consolas",    Font.BOLD,15);
        Font font_game_status   = new Font("Verdana",     Font.BOLD,30);
        //window border lines
        g.setColor(Color.BLACK);
        g.drawRect(0,0, grid_width, grid_height);
        //
                                draw_snake              (g2);
                                draw_fruit              (g2);
        if (showGrid)           draw_grid               (g2);
        if (showControlInfo)    show_keyControlInfo     (g2,5,5, font_game_info);
        if (showGameText) {
                                draw_grid_central_line  (g2);
                                show_advancedInfo       (g2,5,5, font_game_info);
                                show_keyControlInfo     (g2,5,5, font_game_info);
                                show_advancedScoreLevel (g2,5,5, font_game_info);
        } else
                                show_scoreAndLevel      (g2,5,5, font_game_info);
        if (st_gameOver)        draw_status_rec         (g2,"GAME OVER", new Color(255, 36, 36, 46), font_game_status);
        if (st_newGame)         draw_status_rec         (g2,"NEW GAME",  new Color(36, 255, 36, 46), font_game_status);
        if (st_pause)           draw_status_rec         (g2,"PAUSE",     new Color(36, 36, 255, 46), font_game_status);

    }
    private void show_advancedScoreLevel(Graphics2D graph,int off_x, int off_y, Font font) {
        String s1 = "Score: " + score + " | Level: " + level + " Delay: " + delay + " Tick: " + tick;
        FontMetrics metrics = graph.getFontMetrics(font);
        int wdh = metrics.stringWidth(s1);
        draw_text(graph, s1, off_x + grid_width / 2 - wdh / 2, off_y, font);
    }
    private void show_scoreAndLevel(Graphics2D graph, int off_x, int off_y, Font font){
        String s1 = "Score: " + score + " | Level: " + level;
        FontMetrics metrics = graph.getFontMetrics(font);
        int wdh = metrics.stringWidth(s1);
        draw_text(graph, s1, off_x + grid_width / 2 - wdh / 2, off_y, font);
    }
    private void draw_status_rec(Graphics2D graph, String str, Color color, Font font){
        FontMetrics metrics = graph.getFontMetrics(font);
        int wdh = metrics.stringWidth(str);
        int hgt = metrics.getHeight();
        graph.setColor(color);
        graph.fillRect(0, 0, grid_width, grid_height);
        draw_text(graph, str, grid_width / 2 - wdh / 2, grid_height / 2 - hgt / 2, font);
    }
    private void show_advancedInfo(Graphics2D graph, int off_x, int off_y, Font font){
        ArrayList<String> ki = new ArrayList<>();
        ki.add("dir: "              + DIR_CURRENT);
        ki.add("head: ["            + head.x   + "," + head.y     + "]");
        ki.add("fruit: ["           + fruit.x  + "," + fruit.y    + "]");
        ki.add("snake length: "     + snakeLength);
        ki.add("is running: "       + st_running);
        ki.add("is paused: "        + st_pause);
        ki.add("is new game: "      + st_newGame);
        ki.add("is game over: "     + st_gameOver);

        for (int i=0;i<ki.size();i++) {
            draw_text(graph, ki.get(i), off_x, off_y + (i+1)*20, font);
        }
    }
    private void show_keyControlInfo(Graphics2D graph, int off_x, int off_y, Font font) {
        ArrayList<String> ki = new ArrayList<>();
        ki.add("Key control: ");
        ki.add("*  Arrow UP    | - move up");
        ki.add("*  Arrow RIGHT | - move right");
        ki.add("*  Arrow DOWN  | - move down");
        ki.add("*  Arrow LEFT  | - move left");
        ki.add("*        S     | - toggle pause");
        ki.add("*        N     | - new game");
        ki.add("*        R     | - set to random snake color");
        ki.add("*        D     | - set to default snake color");
        ki.add("*        I     | - toggle game info");
        ki.add("*        G     | - toggle grid");
        ki.add("*        X     | - exit");

        for (int i=0;i<ki.size();i++) {
            draw_text(graph, ki.get(i), off_x, off_y + grid_height-(ki.size()-i+1)*20, font);
        }
    }
    private void draw_grid(Graphics2D graph) {
        graph.setColor(new Color(210,210,210));
        for (int i = 1; i <= grid_width / bs-1; i++)    graph.drawLine(i * bs,0,i * bs, grid_height);
        for (int i = 1; i <= grid_height / bs-1; i++)   graph.drawLine(0,i * bs,    grid_width,i * bs);
    }

    private void draw_grid_central_line(Graphics graph) {
        graph.setColor(Color.BLACK);
        graph.drawLine(0,grid_width / 2,grid_width,grid_height / 2);
        graph.drawLine(grid_width / 2,0,grid_width / 2, grid_height);

    }

    private void draw_snake(Graphics2D graph) {
        Point HEAD = head;
        int BLOCK_SIZE = bs;
        Polygon poly_u =
                new Polygon(
                        new int[] {
                                HEAD.x*BLOCK_SIZE,
                                HEAD.x*BLOCK_SIZE + BLOCK_SIZE/2,
                                HEAD.x*BLOCK_SIZE + BLOCK_SIZE},
                        new int[] {
                                HEAD.y*BLOCK_SIZE + BLOCK_SIZE,
                                HEAD.y*BLOCK_SIZE,
                                HEAD.y*BLOCK_SIZE + BLOCK_SIZE},
                        3);
        Polygon poly_d =
                new Polygon(
                        new int[] {
                                HEAD.x*BLOCK_SIZE,
                                HEAD.x*BLOCK_SIZE + BLOCK_SIZE/2,
                                HEAD.x*BLOCK_SIZE + BLOCK_SIZE},
                        new int[] {
                                HEAD.y*BLOCK_SIZE,
                                HEAD.y*BLOCK_SIZE + BLOCK_SIZE,
                                HEAD.y*BLOCK_SIZE},
                        3);
        Polygon poly_l =
                new Polygon(
                        new int[] {
                                HEAD.x*BLOCK_SIZE,
                                HEAD.x*BLOCK_SIZE + BLOCK_SIZE,
                                HEAD.x*BLOCK_SIZE + BLOCK_SIZE},
                        new int[] {
                                HEAD.y*BLOCK_SIZE + BLOCK_SIZE/2,
                                HEAD.y*BLOCK_SIZE,
                                HEAD.y*BLOCK_SIZE + BLOCK_SIZE},
                        3);
        Polygon poly_r =
                new Polygon(
                        new int[] {
                                HEAD.x*BLOCK_SIZE,
                                HEAD.x*BLOCK_SIZE,
                                HEAD.x*BLOCK_SIZE + BLOCK_SIZE},
                        new int[] {
                                HEAD.y*BLOCK_SIZE,
                                HEAD.y*BLOCK_SIZE + BLOCK_SIZE,
                                HEAD.y*BLOCK_SIZE + BLOCK_SIZE/2},
                        3);
        //color for Body
        if (colorType_default)              graph.setColor(Color.BLUE);
        else if (colorType_random)          graph.setColor(rn_color_body);
        for (Point p : snake)               graph.fillRect(p.x * BLOCK_SIZE, p.y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        //color for Head
        if (colorType_default)              graph.setColor(Color.GREEN);
        else if (colorType_random)          graph.setColor(rn_color_head);
        //directed shape for Head
        if (DIR_CURRENT.equals(DIR_UP))     graph.fillPolygon(poly_u);
        if (DIR_CURRENT.equals(DIR_DOWN))   graph.fillPolygon(poly_d);
        if (DIR_CURRENT.equals(DIR_LEFT))   graph.fillPolygon(poly_l);
        if (DIR_CURRENT.equals(DIR_RIGHT))  graph.fillPolygon(poly_r);
    }

    private void draw_fruit(Graphics2D graph) {
        if (colorType_default)       graph.setColor(Color.RED);
        else if (colorType_random)   graph.setColor(rn_color_fruit);
        graph.fillOval(fruit.x * bs+1, fruit.y * bs+1, bs-2, bs-2);
    }

    private void draw_text(Graphics2D graph, String str, int x, int y, Font font) {
        FontMetrics metrics = graph.getFontMetrics(font);
        int wdh = metrics.stringWidth(str);
        int hgt = metrics.getHeight();
        graph.setColor(Color.BLACK);
        graph.setFont(font);
        graph.drawString(str, x, y + (hgt - hgt / 4));
        graph.setColor(new Color(54, 197, 236, 66));
        graph.fillRect(x, y, wdh, hgt);
    }
}
