import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Reference {
    static final int
        BS = 80,
        gap = 10;
    static final String
        BLACK_SQUARE = "BLACK_S",
        WHITE_SQUARE = "WHITE_S",
        BLACK_FIGURE = "BLACK_F",
        WHITE_FIGURE = "WHITE_F";

    @Getter
    @Setter
    static class PlaceHolderClass {
        private int posiotionIndex;
        private Point positionPoint;
        private String
            squareColorName,
            figureName,
            figureColor;
        PlaceHolderClass(int positionIndex, Point positionPoint, String squareColorName, String figureName, String figureColor) {
            this.posiotionIndex = positionIndex;
            this.positionPoint = positionPoint;
            this.squareColorName = squareColorName;
            this.figureName = figureName;
            this.figureColor = figureColor;
        }
    }

    static ArrayList<PlaceHolderClass> placeHolderArrayList = new ArrayList<>();
    static Point hlHoveredPlace = new Point(-1, -1);
    static ArrayList<Point> hlSelectedPlace = new ArrayList<>(2);
    static boolean toggleHeightLightedPlace = false;
    static FieldInit fieldInitBlackRef;
    static FieldInit fieldInitWhiteRef;
    static FigureInit figureInitRef;
    static PlaceHolderClass placeHolder;
    static HashMap<Point, ArrayList<PlaceHolderClass>> placeHolderMap = new HashMap<>();
    static class CanvasRefClass{
        final int
        x0 = gap,
        y0 = gap,
        width = BS * 8,
        height = BS * 8;
    }
//    static Pawn pawnB,pawnW;

    enum figureIDList {
        bB, wB, //Bishop
        bK, wK, //King
        bN, wN, //Knight
        bP, wP, //Pawn
        bQ, wQ, //Queen
        bR, wR; //Rook


        int fieldPositionIndex;
        String figureName;
        String figureColor;
    }
    static ArrayList<figureIDList> figureList = new ArrayList<>();
    static PlaceHolderClass squareInit(int positionIndex, Point positionPoint, String squareColorName, String figureName, String figureColor) {
        return new PlaceHolderClass(positionIndex, positionPoint, squareColorName, figureName, figureColor);
    }


    static void addToField(int positionIndex, String figureName, String figureColor){
        placeHolderArrayList.forEach(obj -> {
            if (obj.getPosiotionIndex() == positionIndex){
                obj.setFigureName(figureName);
                obj.setFigureColor(figureColor);
            }
        });
    }
    static CanvasRefClass getCanvasProp() {
        return new CanvasRefClass();
    }
}