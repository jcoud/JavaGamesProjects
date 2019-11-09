package ChessGame.utils;

import ChessGame.figures.*;

import java.awt.*;

public final class GameUtils {


    public static Point indexToPoint(int index) {
        index-=1;
        return new Point(index % 8, Math.floorDiv(index, 8));
    }
    public static int pointToIndex(Point point) {
        return point.x + 1 + point.y * 8;
    }
    public static int pointToIndex(int x, int y) {
        return x + 1 + y * 8;
    }

    public static String getFigureSymbol(IFigureHolder figure) {

        if (figure instanceof Pawn) return ""; //"♟";
        else if (figure instanceof Bishop) return "B"; // "♝";
        else if (figure instanceof King) return "K"; //"♚";
        else if (figure instanceof Knight) return "N"; //"♞";
        else if (figure instanceof Queen) return "Q"; //"♛";
        else if (figure instanceof Rook) return "R"; //"♜";
        else return "";
    }
    public static String formatFigurePos(int idx) {
        Point p = indexToPoint(idx);
        int i = 97 + p.x;
        return (char) i + "" + (8 - p.y);
    }
}