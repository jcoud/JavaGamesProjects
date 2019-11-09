package ChessGame.figures;

import ChessGame.ChessMain;
import ChessGame.board.Square;
import ChessGame.utils.GameUtils;

import javax.swing.*;
import java.awt.*;

public abstract class FigureBasic implements IFigureHolder {

    /*
    *       | luu |    | ruu |
    *   lul | lu  | mu | ru  | rul
    *       | ml  | xx | mr  |
    *   ldu | ld  | md | rd  | rdu
    *       | ldl |    | rdl |
    *
    */

    boolean firstMove;
    private String id, prefix;
    private boolean black;
    private Square squareSelf;
//    private int index;

    /**
     *
//     * @param index is a number in board from 1 to 64
     * @param id a name of figure
     * @param prefix an unique name that defines a proper position in board
     * @param black is color of figure black as <code>true</code> or white as <code>false</code>
     */

    FigureBasic(String id, String prefix, boolean black) {
        this.id = id;
        this.prefix = prefix;
        this.black = black;
        this.firstMove = true;
    }
    @Override
    public void setSquareSelf(Square squareSelf) {
        this.squareSelf = squareSelf;
    }

    @Override
    public IFigureHolder copy() {
        try {
            return (IFigureHolder) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Point posPoint() {
        return squareSelf.posPoint();
    }

    @Override
    public int posIndex() {
        return squareSelf.posIndex();
    }

    @Override
    public String id() {
        return id;
    }

    protected String getPrefix() {
        return prefix;
    }

    @Override
    public String displayName() {
        return id + prefix + " "+ (black ? "BLACK" : "WHITE");
    }

    @Override
    public boolean isColorBlack() {
        return black;
    }

    @Override
    public ImageIcon figureIcon() {
        String fileName = "figures/"+ id + (black ? "_BLACK" : "_WHITE") + ".png";
        return new ImageIcon(ChessMain.class.getResource(fileName));
    }

    @Override
    public String toString() {
        return String.format("#%s, n:%s", GameUtils.formatFigurePos(posIndex()), displayName()) + (this instanceof IFirstMove ? String.format(" ``fm:{%b}", ((IFirstMove) this).isFirstMove()) : "");
    }
}
