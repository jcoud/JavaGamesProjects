package ChessGame.board;

import ChessGame.figures.*;
import ChessGame.utils.GameUtils;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

public class BoardSet {
    private ArrayList<Square> squareList = new ArrayList<>(64);
    private ArrayList<IFigureHolder> removedFigures = new ArrayList<>();
    private ArrayList<Integer> unExcludedSpots;
    public static final String
            PAWN    = "PAWN",
            ROOK    = "ROOK",
            BISHOP  = "BISHOP",
            KNIGHT  = "KNIGHT",
            QUEEN   = "QUEEN",
            KING    = "KING";

    public BoardSet() {
        setBoard();
        assignFigures();
    }

    private void assignFigures() {


        FigureTracker.queen_b     = new Queen( QUEEN,  "",       true);
        FigureTracker.queen_w     = new Queen( QUEEN,  "",       false);
        FigureTracker.king_b      = new King(  KING,   "",       true);
        FigureTracker.king_w      = new King(  KING,   "",       false);
        FigureTracker.rook_bl     = new Rook(  ROOK,   " left",  true);
        FigureTracker.rook_br     = new Rook(  ROOK,   " right", true);
        FigureTracker.rook_wl     = new Rook(  ROOK,   " left",  false);
        FigureTracker.rook_wr     = new Rook(  ROOK,   " right", false);
        FigureTracker.knight_bl   = new Knight(KNIGHT, " left",  true);
        FigureTracker.knight_br   = new Knight(KNIGHT, " right", true);
        FigureTracker.knight_wl   = new Knight(KNIGHT, " left",  false);
        FigureTracker.knight_wr   = new Knight(KNIGHT, " right", false);
        FigureTracker.bishop_bl   = new Bishop(BISHOP, " left",  true);
        FigureTracker.bishop_br   = new Bishop(BISHOP, " right", true);
        FigureTracker.bishop_wl   = new Bishop(BISHOP, " left",  false);
        FigureTracker.bishop_wr   = new Bishop(BISHOP, " right", false);


        /*SET BLACK FIGURES*/
        for (int i = 0; i < 8; i++)
        getSquare(i+9). setFigure(new Pawn(PAWN, (i+1)+"", true));
        getSquare(1).   setFigure(FigureTracker.rook_bl);
        getSquare(2).   setFigure(FigureTracker.knight_bl);
        getSquare(3).   setFigure(FigureTracker.bishop_bl);
        getSquare(4).   setFigure(FigureTracker.queen_b);
        getSquare(5).   setFigure(FigureTracker.king_b);
        getSquare(6).   setFigure(FigureTracker.bishop_br);
        getSquare(7).   setFigure(FigureTracker.knight_br);
        getSquare(8).   setFigure(FigureTracker.rook_br);
        /*SET WHITE FIGURES*/
        for (int i = 0; i < 8; i++)
        getSquare(i+49).setFigure(new Pawn(PAWN, (i+1)+"", false));
        getSquare(1+56).setFigure(FigureTracker.rook_wl);
        getSquare(2+56).setFigure(FigureTracker.knight_wl);
        getSquare(3+56).setFigure(FigureTracker.bishop_wl);
        getSquare(5+56).setFigure(FigureTracker.king_w);
        getSquare(4+56).setFigure(FigureTracker.queen_w);
        getSquare(6+56).setFigure(FigureTracker.bishop_wr);
        getSquare(7+56).setFigure(FigureTracker.knight_wr);
        getSquare(8+56).setFigure(FigureTracker.rook_wr);
    }

    public String print() {
        StringBuilder s = new StringBuilder("Square list: \n");
        for (Square square : squareList) {
            s.append(String.format("\t%s\n", square.toString()));
        }
        return s.toString();
    }

    public void unRemove(IFigureHolder figure) {
        removedFigures.remove(figure);
    }

    public void updateFigure(IFigureHolder figure, int idx) {
        updateFigure(figure, idx, false);
    }

    public void updateFigure(IFigureHolder figure, int idx, boolean capture) {
        if (capture) removeFigure(figure.posIndex());
        else getSquare(figure.posIndex()).clear();
        getSquare(idx).setFigure(figure);
    }

    public void swapFigure(IFigureHolder figureFrom, IFigureHolder figureTo) {
        updateFigure(figureFrom, figureTo.posIndex());
        updateFigure(figureTo, figureFrom.posIndex());
    }
    public ArrayList<Square> fields() {
        return squareList;
    }
    public void setNewFigure(IFigureHolder figure) {
        getSquare(figure.posIndex()).setFigure(figure);
    }

