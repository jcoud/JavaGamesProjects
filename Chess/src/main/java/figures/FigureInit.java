package figures;

import java.awt.Point;

public class FigureInit {
    public static Pawn[] pawn = new Pawn[16];
    public static Rook[] rook = new Rook[4];
    public static King[] king = new King[2];
    public static Knight[] knight = new Knight[4];
    public static Bishop[] bishop = new Bishop[4];
    public static Queen[] queen = new Queen[2];
    static {
        /*SET BLACK FIGURES*/
        for (int i = 0; i < 8; i++)
        pawn[i]     = new Pawn  (i+9,   new Point(i,1),   IFigureHolder.PAWN, Integer.toString(i+1),IFigureHolder.BLACK_FIGURE);
        rook[0]     = new Rook  (1,     new Point(0,0), IFigureHolder.ROOK, " left",          IFigureHolder.BLACK_FIGURE);
        knight[0]   = new Knight(2,     new Point(1,0), IFigureHolder.KNIGHT, " left",        IFigureHolder.BLACK_FIGURE);
        bishop[0]   = new Bishop(3,     new Point(2,0), IFigureHolder.BISHOP, " left",        IFigureHolder.BLACK_FIGURE);
        queen[0]    = new Queen (4,     new Point(3,0), IFigureHolder.QUEEN, "",              IFigureHolder.BLACK_FIGURE);
        king[0]     = new King  (5,     new Point(4,0), IFigureHolder.KING, "",               IFigureHolder.BLACK_FIGURE);
        bishop[1]   = new Bishop(6,     new Point(5,0), IFigureHolder.BISHOP, " right",       IFigureHolder.BLACK_FIGURE);
        knight[1]   = new Knight(7,     new Point(6,0), IFigureHolder.KNIGHT, " right",       IFigureHolder.BLACK_FIGURE);
        rook[1]     = new Rook  (8,     new Point(7,0), IFigureHolder.ROOK, " right",         IFigureHolder.BLACK_FIGURE);
        /*SET WHITE FIGURES*/
        for (int i = 0; i < 8; i++)
        pawn[i+8]   = new Pawn  (i+49,  new Point(i,6),   IFigureHolder.PAWN, Integer.toString(i+1),IFigureHolder.WHITE_FIGURE);
        rook[2]     = new Rook  (1+56,  new Point(0,7), IFigureHolder.ROOK, " left",          IFigureHolder.WHITE_FIGURE);
        knight[2]   = new Knight(2+56,  new Point(1,7), IFigureHolder.KNIGHT, " left",        IFigureHolder.WHITE_FIGURE);
        bishop[2]   = new Bishop(3+56,  new Point(2,7), IFigureHolder.BISHOP, " left",        IFigureHolder.WHITE_FIGURE);
        queen[1]    = new Queen (4+56,  new Point(3,7), IFigureHolder.QUEEN, "",              IFigureHolder.WHITE_FIGURE);
        king[1]     = new King  (5+56,  new Point(4,7), IFigureHolder.KING, "",               IFigureHolder.WHITE_FIGURE);
        bishop[3]   = new Bishop(6+56,  new Point(5,7), IFigureHolder.BISHOP, " right",       IFigureHolder.WHITE_FIGURE);
        knight[3]   = new Knight(7+56,  new Point(6,7), IFigureHolder.KNIGHT, " right",       IFigureHolder.WHITE_FIGURE);
        rook[3]     = new Rook  (8+56,  new Point(7,7), IFigureHolder.ROOK, " right",         IFigureHolder.WHITE_FIGURE);
    }
}
