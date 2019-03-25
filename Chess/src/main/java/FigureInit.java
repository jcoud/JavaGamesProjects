
import figures.Bishop;
import figures.IFigureHolder;
import figures.King;
import figures.Knight;
import figures.Pawn;
import figures.Queen;
import figures.Rook;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

class FigureInit {

    { setDefault(); }
    void setDefault() {
        Reference.existingFigures.clear();
        /*SET BLACK FIGURES*/
        for (int i = 0; i < 8; i++)
        Reference.existingFigures.add(new Pawn  (i+9,  Reference.getPointFromIndex(i+9),  IFigureHolder.PAWN, Integer.toString(i+1), IFigureHolder.BLACK_FIGURE));
        Reference.existingFigures.add(new Rook  (1,    Reference.getPointFromIndex(1),    IFigureHolder.ROOK, " left", IFigureHolder.BLACK_FIGURE));
        Reference.existingFigures.add(new Knight(2,    Reference.getPointFromIndex(2),    IFigureHolder.KNIGHT, " left", IFigureHolder.BLACK_FIGURE));
        Reference.existingFigures.add(new Bishop(3,    Reference.getPointFromIndex(3),    IFigureHolder.BISHOP, " left", IFigureHolder.BLACK_FIGURE));
        Reference.existingFigures.add(new King  (4,    Reference.getPointFromIndex(4),    IFigureHolder.KING, "", IFigureHolder.BLACK_FIGURE));
        Reference.existingFigures.add(new Queen (5,    Reference.getPointFromIndex(5),    IFigureHolder.QUEEN, "", IFigureHolder.BLACK_FIGURE));
        Reference.existingFigures.add(new Bishop(6,    Reference.getPointFromIndex(6),    IFigureHolder.BISHOP, " right", IFigureHolder.BLACK_FIGURE));
        Reference.existingFigures.add(new Knight(7,    Reference.getPointFromIndex(7),    IFigureHolder.KNIGHT, " right", IFigureHolder.BLACK_FIGURE));
        Reference.existingFigures.add(new Rook  (8,    Reference.getPointFromIndex(8),    IFigureHolder.ROOK, " right", IFigureHolder.BLACK_FIGURE));
        /*SET WHITE FIGURES*/
        for (int i = 0; i < 8; i++)
        Reference.existingFigures.add(new Pawn  (i+49, Reference.getPointFromIndex(i+49),  IFigureHolder.PAWN, Integer.toString(i+1), IFigureHolder.WHITE_FIGURE));
        Reference.existingFigures.add(new Rook  (1+56, Reference.getPointFromIndex(1+56), IFigureHolder.ROOK, " left", IFigureHolder.WHITE_FIGURE));
        Reference.existingFigures.add(new Knight(2+56, Reference.getPointFromIndex(2+56), IFigureHolder.KNIGHT, " left", IFigureHolder.WHITE_FIGURE));
        Reference.existingFigures.add(new Bishop(3+56, Reference.getPointFromIndex(3+56), IFigureHolder.BISHOP, " left", IFigureHolder.WHITE_FIGURE));
        Reference.existingFigures.add(new King  (4+56, Reference.getPointFromIndex(4+56), IFigureHolder.KING, "", IFigureHolder.WHITE_FIGURE));
        Reference.existingFigures.add(new Queen (5+56, Reference.getPointFromIndex(5+56), IFigureHolder.QUEEN, "", IFigureHolder.WHITE_FIGURE));
        Reference.existingFigures.add(new Bishop(6+56, Reference.getPointFromIndex(6+56), IFigureHolder.BISHOP, " right", IFigureHolder.WHITE_FIGURE));
        Reference.existingFigures.add(new Knight(7+56, Reference.getPointFromIndex(7+56), IFigureHolder.KNIGHT, " right", IFigureHolder.WHITE_FIGURE));
        Reference.existingFigures.add(new Rook  (8+56, Reference.getPointFromIndex(8+56), IFigureHolder.ROOK, " right", IFigureHolder.WHITE_FIGURE));
    }

    void drawing(Graphics2D g){
        Reference.existingFigures.forEach(figure -> {
            figure.getAllowedPositionsIndex().forEach(index -> {
                Reference.placeHolderArrayList.forEach(square -> {
                    if (figure.getPositionIndex() == Reference.hlSelectedFigure) {
                        if (square.getPositionIndex() == index) {
                            System.out.print(index + " ");
                            System.out.println(figure.getFigureName() + " " + figure.getFigureColor());
                            g.setColor(Color.decode("#377341"));
                            int r = Reference.BS / 4;
                            g.fillOval(square.getSquarePositionPoint().x * Reference.BS + (Reference.BS - r) / 2, square.getSquarePositionPoint().y * Reference.BS + (Reference.BS - r) / 2, r, r);
                        }
                    }
                    if (figure.getPositionIndex() == square.getPositionIndex()){
                        if (figure.getFigureColor().equals(IFigureHolder.BLACK_FIGURE)) g.setColor(Color.BLACK);
                        if (figure.getFigureColor().equals(IFigureHolder.WHITE_FIGURE)) g.setColor(Color.WHITE);
                        g.fillRect(square.getSquarePositionPoint().x * Reference.BS + Reference.BS/4, square.getSquarePositionPoint().y*Reference.BS + Reference.BS/4, Reference.BS/2, Reference.BS/2);
                    }
                });
            });
        });
    }
}
