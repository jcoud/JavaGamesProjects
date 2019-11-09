package ChessGame.figures;

import java.util.ArrayList;

public class Queen extends FigureBasic {

    public Queen(String id, String displayName, boolean isBlack) {
        super(id, displayName, isBlack);
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

        for (int i = 1; i <= 8; i++) {
            if (this.posPoint().x - i >= 0 && this.posPoint().y - i >= 0)
                lu.add(this.posIndex() - (9 * i));
            if (this.posPoint().x + i <= 7 && this.posPoint().y + i <= 7)
                rd.add(this.posIndex() + (9 * i));
            if (this.posPoint().x + i <= 7 && this.posPoint().y - i >= 0)
                ru.add(this.posIndex() - (7 * i));
            if (this.posPoint().x - i >= 0 && this.posPoint().y + i <= 7)
                ld.add(this.posIndex() + (7 * i));
        }

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
}