    public ArrayList<IFigureHolder> getRemovedFigures() {
        return removedFigures;
    }
    public void applyChanges(ArrayList<Square> newData) {
        squareList = newData;
    }
    public void removeFigure(int squareIdxIn) {
        Square s = getSquare(squareIdxIn);
        if (!s.isEmpty()) {
            removedFigures.add(s.getFigure());
        }
        s.clear();
    }
    public Square getSquare(int idx) {
        return squareList.get(idx -1);
    }


    public Point getSquarePointIfContains(Square.SquareHighlightingType type) {
        for (Square s : squareList) {
            if (s.getHlTypes().contains(type)) return s.posPoint();
        }
        return null;
    }
    public ArrayList<Square> makeSquareListCopy() {
        Iterator<Square> ii = squareList.iterator();
        ArrayList<Square> bc = new ArrayList<>();
        while (ii.hasNext()) {
            bc.add((Square) ii.next().copy());
        }
        return bc;
    }

    private void setBoard() {
        //newGame list
        for (int i = 0; i < 64; i++) squareList.add(null);
        //setup list
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int index = i + j * 8;
                if ((i+1) % 2 == 0 && j % 2 == 0) squareList.set(index, new Square(index + 1, true, null));
                else if (i % 2 == 0 && (j+1) % 2 == 0) squareList.set(index, new Square(index + 1, true, null));
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int index = i + j * 8;
                if (i % 2 == 0 && j % 2 == 0) squareList.set(index, new Square(index + 1, false, null));
                else if ((i+1) % 2 == 0 && (j+1) % 2 == 0) squareList.set(index, new Square(index + 1, false, null));
            }
        }
    }

    public boolean containsIndex(int index) {
        return squareList.contains(squareList.get(index-1));
    }
    public boolean containsFigure(IFigureHolder figure) {
        for (Square s : squareList) {
            if (s.isEmpty()) continue;
            if (s.getFigure() == figure) return true;
        }
        return false;
    }

    private ArrayList<ArrayList<Integer>> figureSpots(IFigureHolder selectedFigure) {
        ArrayList<ArrayList<Integer>> tt = new ArrayList<>();
        if (selectedFigure instanceof Pawn) {
            ArrayList<ArrayList<Integer>> q = selectedFigure.moveRule();
            ArrayList<Integer> t = new ArrayList<>();
            if (!q.get(0).isEmpty()) {
                int indexL = q.get(0).get(0);
                Square squareAtL = squareList.get(indexL - 1);
                if (!squareAtL.isEmpty() && squareAtL.getFigure().isColorBlack() != selectedFigure.isColorBlack())
                    t.add(indexL);
            }
            tt.add(t);
            t = new ArrayList<>();
            if (!q.get(1).isEmpty()) {
                int indexM = q.get(1).get(0);
                Square squareAtM = squareList.get(indexM - 1);
                if (squareAtM.isEmpty()) {
                    t.add(indexM);
                    if (q.get(1).size() > 1) {
                        indexM = q.get(1).get(1);
                        squareAtM = squareList.get(indexM - 1);
                        if (squareAtM.isEmpty()) {
                            t.add(indexM);
                        }
                    }
                }
            }
            tt.add(t);
            t = new ArrayList<>();
            if (!q.get(2).isEmpty()) {
                int indexR = q.get(2).get(0);
                Square squareAtR = squareList.get(indexR - 1);
                if (!squareAtR.isEmpty() && squareAtR.getFigure().isColorBlack() != selectedFigure.isColorBlack())
                    t.add(indexR);
            }
            tt.add(t);
        }
        else {
            for (ArrayList<Integer> dir : selectedFigure.moveRule()) {
                ArrayList<Integer> t = new ArrayList<>();
                for (Integer index : dir) {
                    Square squareAt = squareList.get(index - 1);
                    if (!squareAt.isEmpty()) {
                        IFigureHolder figure = squareAt.getFigure();
                        if (figure.isColorBlack() != selectedFigure.isColorBlack()) {
                            t.add(index);
                        }
                        break;
                    } else t.add(index);
                }
                tt.add(t);
            }
        }
        if (selectedFigure instanceof King && ((IFirstMove) selectedFigure).isFirstMove()) {
            int index = GameUtils.pointToIndex(7, selectedFigure.posPoint().y);
            Square squareAt = squareList.get(index - 1);
            if (!squareAt.isEmpty()) {
                if (squareList.get(GameUtils.pointToIndex(4, selectedFigure.posPoint().y)).isEmpty() &&
                        squareList.get(GameUtils.pointToIndex(5, selectedFigure.posPoint().y)).isEmpty()) {

                    IFigureHolder figureAt = squareAt.getFigure();
                    if (figureAt instanceof Rook) {
                        if (figureAt.isColorBlack() == selectedFigure.isColorBlack() && ((IFirstMove) figureAt).isFirstMove()) {
                            tt.get(3).add(index);
                        }
                    }
                }
            }
            index = GameUtils.pointToIndex(0, selectedFigure.posPoint().y);
            squareAt = squareList.get(index - 1);
            if (!squareAt.isEmpty()) {
                if (squareList.get(GameUtils.pointToIndex(0, selectedFigure.posPoint().y)).isEmpty() &&
                        squareList.get(GameUtils.pointToIndex(1, selectedFigure.posPoint().y)).isEmpty() &&
                        squareList.get(GameUtils.pointToIndex(2, selectedFigure.posPoint().y)).isEmpty()) {
                    IFigureHolder figureAt = squareAt.getFigure();
                    if (figureAt instanceof Rook) {
                        if (figureAt.isColorBlack() == selectedFigure.isColorBlack() && ((IFirstMove) figureAt).isFirstMove()) {
                            tt.get(7).add(index);
                        }
                    }
                }
            }
        }
        if (selectedFigure instanceof Rook && ((IFirstMove) selectedFigure).isFirstMove()) {
            int index = GameUtils.pointToIndex(4, selectedFigure.posPoint().y);
            Square squareAt = squareList.get(index - 1);
            if (!squareAt.isEmpty()) {
                if (selectedFigure.posPoint().x == 0 &&
                        squareList.get(GameUtils.pointToIndex(0, selectedFigure.posPoint().y)).isEmpty() &&
                        squareList.get(GameUtils.pointToIndex(1, selectedFigure.posPoint().y)).isEmpty() &&
                        squareList.get(GameUtils.pointToIndex(2, selectedFigure.posPoint().y)).isEmpty()) {

                    IFigureHolder figureAt = squareAt.getFigure();
                    if (figureAt instanceof King) {
                        if (figureAt.isColorBlack() == selectedFigure.isColorBlack() && ((IFirstMove) figureAt).isFirstMove()) {
                            tt.get(1).add(index);
                        }
                    }
                }
                if (selectedFigure.posPoint().x == 7 &&
                        squareList.get(GameUtils.pointToIndex(4, selectedFigure.posPoint().y)).isEmpty() &&
                        squareList.get(GameUtils.pointToIndex(5, selectedFigure.posPoint().y)).isEmpty()) {

                    IFigureHolder figureAt = squareAt.getFigure();
                    if (figureAt instanceof King) {
                        if (figureAt.isColorBlack() == selectedFigure.isColorBlack() && ((IFirstMove) figureAt).isFirstMove()) {
                            tt.get(3).add(index);
                        }
                    }
                }
            }
        }
        return tt;
    }
    public void setUnExcludedSpots(ArrayList<Integer> inclSpots) {
        this.unExcludedSpots = inclSpots;
    }

    public ArrayList<ArrayList<Integer>> getUnExcludedAvailableSpots(IFigureHolder selectedFigure) {
        return figureSpots(selectedFigure);
    }

    public ArrayList<ArrayList<Integer>> getAvailableSpots(IFigureHolder selectedFigure) {
        ArrayList<ArrayList<Integer>> s = figureSpots(selectedFigure);
        //excluding
        if (this.unExcludedSpots != null) {
            ArrayList<ArrayList<Integer>> ss = new ArrayList<>();
            s.forEach(dir -> {
                ArrayList<Integer> q = new ArrayList<>();
                for(Integer idx : dir) {
                    Square sq = squareList.get(idx - 1);
//                    if (GameLogic.getGameData().st_turn_canBeCovered)
                    boolean b1 = sq.getHlTypes().contains(Square.SquareHighlightingType.COVER_IN) && squareList.get(selectedFigure.posIndex()-1).getHlTypes().contains(Square.SquareHighlightingType.COVER_BY);
                    boolean b2 = sq.getHlTypes().contains(Square.SquareHighlightingType.RUN_TO) && selectedFigure instanceof King;
                    boolean b3 = sq.getHlTypes().contains(Square.SquareHighlightingType.ATTACKER_TARGET) && squareList.get(selectedFigure.posIndex()-1).getHlTypes().contains(Square.SquareHighlightingType.ATTACKING_FROM);
                    if (!(b1 || b2 || b3)) continue;
                    for (Integer i : this.unExcludedSpots) {
                        if (idx.equals(i)) {
                            q.add(idx);
                        }
                    }
                }
                ss.add(q);
            });
            return ss;
        }
        return s;
    }
}
