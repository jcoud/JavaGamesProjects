package ChessGame.board;

import ChessGame.figures.*;

public class FigureTracker {
    public static Queen queen_w, queen_b;
    public static King king_w, king_b;
    public static Rook rook_wl, rook_wr, rook_bl, rook_br;
    public static Knight knight_wl, knight_wr, knight_bl, knight_br;
    public static Bishop bishop_wl, bishop_wr, bishop_bl, bishop_br;

    public static String toString_() {
        return "FigureTracker list:\n" +
           "\t" + queen_b  .toString() + "\n" +
           "\t" + queen_w  .toString() + "\n" +
           "\t" + king_b   .toString() + "\n" +
           "\t" + king_w   .toString() + "\n" +
           "\t" + rook_bl  .toString() + "\n" +
           "\t" + rook_br  .toString() + "\n" +
           "\t" + rook_wl  .toString() + "\n" +
           "\t" + rook_wr  .toString() + "\n" +
           "\t" + knight_bl.toString() + "\n" +
           "\t" + knight_br.toString() + "\n" +
           "\t" + knight_wl.toString() + "\n" +
           "\t" + knight_wr.toString() + "\n" +
           "\t" + bishop_bl.toString() + "\n" +
           "\t" + bishop_br.toString() + "\n" +
           "\t" + bishop_wl.toString() + "\n" +
           "\t" + bishop_wr.toString();
    }
}
