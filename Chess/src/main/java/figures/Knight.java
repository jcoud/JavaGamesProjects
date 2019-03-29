package figures;

import java.awt.Point;
import java.util.ArrayList;

public class Knight extends IFigureHolder {
    Knight(int pi, Point pp, String figureID, String name, String figureColor) {
        this.pi = pi;
        this.pp = pp;
        this.figureID = figureID;
        this.figureName = figureID + " " + name;
        this.figureColor = figureColor;
    }
    @Override
    public ArrayList<Integer> getAllowedPositionsIndex(int pi, Point pp) {
        ArrayList<Integer> temp = new ArrayList<>();
        if (this.pp.x - 1 >= 0 && this.pp.y - 2 >= 0)
            temp.add(this.pi - 17);
        if (this.pp.x - 2 >= 0 && this.pp.y - 1 >= 0)
            temp.add(this.pi - 10);
        if (this.pp.x + 1 <= 7 && this.pp.y + 2 <= 7)
            temp.add(this.pi + 17);
        if (this.pp.x + 2 <= 7 && this.pp.y + 1 <= 7)
            temp.add(this.pi + 10);

        if (this.pp.x + 1 <= 7 && this.pp.y - 2 >= 0)
            temp.add(this.pi - 15);
        if (this.pp.x + 2 <= 7 && this.pp.y - 1 >= 0)
            temp.add(this.pi - 6);
        if (this.pp.x - 1 >= 0 && this.pp.y + 2 <= 7)
            temp.add(this.pi + 15);
        if (this.pp.x - 2 >= 0 && this.pp.y + 1 <= 7)
            temp.add(this.pi + 6);

        return temp;
    }
}
