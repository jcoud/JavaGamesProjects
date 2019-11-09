package ChessGame.figures;

import java.util.ArrayList;

public class Rook extends FigureBasic implements IFirstMove {
    public Rook(String id, String displayName, boolean isBlack) {
        super(id, displayName, isBlack);
    }

    @Override
    public ArrayList<ArrayList<Integer>> moveRule() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> mu = new ArrayList<>();
        ArrayList<Integer> ml = new ArrayList<>();
        ArrayList<Integer> mr = new ArrayList<>();
        ArrayList<Integer> md = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            if (this.posPoint().x + i <= 7)
                mr.add(this.posIndex() + i);
            if (this.posPoint().x - i >= 0)
                ml.add(this.posIndex() - i);
            if (this.posPoint().y + i <= 7)
                md.add(this.posIndex() + (8 * i));
            if (this.posPoint().y - i >= 0)
                mu.add(this.posIndex() - (8 * i));
        }
        list.add(mu);
        list.add(mr);
        list.add(md);
        list.add(ml);
        return list;

    }

    @Override
    public boolean isFirstMove() {
        return firstMove;
    }

    @Override
    public void turn() {
        firstMove = false;
    }
}
