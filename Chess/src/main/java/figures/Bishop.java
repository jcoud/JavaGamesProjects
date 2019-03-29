package figures;

import java.awt.Point;
import java.util.ArrayList;

public class Bishop extends IFigureHolder {
    Bishop(int pi, Point pp, String figureID, String name, String figureColor) {
        this.pi = pi;
        this.pp = pp;
        this.figureID = figureID;
        this.figureName = figureID + " " + name;
        this.figureColor = figureColor;
    }
    Bishop(){}
    @Override
    public ArrayList<Integer> getAllowedPositionsIndex(int pi, Point pp) {
        ArrayList<Integer> temp = new ArrayList<>();
        int pi_ = pi;
        Point pp_ = pp;
        if (pi == -1 && pp == null){
            pi_ = this.pi;
            pp_ = this.pp;
        }
        for (int i = 1; i <= 8; i++) {
            if (pp_.x - i >= 0 && pp_.y - i >= 0)
                temp.add(pi_ - (9 * i));
            if (pp_.x + i <= 7 && pp_.y + i <= 7)
                temp.add(pi_ + (9 * i));
            if (pp_.x + i <= 7 && pp_.y - i >= 0)
                temp.add(pi_ - (7 * i));
            if (pp_.x - i >= 0 && pp_.y + i <= 7)
                temp.add(pi_ + (7 * i));
        }
        return temp;
    }
}
