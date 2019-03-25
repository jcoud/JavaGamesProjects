package figures;

import lombok.Getter;

import java.util.ArrayList;


public class Pawn extends IFigureHolder {
    public Pawn(int positionIndex, String figureID, String uniqueNameInfo, String figureColor) {
        this.positionIndex = positionIndex;
        this.figureID = figureID;
        this.figureName = figureID + " " + uniqueNameInfo;
        this.figureColor = figureColor;
    }

    @Override
    public ArrayList<Integer> getAllowedPositionsIndex() {
        ArrayList<Integer> temp = new ArrayList<>();
        if (this.figureColor.equals(BLACK_FIGURE)) {
//            temp.add(this.positionIndex + 8);
//            temp.add(this.positionIndex + 10);
            if (this.positionIndex >= 8 && this.positionIndex <= 16) {
                temp.add(this.positionIndex + 8);
                temp.add(this.positionIndex + 16);
            } else {
                temp.add(this.positionIndex + 8);
            }
            return temp;
        } else {
//            temp.add(this.positionIndex - 8);
//            temp.add(this.positionIndex - 10);
            if (this.positionIndex >= 48 && this.positionIndex <= 56) {
                temp.add(this.positionIndex - 8);
                temp.add(this.positionIndex - 16);
            } else {
                temp.add(this.positionIndex - 8);
            }
            return temp;
        }
    }
}
