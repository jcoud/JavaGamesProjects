package figures;

import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Rook extends IFigureHolder {
    public Rook(int positionIndex, Point positionPoint, String figureID, String uniqueNameInfo, String figureColor) {
        this.positionIndex = positionIndex;
        this.positionPoint = positionPoint;
        this.figureID = figureID;
        this.figureName = figureID + " " + uniqueNameInfo;
        this.figureColor = figureColor;
    }
//    @Override
//    public ArrayList<Integer> getAllowedPositionsIndex() {
//        return super.getAllowedPositionsIndex();
//
//    }
}
