import lombok.Getter;
import lombok.Setter;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

public class Reference {
    static final int
        BS = 80,
        gap = 10;
    static final String
        BLACK_SQUARE = "BLACK_S",
        WHITE_SQUARE = "WHITE_S",
        BLACK_FIGURE = "BLACK_F",
        WHITE_FIGURE = "WHITE_F";

//    enum figureId{
//        PAWN,
//        ROOK,
//        BISHOP,
//        KNIGHT,
//        QUEEN,
//        KING
//    }
    static final String
        PAWN    = "pawn",
        ROOK    = "rook",
        BISHOP  = "bishop",
        KNIGHT  = "knight",
        QUEEN   = "queen",
        KING    = "king";

    @Getter
    @Setter
    static class PlaceHolderClass {
        private int positionIndex;
        private Point positionPoint;
        private String
            squareColorName,
            figureName,
            figureColor;
        PlaceHolderClass(int positionIndex, Point positionPoint, String squareColorName, String figureName, String figureColor) {
            this.positionIndex = positionIndex;
            this.positionPoint = positionPoint;
            this.squareColorName = squareColorName;
            this.figureName = figureName;
            this.figureColor = figureColor;
        }
    }

    static ArrayList<PlaceHolderClass> placeHolderArrayList = new ArrayList<>();
    static ArrayList<FigureHolder> existingFigures = new ArrayList<>();
    static int hlHoveredSquare = 0;
    static int hlSelectedFigure = 0;
    static int[] hlSelectedSquare = new int[]{0,0};
    static boolean toggleHeightLightedPlace = false;
    static FieldInit fieldInitBlackRef;
    static FieldInit fieldInitWhiteRef;
    static FigureInit figureInitRef;
    @Getter
    static class Canvas {
        static final int
        x0 = gap,
        y0 = gap,
        width = BS * 8,
        height = BS * 8;
    }
    @Getter
    @Setter
    static class FigureHolder {
        private int positionIndex;
        private String figureID;
        private String figureName;
        private String figureColor;
        FigureHolder(int positionIndex, final String figureID, String figureName, String figureColor){
            this.positionIndex = positionIndex;
            this.figureID = figureID;
            this.figureName = figureName;
            this.figureColor = figureColor;
        }
        ArrayList<Integer> getAllowedPositionsIndex(int fromIndex) {
            if (hlSelectedFigure != 0) {
                if (this.figureID.equals(PAWN)){
//                    ArrayList<Integer> temp = new ArrayList<>();
////                    if (placeHolderArrayList.get().getFigureColor())
//                    placeHolderArrayList.forEach(square -> {
//                        if (square.getPositionIndex() == fromIndex) {
//                            existingFigures.forEach(figure -> {
//                                if (figure.getPositionIndex() == fromIndex + 8) {
//                                    if (!square.getFigureColor().equals(figure.getFigureColor())) {
//                                        temp.add(fromIndex + 8);
//                                    }
//                                } else if (figure.getPositionIndex() == fromIndex + 10) {
//                                    if (!square.getFigureColor().equals(figure.getFigureColor())) {
//                                        temp.add(fromIndex + 10);
//                                    }
//                                }
//                            });
//                        }
//                    });
//                    if (Math.floor(fromIndex / 8) + 1 == 2) {
//                        temp.add(fromIndex + 8);
//                        temp.add(fromIndex + 16);
//                    } else {
//                        temp.add(fromIndex + 8);
//                    }
//                    return temp;
//                    System.out.println("a");
                }
            }
            return new ArrayList<>(Collections.singletonList(0));
        }
    }

    static PlaceHolderClass squareInit(int positionIndex, Point positionPoint, String squareColorName, String figureName, String figureColor) {
        return new PlaceHolderClass(positionIndex, positionPoint, squareColorName, figureName, figureColor);
    }
    static void updateField(){
        existingFigures.forEach(figure -> {
            figure.getAllowedPositionsIndex(hlSelectedFigure).forEach(indexPoint ->{
                if (hlSelectedSquare[0] != 0){
                    if (indexPoint == hlSelectedSquare[0]){
                        hlSelectedFigure = hlSelectedSquare[0];
                        figure.setPositionIndex(hlSelectedFigure);
                    }
                }
            });
        });

        existingFigures.forEach(figure ->{
            placeHolderArrayList.forEach(square -> {
                if (figure.getPositionIndex() == square.getPositionIndex()){
                    square.setFigureName(figure.getFigureName());
                    square.setFigureColor(figure.getFigureColor());
                }
            });
        });
    }
}