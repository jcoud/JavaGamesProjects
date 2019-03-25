package figures;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Bishop extends IFigureHolder {
    public Bishop(int positionIndex, Point positionPoint, String figureID, String uniqueNameInfo, String figureColor) {
        this.positionIndex = positionIndex;
        this.positionPoint = positionPoint;
        this.figureID = figureID;
        this.figureName = figureID + " " + uniqueNameInfo;
        this.figureColor = figureColor;
    }
/*    @Override
    public ArrayList<Integer> getAllowedPositionsIndex() {
        ArrayList<Integer> temp = new ArrayList<>();
        if (this.figureColor.equals(BLACK_FIGURE)) {
            for (int i = this.positionIndex; i >= 0; i=-9) {temp.add(i);}
            for (a i = this.positionIndex; i <= 64; i=+9) {temp.add(i);}
        } else {

        }
        return temp;
    }*/
}
