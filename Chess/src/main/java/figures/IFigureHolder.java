package figures;

import lombok.Getter;
import lombok.Setter;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Getter
@Setter
public abstract class IFigureHolder {
    int pi;
    Point pp;
    String figureID;
    String figureName;
    String figureColor;

    public static final String
        PAWN    = "PAWN",
        ROOK    = "ROOK",
        BISHOP  = "BISHOP",
        KNIGHT  = "KNIGHT",
        QUEEN   = "QUEEN",
        KING    = "KING";
    public static final String
        BLACK_FIGURE = "BLACK",
        WHITE_FIGURE = "WHITE";
    public static final ArrayList<String> figuresNameList =
        new ArrayList<>(Arrays.asList(PAWN, ROOK, BISHOP, KNIGHT, QUEEN, KING));

    public ArrayList<Integer> getAllowedPositionsIndex(int pi, Point pp){
        return new ArrayList<>(Collections.singletonList(-1)); // set to -1 by default
    }
}
