package ChessGame.figures;

import java.util.ArrayList;

public class Knight extends FigureBasic {
    public Knight(String id, String displayName, boolean isBlack) {
        super(id, displayName, isBlack);
    }

    @Override
    public ArrayList<ArrayList<Integer>> moveRule() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> lul = new ArrayList<>();
        ArrayList<Integer> luu = new ArrayList<>();
        ArrayList<Integer> ruu = new ArrayList<>();
        ArrayList<Integer> rul = new ArrayList<>();
        ArrayList<Integer> rdu = new ArrayList<>();
        ArrayList<Integer> rdl = new ArrayList<>();
        ArrayList<Integer> ldl = new ArrayList<>();
        ArrayList<Integer> ldu = new ArrayList<>();

        if (this.posPoint().x - 2 >= 0 && this.posPoint().y - 1 >= 0)
            lul.add(this.posIndex() - 10);
        if (this.posPoint().x - 1 >= 0 && this.posPoint().y - 2 >= 0)
            luu.add(this.posIndex() - 17);
        if (this.posPoint().x - 2 >= 0 && this.posPoint().y + 1 <= 7)
            ruu.add(this.posIndex() + 6);
        if (this.posPoint().x - 1 >= 0 && this.posPoint().y + 2 <= 7)
            rul.add(this.posIndex() + 15);
        if (this.posPoint().x + 1 <= 7 && this.posPoint().y + 2 <= 7)
            rdu.add(this.posIndex() + 17);
        if (this.posPoint().x + 2 <= 7 && this.posPoint().y + 1 <= 7)
            rdl.add(this.posIndex() + 10);
        if (this.posPoint().x + 2 <= 7 && this.posPoint().y - 1 >= 0)
            ldl.add(this.posIndex() - 6);
        if (this.posPoint().x + 1 <= 7 && this.posPoint().y - 2 >= 0)
            ldu.add(this.posIndex() - 15);

        list.add(lul);
        list.add(luu);
        list.add(ruu);
        list.add(rul);
        list.add(rdu);
        list.add(rdl);
        list.add(ldl);
        list.add(ldu);
        return list;
    }
}
