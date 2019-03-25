package figures;

import java.util.ArrayList;

public class Bishop extends IFigureHolder {
    public Bishop(int positionIndex, String figureID, String uniqueNameInfo, String figureColor) {
        this.positionIndex = positionIndex;
        this.figureID = figureID;
        this.figureName = figureID + " " + uniqueNameInfo;
        this.figureColor = figureColor;
    }
    @Override
    public ArrayList<Integer> getAllowedPositionsIndex() {
        ArrayList<Integer> temp = new ArrayList<>();
        if (this.figureColor.equals(BLACK_FIGURE)) {
            for (int i = this.positionIndex; i >= 0; i=-9) {temp.add(i);}
            for (int i = this.positionIndex; i <= 64; i=+9) {temp.add(i);}
        } else {

        }
        return temp;
    }
}
