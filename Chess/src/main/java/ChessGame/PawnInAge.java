package ChessGame;

import ChessGame.board.BoardSet;
import ChessGame.board.FigureTracker;
import ChessGame.figures.*;
import ChessGame.utils.DrawUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;

class PawnInAge {

    private ImageIcon[] iconlistb = new ImageIcon[]
        {
            FigureTracker.queen_b.figureIcon(),
            FigureTracker.bishop_bl.figureIcon(),
            FigureTracker.rook_bl.figureIcon(),
            FigureTracker.knight_bl.figureIcon()
        };
    private ImageIcon[] iconlistw = new ImageIcon[]
        {
            FigureTracker.queen_w.figureIcon(),
            FigureTracker.bishop_wl.figureIcon(),
            FigureTracker.rook_wl.figureIcon(),
            FigureTracker.knight_wl.figureIcon()
        };


    private JButton[] fb = new JButton[5];
    private JFrame framePopup;
    private Pawn pawn;
    private static Component repaintComponent;
    private boolean[] buttonBlocker = new boolean[4]; // true means blocked

    //figures order: queen, bishop, rook, knight
    private static PawnInAge instance = new PawnInAge();
    private int exchangeTo;
    private IFigureHolder capturedFigure;



    private enum ActionString { QUEEN, BISHOP, ROOK, KNIGHT, SKIP }

    static PawnInAge getInstance() {
        return instance;
    }


    Icon alineIcon(ImageIcon imageIcon) {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                g.drawImage(imageIcon.getImage(), c.getWidth()/2 - getIconWidth()/2, c.getHeight()/2 - getIconHeight()/2, null);
            }

            @Override
            public int getIconWidth() {
                return imageIcon.getIconWidth();
            }

            @Override
            public int getIconHeight() {
                return imageIcon.getIconHeight();
            }
        };
    }

    static void setRepaintComponent(Component c) {
        repaintComponent = c;
    }

    private PawnInAge() {
        instance = this;
    }
    PawnInAge buildData(Pawn pawn, IFigureHolder capturedFigure) {
        this.pawn = pawn;
        this.capturedFigure = capturedFigure;
        return this;
    }
    PawnInAge buildData(Pawn pawn, int idxTo) {
        this.pawn = pawn;
        this.exchangeTo = idxTo;
        return this;
    }

    private void setFigure(Class<? extends FigureBasic> fclazz) {
        IFigureHolder f;
        String s;
        if (fclazz.equals(Queen.class)) s = BoardSet.QUEEN;
        else if (fclazz.equals(Rook.class)) s = BoardSet.ROOK;
        else if (fclazz.equals(Bishop.class)) s = BoardSet.BISHOP;
        else if (fclazz.equals(Knight.class)) s = BoardSet.KNIGHT;
        else return;
        try {
            f = fclazz.getDeclaredConstructor(String.class, String.class, boolean.class).newInstance(s, "", pawn.isColorBlack());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }
        GameLogic gl = GameLogic.getInstance();
        f.setSquareSelf(gl.boardData.getSquare(exchangeTo));
        System.out.println(f);
        Pawn pc = (Pawn) pawn.copy();
        gl.boardData.swapFigure(pawn, f);
        gl.moveDataList.add(gl.buildMoveData().Exchange(pawn, pc, capturedFigure));
        repaintComponent.repaint();
    }
    void initFrame() {
        framePopup = new JFrame();
        framePopup.setSize(new Dimension(DrawUtils.BS * 5, DrawUtils.BS));
        framePopup.setLayout(new GridLayout(0,5));
        ActionListener action = (e) -> {
            switch (ActionString.valueOf(e.getActionCommand())) {
                case QUEEN:
                    if (!buttonBlocker[0]) setFigure(Queen.class);
                    break;
                case BISHOP:
                    if (!buttonBlocker[1]) setFigure(Bishop.class);
                    break;
                case ROOK:
                    if (!buttonBlocker[2]) setFigure(Rook.class);
                    break;
                case KNIGHT:
                    if (!buttonBlocker[3]) setFigure(Knight.class);
                    break;
                case SKIP:
                    IFigureHolder pc = pawn.copy();
                    GameLogic gl = GameLogic.getInstance();
                    gl.boardData.updateFigure(pawn, exchangeTo);
                    gl.moveDataList.add(gl.buildMoveData().Moving(pc, pawn));
                    break;
            }
            framePopup.setVisible(false);
            InOut.MouseIOAdapter.aBooleanP.set(true);
            synchronized (InOut.MouseIOAdapter.thread) {
                InOut.MouseIOAdapter.thread.notify();
            }
        };
        for (int i = 0; i < 5; i++) {
            fb[i] = new JButton(ActionString.values()[i].name());
            fb[i].addActionListener(action);
            fb[i].setPreferredSize(new Dimension(DrawUtils.BS, DrawUtils.BS));
//            fb[i].setBackground(Color.WHITE);
            framePopup.getContentPane().add(fb[i]);
        }
        framePopup.setVisible(false);
        framePopup.setResizable(false);
        framePopup.setLocationRelativeTo(null);
        framePopup.pack();
    }
    private void updateButtonBlockerList() {
        GameLogic gm = GameLogic.getInstance();
        if (pawn.isColorBlack()) {
            buttonBlocker[0] = !gm.boardData.getRemovedFigures().contains(FigureTracker.queen_b);
            buttonBlocker[1] = !gm.boardData.getRemovedFigures().contains(FigureTracker.bishop_bl) && !gm.boardData.getRemovedFigures().contains(FigureTracker.bishop_br);
            buttonBlocker[2] = !gm.boardData.getRemovedFigures().contains(FigureTracker.rook_bl)   && !gm.boardData.getRemovedFigures().contains(FigureTracker.rook_br);
            buttonBlocker[3] = !gm.boardData.getRemovedFigures().contains(FigureTracker.knight_bl) && !gm.boardData.getRemovedFigures().contains(FigureTracker.knight_br);
        }
        else {
            buttonBlocker[0] = !gm.boardData.getRemovedFigures().contains(FigureTracker.queen_w);
            buttonBlocker[1] = !gm.boardData.getRemovedFigures().contains(FigureTracker.bishop_wl) && !gm.boardData.getRemovedFigures().contains(FigureTracker.bishop_wr);
            buttonBlocker[2] = !gm.boardData.getRemovedFigures().contains(FigureTracker.rook_wl)   && !gm.boardData.getRemovedFigures().contains(FigureTracker.rook_wr);
            buttonBlocker[3] = !gm.boardData.getRemovedFigures().contains(FigureTracker.knight_wl) && !gm.boardData.getRemovedFigures().contains(FigureTracker.knight_wr);
        }
    }
    private void updatePaint() {

        for (int i=0; i<4; i++) {
            fb[i].setIcon(pawn.isColorBlack() ? alineIcon(iconlistb[i]) : alineIcon(iconlistw[i]));
            if (!buttonBlocker[i]) continue;
            fb[i].setForeground(new Color(200, 200, 200, 50));
            fb[i].setEnabled(false);
        }
    }

    void request() {
        updateButtonBlockerList();
        updatePaint();
        InOut.MouseIOAdapter.aBooleanP.set(false);
        framePopup.setVisible(true);
        synchronized (InOut.MouseIOAdapter.thread){
            try {
                InOut.MouseIOAdapter.thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
