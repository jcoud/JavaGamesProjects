package figures;

import java.awt.Point;
import java.util.ArrayList;

public class Queen extends IFigureHolder {
    Queen(int pi, Point pp, String figureID, String name, String figureColor) {
        this.pi = pi;
        this.pp = pp;
        this.figureID = figureID;
        this.figureName = figureID + name;
        this.figureColor = figureColor;
    }
    @Override
    public ArrayList<Integer> getAllowedPositionsIndex(int pi, Point pp) {
        ArrayList<Integer> temp = new ArrayList<>(8);
        temp.addAll(new Bishop().getAllowedPositionsIndex(this.pi, this.pp));
        temp.addAll(new King().getAllowedPositionsIndex(this.pi, this.pp));
        temp.addAll(new Rook().getAllowedPositionsIndex(this.pi, this.pp));
        ArrayList<Integer> notDupl = new ArrayList<>();
        temp.forEach(el -> {if (!notDupl.contains(el)) notDupl.add(el);});
        return notDupl;
    }
}
