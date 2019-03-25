package figures;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;

public class Queen extends IFigureHolder {
    public Queen(int positionIndex, String figureID, String uniqueNameInfo, String figureColor) {
        this.positionIndex = positionIndex;
        this.figureID = figureID;
        this.figureName = figureID + " " + uniqueNameInfo;
        this.figureColor = figureColor;
    }
    @Override
    public ArrayList<Integer> getAllowedPositionsIndex() {
        return super.getAllowedPositionsIndex();

    }
}
