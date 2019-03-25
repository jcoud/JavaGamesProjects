package figures;

import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Knight extends IFigureHolder {
    public Knight(int positionIndex, Point positionPoint, String figureID, String uniqueNameInfo, String figureColor) {
        this.positionIndex = positionIndex;
        this.positionPoint = positionPoint;
        this.figureID = figureID;
        this.figureName = figureID + " " + uniqueNameInfo;
        this.figureColor = figureColor;
    }/*
    @Override
    public ArrayList<Integer> getAllowedPositionsIndex() {
        ArrayList<Integer> temp = new ArrayList<>();
//        if (this.positionIndex + 10 >= 0) {temp.add(this.positionIndex + 10);}
//        if (this.positionIndex + 17 >= 0) {temp.add(this.positionIndex + 17);}
//        if (this.positionIndex + 17 >= 0) {temp.add(this.positionIndex + 17);}
//        if (this.positionIndex + 17 >= 0) {temp.add(this.positionIndex + 17);}
        return temp;
    }*/
}
