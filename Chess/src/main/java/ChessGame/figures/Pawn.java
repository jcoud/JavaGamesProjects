package ChessGame.figures;

import java.util.ArrayList;

public class Pawn extends FigureBasic implements IFirstMove {
    public Pawn(String id, String displayName, boolean isBlack) {
        super(id, displayName, isBlack);
    }

    @Override
    public boolean isFirstMove() {
        return firstMove;
    }

    @Override
    public void turn() {
        firstMove = false;
    }

    @Override
    public ArrayList<ArrayList<Integer>> moveRule() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> lu = new ArrayList<>();
        ArrayList<Integer> mu = new ArrayList<>();
        ArrayList<Integer> ru = new ArrayList<>();
        if (!this.isColorBlack()) {
            if (this.posPoint().y - 1 >= 0) {
                if (this.posPoint().x - 1 >= 0) {
                    lu.add(this.posIndex() - 9);
                }
                if (this.posPoint().x + 1 <= 7) {
                    ru.add(this.posIndex() - 7);
                }
                mu.add(this.posIndex() - 8);
            }
            if (this.posPoint().y - 2 >= 0 && firstMove) {
                mu.add(this.posIndex() - 16);
            }
        } else {
            if (this.posPoint().y + 1 <= 7) {
                if (this.posPoint().x - 1 >= 0) {
                    lu.add(this.posIndex() + 7);
                }
                if (this.posPoint().x + 1 <= 7) {
                    ru.add(this.posIndex() + 9);
                }
                mu.add(this.posIndex() + 8);
            }
            if (this.posPoint().y + 2 <= 7 && firstMove) {
                mu.add(this.posIndex() + 16);
            }
        }
        list.add(lu);
        list.add(mu);
        list.add(ru);
        return list;
    }
}
