package ChessGame.figures;

import ChessGame.IDefaultHolder;
import ChessGame.board.Square;

import javax.swing.*;
import java.util.ArrayList;

public interface IFigureHolder extends IDefaultHolder {
    void setSquareSelf(Square squareSelf);

    String id();
    String displayName();
    ImageIcon figureIcon();
    ArrayList<ArrayList<Integer>> moveRule();
//    String toString();
}
