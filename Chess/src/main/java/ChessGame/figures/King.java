package ChessGame.figures;


import java.util.ArrayList;


public class King extends FigureBasic implements IFirstMove {
    public King(String id, String displayName, boolean isBlack) {
        super(id, displayName, isBlack);
    }

    @Override
    public boolean isFirstMove() {
        return firstMove;
    }

    @Override
    public ArrayList<ArrayList<Integer>> moveRule() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> lu = new ArrayList<>();
        ArrayList<Integer> mu = new ArrayList<>();
        ArrayList<Integer> ru = new ArrayList<>();
        ArrayList<Integer> ml = new ArrayList<>();
        ArrayList<Integer> mr = new ArrayList<>();
        ArrayList<Integer> ld = new ArrayList<>();
        ArrayList<Integer> md = new ArrayList<>();
        ArrayList<Integer> rd = new ArrayList<>();

        if (this.posPoint().y >= 0) {
            if (this.posIndex() - 9 >= 1) lu.add(this.posIndex() - 9);
            if (this.posIndex() - 8 >= 1) mu.add(this.posIndex() - 8);
            if (this.posIndex() - 7 >= 1) ru.add(this.posIndex() - 7);
        }
        if (this.posPoint().x >= 0) {
            if (this.posIndex() - 1 >= 1) ml.add(this.posIndex() - 1);
        }
        if (this.posPoint().x < 8) {
            if (this.posIndex() + 1 <= 64) mr.add(this.posIndex() + 1);
        }
        if (this.posPoint().y < 8){
            if (this.posIndex() + 7 <= 64) ld.add(this.posIndex() + 7);
            if (this.posIndex() + 8 <= 64) md.add(this.posIndex() + 8);
            if (this.posIndex() + 9 <= 64) rd.add(this.posIndex() + 9);
        }
        list.add(lu);
        list.add(mu);
        list.add(ru);
        list.add(mr);
        list.add(rd);
        list.add(md);
        list.add(ld);
        list.add(ml);
        return list;
    }

    @Override
    public void turn() {
        firstMove = false;
    }
}
