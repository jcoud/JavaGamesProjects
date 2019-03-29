import lombok.Getter;
import lombok.Setter;

import javax.swing.ImageIcon;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Reference {
    static final int
        BS = 80,
        gap = 10;
    static final String
        BLACK_SQUARE = "BLACK_S",
        WHITE_SQUARE = "WHITE_S";
    @Getter
    @Setter
    static class PlaceHolderClass {
        private int positionIndex;
        private Point
            squarePositionPoint,
            figurePositionPoint;
        private String
            squareColorName,
            figureName,
            figureColor;
        PlaceHolderClass(int positionIndex, Point squarePositionPoint, String squareColorName, Point figurePositionPoint, String figureName, String figureColor) {
            this.positionIndex = positionIndex;
            this.squarePositionPoint = squarePositionPoint;
            this.squareColorName = squareColorName;
            this.figurePositionPoint = figurePositionPoint;
            this.figureName = figureName;
            this.figureColor = figureColor;
        }

    }
    static Point getPointFromIndex(int index){
        for (Reference.PlaceHolderClass square : Reference.placeHolderArrayList) {
            if (square.getPositionIndex() == index) {
                return square.getSquarePositionPoint();
            }
        }
        return new Point();
    }

    //todo: set static image for figures to not creating new Object when calling drawing method @ FieldInit#drawing(Graphics2D)
    static HashMap<String, ImageIcon> iconList = new HashMap<>(12);
    static ArrayList<PlaceHolderClass> placeHolderArrayList = new ArrayList<>(64);
    static ArrayList<figures.IFigureHolder> existingFigures = new ArrayList<>(32);
    static int hlHoveredSquare = 0;
    static int hlSelectedFigure = 0;
    static int[] hlSelectedSquare = new int[]{0,0};
    static boolean toggleHeightLightedPlace = false;
    static FieldInit fieldInitRef;
    static FigureSet figureSetRef;
    @Getter
    static class Canvas {
        static final int
        x0 = gap,
        y0 = gap,
        width = BS * 8,
        height = BS * 8;
    }
    static PlaceHolderClass squareInit(int positionIndex, Point squarePositionPoint, String squareColorName, Point figurePositionPoint, String figureName, String figureColor) {
        return new PlaceHolderClass(positionIndex, squarePositionPoint, squareColorName, figurePositionPoint, figureName, figureColor);
    }
    static void updateField(){
//        existingFigures.forEach(figure -> {
//            figure.getAllowedPositionsIndex().forEach(indexPoint -> {
//                if (hlSelectedSquare[0] != 0) {
//                    if (indexPoint == hlSelectedSquare[0]) {
//                        hlSelectedFigure = hlSelectedSquare[0];
//                        figure.setPi(hlSelectedFigure);
//                    }
//                }
//            });
//        });
        placeHolderArrayList.forEach(square -> {
            existingFigures.forEach(figure ->{
                if (figure.getPi() == square.getPositionIndex()){
                    square.setFigurePositionPoint(figure.getPp());
                    square.setFigureName(figure.getFigureName());
                    square.setFigureColor(figure.getFigureColor());
                }
            });
        });
    }
}