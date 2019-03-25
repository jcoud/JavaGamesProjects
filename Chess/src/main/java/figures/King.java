package figures;

import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class King extends IFigureHolder {
    public King(int positionIndex, Point positionPoint, String figureID, String uniqueNameInfo, String figureColor) {
        this.positionIndex = positionIndex;
        this.positionPoint = positionPoint;
        this.figureID = figureID;
        this.figureName = figureID + " " + uniqueNameInfo;
        this.figureColor = figureColor;
    }
    @Override
    public ArrayList<Integer> getAllowedPositionsIndex() {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(this.positionIndex-9);
        temp.add(this.positionIndex-8);
        temp.add(this.positionIndex-7);
        temp.add(this.positionIndex-1);
        temp.add(this.positionIndex+1);
        temp.add(this.positionIndex+7);
        temp.add(this.positionIndex+8);
        temp.add(this.positionIndex+9);
        return temp;
    }
}
