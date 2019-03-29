package figures;

import java.awt.Point;
import java.util.ArrayList;

public class Pawn extends IFigureHolder {
    Pawn(int pi, Point pp, String figureID, String name, String figureColor) {
        this.pi = pi;
        this.pp = pp;
        this.figureID = figureID;
        this.figureName = figureID + " " + name;
        this.figureColor = figureColor;
    }
    @Override
    public ArrayList<Integer> getAllowedPositionsIndex(int pi, Point pp) {
        ArrayList<Integer> temp = new ArrayList<>();
        if (this.figureColor.equals(BLACK_FIGURE)) {
            if (this.pp.y == 1) {
                temp.add(this.pi + 8);
                temp.add(this.pi + 16);
            } else {
                temp.add(this.pi + 8);
            }
            return temp;
        } else {
            if (this.pp.y == 6) {
                temp.add(this.pi - 8);
                temp.add(this.pi - 16);
            } else {
                temp.add(this.pi - 8);
            }
            return temp;
        }
    }
}
