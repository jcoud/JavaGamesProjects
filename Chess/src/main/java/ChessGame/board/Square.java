package ChessGame.board;

import ChessGame.IDefaultHolder;
import ChessGame.figures.IFigureHolder;
import ChessGame.utils.DrawUtils;
import ChessGame.utils.GameUtils;

import java.awt.*;
import java.util.ArrayList;

public class Square implements IDefaultHolder {
    private int posIndex;
    private boolean black;
    private IFigureHolder figure;
    private boolean selected;
    private ArrayList<SquareHighlightingType> hlTypes;



    public Square(int posIndex, boolean black, IFigureHolder figure) {
        this.posIndex = posIndex;
        this.black = black;
        this.figure = figure;
        this.selected = false;
        this.hlTypes = new ArrayList<>();
    }

    public boolean isEmpty() {
        return figure == null;
    }

    public IFigureHolder getFigure() {
        return figure;
    }

    public void setFigure(IFigureHolder figure) {
        figure.setSquareSelf(this);
        this.figure = figure;
    }

    @Override
    public IDefaultHolder copy() {
        try {
            return (IDefaultHolder) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int posIndex() {
        return posIndex;
    }

    @Override
    public Point posPoint() {
        return GameUtils.indexToPoint(posIndex);
    }

    @Override
    public boolean isColorBlack() {
        return black;
    }

    public boolean isSelected() {
        return selected;
    }

    public void markSelectedAs(boolean flag) {
        this.selected = flag;
    }

    public void draw(Graphics2D g) {
        g.setColor(black ? Color.decode("#b58869") : Color.decode("#fadcc2"));
        g.fillRect(posPoint().x * DrawUtils.BS, posPoint().y * DrawUtils.BS, DrawUtils.BS, DrawUtils.BS);
        Font ff = new Font(Font.MONOSPACED, Font.BOLD, 20);
        g.setFont(ff);
        g.setColor(black ? Color.decode("#fadcc2") : Color.decode("#b58869"));
        g.drawString(GameUtils.formatFigurePos(posIndex), posPoint().x * DrawUtils.BS, posPoint().y * DrawUtils.BS + DrawUtils.BS);
        if (!isEmpty()) {
            g.drawImage(figure.figureIcon().getImage(), posPoint().x * DrawUtils.BS, posPoint().y * DrawUtils.BS, null);
        }
    }

    @Override
    public String toString() {
        return String.format("#%s, c:%s, t:%s, f:{%s}", GameUtils.formatFigurePos(posIndex), (black ? "BLACK" : "WHITE"), hlTypes.toString(), (isEmpty() ? "EMPTY" : figure.toString()));
    }

    public ArrayList<SquareHighlightingType> getHlTypes() {
        return hlTypes;
    }

    public void addHlType(SquareHighlightingType hlType) {
        this.hlTypes.add(hlType);
    }

    public void clear() {
        this.figure = null;
    }

    public enum SquareHighlightingType {
        COVER_IN, COVER_BY, ATTACKER_TARGET, ATTACKING_FROM, RUN_TO
    }
}
