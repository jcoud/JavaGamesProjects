package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static SnakeGame.DrawSheet.ColorRequestFor;

public class SnakeDrawPanel extends JPanel {
    private GameLogic gl;

    SnakeDrawPanel() {
        gl = GameLogic.getInstance();
        setPreferredSize(DrawSheet.getDim(DrawSheet.CanvasDim,1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g.clearRect(1, 1, DrawSheet.CanvasDim.width-2, DrawSheet.CanvasDim.height-2);
        Font font_game_info = new Font(SnakeGame.UBUNTU_MONO_FONT, Font.BOLD, 15);
        Font font_game_status = new Font(SnakeGame.UBUNTU_MONO_FONT, Font.BOLD, 30);
        //window border lines
        draw_snake(g2);
        draw_fruit(g2);
        draw_grid(g2);
        show_scoreAndLevel(g2, font_game_info);
        show_gameInfo(g2, font_game_info);
        if (gl.st_gameOver) draw_status_rec(g2, "GAME OVER", new Color(255, 36, 36, 46), font_game_status);
        else if (gl.show_newGame) draw_status_rec(g2, "NEW GAME", new Color(36, 255, 36, 46), font_game_status);
        else if (!gl.st_running) draw_status_rec(g2, "PAUSE", new Color(36, 36, 255, 46), font_game_status);

    }

    private void show_scoreAndLevel(Graphics2D graph, Font font) {
        String s1 = "Score: " + gl.score + " | Level: " + gl.level;
        FontMetrics metrics = graph.getFontMetrics(font);
        int wdh = metrics.stringWidth(s1);
        draw_text(graph, s1, 5 + DrawSheet.CanvasDim.width / 2 - wdh / 2, 5, font);
    }

    private void draw_status_rec(Graphics2D graph, String str, Color color, Font font) {
        FontMetrics metrics = graph.getFontMetrics(font);
        int wdh = metrics.stringWidth(str);
        int hgt = metrics.getHeight();
        graph.setColor(color);
        graph.fillRect(0, 0, DrawSheet.CanvasDim.width, DrawSheet.CanvasDim.height);
        draw_text(graph, str, DrawSheet.CanvasDim.width / 2 - wdh / 2, DrawSheet.CanvasDim.height / 2 - hgt / 2, font);
    }

    private void show_gameInfo(Graphics2D graph, Font font) {
        ArrayList<String> ki = new ArrayList<>();
        int fh = graph.getFontMetrics(font).getHeight();
        if (gl.show_gameStats) {
            draw_grid_central_line(graph);
            ki.add("Game Info:");
            ki.add("  dir: " + (gl.DIR_CURR == gl.DIR_LEFT ? "LEFT" : gl.DIR_CURR == gl.DIR_RIGHT ? "RIGHT" : gl.DIR_CURR == gl.DIR_DOWN ? "DOWN" : gl.DIR_CURR == gl.DIR_UP ? "UP" : ""));
            ki.add("  head: [" + gl.head.x + "," + gl.head.y + "]");
            ki.add("  fruit: [" + gl.fruit.x + "," + gl.fruit.y + "]");
            ki.add("  snake length: " + gl.snakeLength);
            ki.add("  is running: " + gl.st_running);
            ki.add("  is new game: " + gl.show_newGame);
            ki.add("  is game over: " + gl.st_gameOver);
            for (int i = 0; i < ki.size(); i++) {
                draw_text(graph, ki.get(i), 5, 5 + (i * fh), font);
            }
        }
        if (gl.show_controlInfo) {
            ArrayList<String> ko = new ArrayList<>();
            ko.add("Key control: ");
            ko.add("  [arrow up] - move up");
            ko.add("  [arrow right] - move right");
            ko.add("  [arrow down] - move down");
            ko.add("  [arrow left] - move left");
            ko.add("  [S] - toggle pause");
            ko.add("  [N] - new game");
            ko.add("  [R] - set to random snake color");
            ko.add("  [D] - set to default snake color");
            ko.add("  [I] - toggle game info");
            ko.add("  [G] - toggle grid");
            ko.add("  [X] - exit");
            for (int i = 0; i < ko.size(); i++) {
                draw_text(graph, ko.get(i), 5, 5 + (((gl.show_gameStats ? ki.size() + 1 : 0) + i) * fh), font);
            }
        }
    }

    private void draw_grid(Graphics2D graph) {
        if (!gl.show_grid) return;
        graph.setColor(new Color(210, 210, 210));
        for (int i = 1; i <= DrawSheet.CanvasDim.width / DrawSheet.BS - 1; i++)
            graph.drawLine(i * DrawSheet.BS, 0, i * DrawSheet.BS, DrawSheet.CanvasDim.height);
        for (int i = 1; i <= DrawSheet.CanvasDim.width / DrawSheet.BS - 1; i++)
            graph.drawLine(0, i * DrawSheet.BS, DrawSheet.CanvasDim.width, i * DrawSheet.BS);
    }

    private void draw_grid_central_line(Graphics graph) {
        graph.setColor(Color.BLACK);
        graph.drawLine(0, DrawSheet.CanvasDim.width / 2, DrawSheet.CanvasDim.width, DrawSheet.CanvasDim.height / 2);
        graph.drawLine(DrawSheet.CanvasDim.width / 2, 0, DrawSheet.CanvasDim.width / 2, DrawSheet.CanvasDim.height);

    }

    private void draw_snake(Graphics2D graph) {
        Polygon poly_u =
                new Polygon(
                        new int[]{
                                gl.head.x * DrawSheet.BS,
                                gl.head.x * DrawSheet.BS + DrawSheet.BS / 2,
                                gl.head.x * DrawSheet.BS + DrawSheet.BS},
                        new int[]{
                                gl.head.y * DrawSheet.BS + DrawSheet.BS,
                                gl.head.y * DrawSheet.BS,
                                gl.head.y * DrawSheet.BS + DrawSheet.BS},
                        3);
        Polygon poly_d =
                new Polygon(
                        new int[]{
                                gl.head.x * DrawSheet.BS,
                                gl.head.x * DrawSheet.BS + DrawSheet.BS / 2,
                                gl.head.x * DrawSheet.BS + DrawSheet.BS},
                        new int[]{
                                gl.head.y * DrawSheet.BS,
                                gl.head.y * DrawSheet.BS + DrawSheet.BS,
                                gl.head.y * DrawSheet.BS},
                        3);
        Polygon poly_l =
                new Polygon(
                        new int[]{
                                gl.head.x * DrawSheet.BS,
                                gl.head.x * DrawSheet.BS + DrawSheet.BS,
                                gl.head.x * DrawSheet.BS + DrawSheet.BS},
                        new int[]{
                                gl.head.y * DrawSheet.BS + DrawSheet.BS / 2,
                                gl.head.y * DrawSheet.BS,
                                gl.head.y * DrawSheet.BS + DrawSheet.BS},
                        3);
        Polygon poly_r =
                new Polygon(
                        new int[]{
                                gl.head.x * DrawSheet.BS,
                                gl.head.x * DrawSheet.BS,
                                gl.head.x * DrawSheet.BS + DrawSheet.BS},
                        new int[]{
                                gl.head.y * DrawSheet.BS,
                                gl.head.y * DrawSheet.BS + DrawSheet.BS,
                                gl.head.y * DrawSheet.BS + DrawSheet.BS / 2},
                        3);
        //color for Body
        graph.setColor(DrawSheet.requestNewColor(ColorRequestFor.BODY, gl.colorNormal));
        for (Point p : gl.snake) {
            if (p.equals(gl.head)) continue;
            graph.fillRect(p.x * DrawSheet.BS, p.y * DrawSheet.BS, DrawSheet.BS, DrawSheet.BS);
        }
        //color for Head
        graph.setColor(DrawSheet.requestNewColor(ColorRequestFor.HEAD, gl.colorNormal));
        //directed shape for Head
        if (gl.DIR_CURR == gl.DIR_UP)       graph.fillPolygon(poly_u);
        if (gl.DIR_CURR == gl.DIR_DOWN)     graph.fillPolygon(poly_d);
        if (gl.DIR_CURR == gl.DIR_LEFT)     graph.fillPolygon(poly_l);
        if (gl.DIR_CURR == gl.DIR_RIGHT)    graph.fillPolygon(poly_r);
    }

    private void draw_fruit(Graphics2D graph) {
        graph.setColor(DrawSheet.requestNewColor(ColorRequestFor.FRUIT, gl.colorNormal));
        graph.fillOval(gl.fruit.x * DrawSheet.BS + 1, gl.fruit.y * DrawSheet.BS + 1, DrawSheet.BS - 2, DrawSheet.BS - 2);
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
