package figures;


import java.awt.Point;
import java.util.ArrayList;


public class King extends IFigureHolder {
    public King(int pi, Point pp, String figureID, String name, String figureColor) {
        this.pi = pi;
        this.pp = pp;
        this.figureID = figureID;
        this.figureName = figureID + " " + name;
        this.figureColor = figureColor;
    }
    King(){}
    @Override
    public ArrayList<Integer> getAllowedPositionsIndex(int pi, Point pp) {
        ArrayList<Integer> temp = new ArrayList<>();
        int pi_ = pi;
        Point pp_ = pp;
        if (pi == -1 && pp == null){
            pi_ = this.pi;
            pp_ = this.pp;
        }
        if (pp_.y >= 0) {
            if (pi_ - 9 >= 1) temp.add(pi_ - 9);
            if (pi_ - 8 >= 1) temp.add(pi_ - 8);
            if (pi_ - 7 >= 1) temp.add(pi_ - 7);
        }
        if (pp_.x >= 0) {
            if (pi_ - 1 >= 1) temp.add(pi_ - 1);
        }
        if (pp_.x < 8) {
            if (pi_ + 1 <= 64) temp.add(pi_ + 1);
        }
        if (pp_.y < 8){
            if (pi_ + 7 <= 64) temp.add(pi_ + 7);
            if (pi_ + 8 <= 64) temp.add(pi_ + 8);
            if (pi_ + 9 <= 64) temp.add(pi_ + 9);
        }
        return temp;
    }
}
