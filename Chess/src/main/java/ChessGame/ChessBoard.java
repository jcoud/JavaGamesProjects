package ChessGame;

import ChessGame.board.Square;
import ChessGame.utils.DrawUtils;
import ChessGame.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ChessBoard extends JPanel {
    private int i = 0;
    private GameLogic gl = GameLogic.getInstance();

    public ChessBoard() {

        JPanel board = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;
                g.clearRect(0,0,getWidth(), getHeight());

                //pre-draw
                for (Square square : gl.boardData.fields()) {
                    square.draw(g2);
                }
                //post-draw
                for (Square square : gl.boardData.fields()) {
                    drawSquareHLType(g2, square);
                    if (!square.isSelected()) continue;
                    drawSquareSelection(g2);
                    showAvailableSpots(g2, square);
                }
                drawConnections(g2);
                showSystemInfo(g2);
            }
        };

        board.setPreferredSize(new Dimension(DrawUtils.Canvas.width, DrawUtils.Canvas.height));
        board.addMouseListener(new InOut.MouseIOAdapter());
        for (int i = 0; i < InOut.keyEventList.length; i++){
            board.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(InOut.keyEventList[i], 0, false), KeyEvent.getKeyText(InOut.keyEventList[i])+"_act");
            board.getActionMap().put(KeyEvent.getKeyText(InOut.keyEventList[i])+"_act", new InOut.KeysAction(InOut.keyEventList[i]));
        }
//        board.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        setSize(new Dimension(board.getPreferredSize().width + DrawUtils.GAP * 2, board.getPreferredSize().height + DrawUtils.GAP * 2));

        String s = "F3, F4 - help";
        JLabel help = new JLabel(s);
        help.setFont(new Font(ChessMain.UBUNTU_MONO_FONT, Font.BOLD, help.getFont().getSize()));
        help.setPreferredSize(new Dimension(getSize().width - DrawUtils.GAP * 2, DrawUtils.GAP));

        JPanel pl = createCornerPanel(board, false);
        JPanel pb = createCornerPanel(board, true);

        GridBagLayout ll = new GridBagLayout();
        GridBagConstraints lc = new GridBagConstraints();
        setLayout(ll);
//        setBorder(BorderFactory.createLineBorder(Color.RED));

