package figures;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;

@Getter
@Setter
public abstract class IFigureHolder {

    int positionIndex;
    String figureID;
    String figureName;
    String figureColor;

    public static final String
        PAWN    = "pawn",
        ROOK    = "rook",
        BISHOP  = "bishop",
        KNIGHT  = "knight",
        QUEEN   = "queen",
        KING    = "king",
        BLACK_FIGURE = "BLACK_F",
        WHITE_FIGURE = "WHITE_F";


//    IFigureHolder(int positionIndex, final String figureID, String figureName, String figureColor) {
//        this.positionIndex = positionIndex;
//        this.figureID = figureID;
//        this.figureName = figureName;
//        this.figureColor = figureColor;
//    }
    public ArrayList<Integer> getAllowedPositionsIndex(){
        return new ArrayList<>(Collections.singletonList(0));
    }
}
