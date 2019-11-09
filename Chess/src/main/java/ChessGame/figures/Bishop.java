package ChessGame.figures;

import java.util.ArrayList;

public class Bishop extends FigureBasic {
    public Bishop(String id, String displayName, boolean isBlack) {
        super(id, displayName, isBlack);
    }

    @Override
    public ArrayList<ArrayList<Integer>> moveRule() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> lu = new ArrayList<>();
        ArrayList<Integer> rd = new ArrayList<>();
        ArrayList<Integer> ru = new ArrayList<>();
        ArrayList<Integer> ld = new ArrayList<>();

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
        list.add(lu);
        list.add(ru);
        list.add(rd);
        list.add(ld);
        return list;
    }
}