//        lc.anchor = GridBagConstraints.WEST;
        lc.gridwidth = 2;
        lc.weightx = 1f;
        lc.insets.left = DrawUtils.GAP;
        lc.insets.right = DrawUtils.GAP;
        ll.setConstraints(help, lc);
        add(help);

        lc.insets.left = 0;
        lc.insets.right = 0;
        lc.gridwidth = 1;
        lc.gridy = 1;
        lc.weightx = 0f;
        lc.weighty = 1f;
        lc.fill = GridBagConstraints.VERTICAL;
        ll.setConstraints(pl, lc);
        add(pl);

        lc.weightx = 1f;
        lc.weighty = 1f;
        lc.gridx = 1;
        lc.insets.right = DrawUtils.GAP;
        lc.fill = GridBagConstraints.BOTH;
        ll.setConstraints(board, lc);
        add(board);

        lc.gridwidth = 2;
        lc.weightx = 1f;
        lc.insets.bottom = 0;
        lc.gridx = 0;
        lc.gridy = 2;
        lc.insets.left = DrawUtils.GAP;
        lc.fill = GridBagConstraints.HORIZONTAL;
        ll.setConstraints(pb, lc);
        add(pb);
    }

    private JPanel createCornerPanel(JPanel boardPanel, boolean bottom) {
        JPanel panel = new JPanel();
        panel.setFont(new Font(ChessMain.UBUNTU_MONO_FONT, Font.PLAIN, 20));
        if (bottom) {
            panel.setPreferredSize(new Dimension(boardPanel.getPreferredSize().width - DrawUtils.GAP * 2, DrawUtils.GAP));
            panel.setLayout(new GridLayout(0, 8));
            for (int i = 0; i < 8; i++) {
                int c = 97 + i;
                JLabel l = new JLabel((char) c + "");
                l.setVerticalAlignment(JLabel.CENTER);
                l.setFont(panel.getFont());
                l.setHorizontalAlignment(JLabel.CENTER);
                panel.add(l);
            }
        }
        else {
            panel.setPreferredSize(new Dimension(DrawUtils.GAP, boardPanel.getPreferredSize().height));
            panel.setLayout(new GridLayout(8, 0));
            for (int i = 0; i < 8; i++) {
                JLabel l = new JLabel((8-i) + "");
                l.setVerticalAlignment(JLabel.CENTER);
                l.setFont(panel.getFont());
                l.setHorizontalAlignment(JLabel.CENTER);
                panel.add(l);
            }
        }
        panel.setBackground(Color.decode("0xB47C68"));
        return panel;
    }

    private void drawSquareHLType(Graphics2D g, Square square) {
        if (!gl.sw_show_squareHLType) return;
        for (int i = 0; i < square.getHlTypes().size(); i++) {
            int x, y;
            int thickness = 4;
            Color c;
            if (square.getHlTypes().get(i) == Square.SquareHighlightingType.RUN_TO) {
                c = new Color(100, 255, 100);
                x = square.posPoint().x * DrawUtils.BS + thickness;
                y = square.posPoint().y * DrawUtils.BS + DrawUtils.BS - thickness;
            } else if (square.getHlTypes().get(i) == Square.SquareHighlightingType.ATTACKER_TARGET) {
                c = new Color(255, 100, 100);
                x = square.posPoint().x * DrawUtils.BS + thickness;
                y = square.posPoint().y * DrawUtils.BS + DrawUtils.BS - thickness;
            } else if (square.getHlTypes().get(i) == Square.SquareHighlightingType.ATTACKING_FROM) {
                c = new Color(255, 100, 100);
                x = square.posPoint().x * DrawUtils.BS + thickness;
                y = square.posPoint().y * DrawUtils.BS + (thickness * (i + 1));
            } else if (square.getHlTypes().get(i) == Square.SquareHighlightingType.COVER_BY) {
                c = new Color(255, 255, 100);
                x = square.posPoint().x * DrawUtils.BS + thickness;
                y = square.posPoint().y * DrawUtils.BS + (thickness * (i + 1));
            } else if (square.getHlTypes().get(i) == Square.SquareHighlightingType.COVER_IN) {
                c = new Color(255, 255, 100);
                x = square.posPoint().x * DrawUtils.BS + thickness;
                y = square.posPoint().y * DrawUtils.BS + DrawUtils.BS - thickness;
            } else break;
            DrawUtils.drawLineWithOutline(g, c, thickness, x, y, x + DrawUtils.BS - 8, y);
//            System.out.println(posIndex + " : " + square.getHlTypes().get(i).name());
        }
    }

    private void drawFormattedTextWithRect(Graphics2D g, Object[] str_c, int x, int y) {
        int sw = g.getFontMetrics(g.getFont()).stringWidth((String) str_c[0]);
        int sh = g.getFontMetrics(g.getFont()).getHeight();
        g.setColor(new Color(.3f, .3f, .3f, .5f));
        g.fillRect(x, y, sw, sh);
        g.setColor(Color.WHITE);
        g.drawString((String) str_c[0], x, y + sh * 0.75f);

        String s = (boolean) str_c[1] ? "true" : "false";
        int swb = g.getFontMetrics(g.getFont()).stringWidth(s);
        g.setColor(new Color(.3f, .3f, .3f, .5f));
        g.fillRect(x + sw, y, swb + 2, sh);
        g.setColor((boolean) str_c[1] ? Color.GREEN : Color.RED);
        g.drawString(s, x + sw, y + sh * 0.75f);
    }

    private void drawFormattedTextList(Graphics2D g, ArrayList<Object[]> strArr, int x, int y) {
        Font font = new Font(ChessMain.UBUNTU_MONO_FONT, Font.BOLD, 18);
        g.setFont(font);
        int sh = g.getFontMetrics(font).getHeight();
        for (Object[] o : strArr) {
            drawFormattedTextWithRect(g, o, x, y + (sh * (strArr.indexOf(o))));
        }
    }
    private void drawTextList(Graphics2D g, ArrayList<String> strArr, int x, int y) {
        g.setFont(g.getFont());
        int sh = g.getFontMetrics(g.getFont()).getHeight();
        for (String s : strArr) {
            int sw = g.getFontMetrics(g.getFont()).stringWidth(s);
            g.setColor(new Color(.3f, .3f, .3f, .5f));
            g.fillRect(x, y + (sh * strArr.indexOf(s)), sw, sh);
            g.setColor(Color.WHITE);
            g.drawString(s, x, y + (sh * strArr.indexOf(s)) + sh * 0.75f);
        }
    }

    private void showSystemInfo(Graphics2D g) {
        Font font = new Font(ChessMain.UBUNTU_MONO_FONT, Font.BOLD, 18);
        g.setFont(font);
        ArrayList<Object[]> sl = new ArrayList<>();
        if (gl.sw_sys_show_info) {
            sl.add(new Object[]{"[l] show line connection: ", gl.sw_show_connectionLines});
            sl.add(new Object[]{"[a] show figure ruler (master): ", gl.sw_show_rulerMaster});
            sl.add(new Object[]{"[s] show figure ruler (simple color style): ", gl.sw_show_rulerSimpleColor});
            sl.add(new Object[]{"[d] show figure ruler (dots style): ", gl.sw_show_rulerDots});
            sl.add(new Object[]{"[q] show figure ruler (full square style): ", gl.sw_show_rulerSquares});
            sl.add(new Object[]{"[h] show square HL type: ", gl.sw_show_squareHLType});
            sl.add(new Object[]{"[p] switch: ignore PlayerColor: ", gl.sw_gl_ignorePlayerColor});
            sl.add(new Object[]{"[o] switch: free move: ", gl.sw_gl_freeMove});
            drawFormattedTextList(g, sl, 5, 5);
        }
        if (gl.sw_sys_full_info) {
            ArrayList<String> sf = new ArrayList<>();
            sf.add("[x] close the game");
            sf.add("[e] erase figure");
            sf.add("[n] new game");
            sf.add("[r] randomize ruler colors");
            sf.add("[f3] show game keys");
            sf.add("[f4] show window keys");
            drawTextList(g, sf, 5, 5 + sl.size() * getFontMetrics(font).getHeight());
        }
    }

    private void showAvailableSpots(Graphics2D g, Square square) {
        if (!gl.sw_show_rulerMaster) return;
        if (square.isEmpty()) return;
        ArrayList<ArrayList<Integer>> q = gl.boardData.getAvailableSpots(square.getFigure());

        if (gl.sw_show_rulerSquares) {
            gl.boardData.fields().forEach(s -> {
                ArrayList<Integer> w = new ArrayList<>();
                for (ArrayList<Integer> dir : q) {
                    if (!dir.contains(s.posIndex())) continue;
                    if (gl.sw_show_rulerSimpleColor) {
                        int ii = dir.get(dir.size()-1);
                        Square sq = gl.boardData.getSquare(ii);
                        if (!sq.isEmpty() && sq.isColorBlack() == !square.getFigure().isColorBlack()) {
                            g.setColor(Color.RED);
                        }
                        else g.setColor(Color.WHITE);
                    }
                    else g.setColor(DrawUtils.getColor(q.indexOf(dir)));
                    int th = 1;
                    g.setStroke(new BasicStroke(th));
                    g.drawRect(s.posPoint().x * DrawUtils.BS + th / 2, s.posPoint().y * DrawUtils.BS + th / 2, DrawUtils.BS - th, DrawUtils.BS - th);
                    g.setColor(new Color(255, 255, 255, 40));
                    g.fillRect(s.posPoint().x * DrawUtils.BS + th / 2, s.posPoint().y * DrawUtils.BS + th / 2, DrawUtils.BS - th, DrawUtils.BS - th);
                    g.setStroke(new BasicStroke(1));

                    w.addAll(dir);
                }
                if (!w.contains(s.posIndex())) {
                    g.setColor(new Color(0, 0, 0, 70));
                    g.fillRect(s.posPoint().x * DrawUtils.BS, s.posPoint().y * DrawUtils.BS, DrawUtils.BS, DrawUtils.BS);
                }
            });
        }
        if (gl.sw_show_rulerDots) {
            int r = DrawUtils.BS / 4;
            for (ArrayList<Integer> dir : q) {
                int i = q.indexOf(dir);
                if (gl.sw_show_rulerSimpleColor) {
                    if (dir.isEmpty()) continue;
                    int ii = dir.get(dir.size()-1);
                    Square sq = gl.boardData.getSquare(ii);
                    if (!sq.isEmpty() && sq.isColorBlack() == !square.getFigure().isColorBlack()) {
                        g.setColor(Color.RED);
                    }
                    else g.setColor(Color.WHITE);
                }
                else g.setColor(DrawUtils.getColor(i));
                for (Integer index : dir) {
                    if (index > 0) {
                        Point p = GameUtils.indexToPoint(index);
                        g.fillOval(p.x * DrawUtils.BS + (DrawUtils.BS - r) / 2, p.y * DrawUtils.BS + (DrawUtils.BS - r) / 2, r, r);
                    }
                }
            }
        }
    }

    private void drawSquareSelection(Graphics2D g) {
//        Point prevP = GameUtils.indexToPoint(gl.prevIndex);
//        g.setColor(new Color(130, 230, 88));
//        g.setStroke(new BasicStroke(4));
//        g.drawRect(prevP.x * GameUtils.BS, prevP.y * GameUtils.BS, GameUtils.BS, GameUtils.BS);
//        g.setStroke(new BasicStroke(1));

        Point currP = GameUtils.indexToPoint(gl.currIndex);
        g.setColor(new Color(150, 190, 255));
        int th = 4;
        g.setStroke(new BasicStroke(th));
        g.drawRect(currP.x * DrawUtils.BS + th/2, currP.y * DrawUtils.BS + th/2 , DrawUtils.BS - th, DrawUtils.BS - th);
        g.setStroke(new BasicStroke(1));

    }

    private void drawConnections(Graphics2D g) {
        if (!gl.sw_show_connectionLines) return;
        gl.squarePare.forEach(sp -> {
            int x1 = ((Point) sp[0]).x * DrawUtils.BS + DrawUtils.BS / 2;
            int y1 = ((Point) sp[0]).y * DrawUtils.BS + DrawUtils.BS / 2;
            int x2 = ((Point) sp[1]).x * DrawUtils.BS + DrawUtils.BS / 2;
            int y2 = ((Point) sp[1]).y * DrawUtils.BS + DrawUtils.BS / 2;
            DrawUtils.drawArrowLine(g, (Color) sp[2], 2, x1, y1, x2, y2);
        });
    }

    private void grid(Graphics2D g) {
        g.setColor(new Color(255, 255, 255).darker());
        g.drawRect(0, 0, DrawUtils.Canvas.width, DrawUtils.Canvas.height);
        for (int i = 1; i <= DrawUtils.Canvas.width / DrawUtils.BS; i++)
            g.drawLine(i * DrawUtils.BS, 0, i * DrawUtils.BS, DrawUtils.Canvas.height);
        for (int i = 1; i <= DrawUtils.Canvas.height / DrawUtils.BS - 1; i++)
            g.drawLine(0, i * DrawUtils.BS, DrawUtils.Canvas.width, i * DrawUtils.BS);
    }

    @Override
    public void repaint() {
            super.repaint();
    }
}
