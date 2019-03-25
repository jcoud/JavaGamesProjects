//import figures.Pawn;

import figures.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

class FigureInit {

    { setDefault(); }
    void setDefault() {
        Reference.existingFigures.clear();
        /*SET BLACK FIGURES*/
        ArrayList<IFigureHolder> bPs = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) bPs.add(
            new Pawn(i+9, IFigureHolder.PAWN, Integer.toString(i+1), IFigureHolder.BLACK_FIGURE)
        );

        ArrayList<IFigureHolder> bRs = new ArrayList<>(Arrays.asList(
            new Rook(1, IFigureHolder.ROOK, " left", IFigureHolder.BLACK_FIGURE),
            new Rook(8, IFigureHolder.ROOK, " right", IFigureHolder.BLACK_FIGURE)
        ));

        ArrayList<IFigureHolder> bNs = new ArrayList<>(Arrays.asList(
            new Knight(2, IFigureHolder.KNIGHT, " left", IFigureHolder.BLACK_FIGURE),
            new Knight(7, IFigureHolder.KNIGHT, " right", IFigureHolder.BLACK_FIGURE)
        ));
        ArrayList<IFigureHolder> bBs = new ArrayList<>(Arrays.asList(
            new Bishop(3, IFigureHolder.BISHOP, " left", IFigureHolder.BLACK_FIGURE),
            new Bishop(6, IFigureHolder.BISHOP, " right", IFigureHolder.BLACK_FIGURE)
        ));

        Reference.existingFigures.addAll(bPs);
        Reference.existingFigures.addAll(bRs);
        Reference.existingFigures.addAll(bNs);
        Reference.existingFigures.addAll(bBs);
        Reference.existingFigures.add(new King(4, IFigureHolder.KING, "", IFigureHolder.BLACK_FIGURE));
        Reference.existingFigures.add(new Queen(5, IFigureHolder.QUEEN, "", IFigureHolder.BLACK_FIGURE));

        /*SET WHITE FIGURES*/
        ArrayList<IFigureHolder> wPs = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) wPs.add(
                new Pawn(i+49, IFigureHolder.PAWN, Integer.toString(i+1), IFigureHolder.WHITE_FIGURE)
        );

        ArrayList<IFigureHolder> wRs = new ArrayList<>(Arrays.asList(
                new Rook(1+56, IFigureHolder.ROOK, " left", IFigureHolder.WHITE_FIGURE),
                new Rook(8+56, IFigureHolder.ROOK, " right", IFigureHolder.WHITE_FIGURE)
        ));

        ArrayList<IFigureHolder> wNs = new ArrayList<>(Arrays.asList(
                new Knight(2+56, IFigureHolder.KNIGHT, " left", IFigureHolder.WHITE_FIGURE),
                new Knight(7+56, IFigureHolder.KNIGHT, " right", IFigureHolder.WHITE_FIGURE)
        ));
        ArrayList<IFigureHolder> wBs = new ArrayList<>(Arrays.asList(
                new Bishop(3+56, IFigureHolder.BISHOP, " left", IFigureHolder.WHITE_FIGURE),
                new Bishop(6+56, IFigureHolder.BISHOP, " right", IFigureHolder.WHITE_FIGURE)
        ));

        Reference.existingFigures.addAll(wPs);
        Reference.existingFigures.addAll(wRs);
        Reference.existingFigures.addAll(wNs);
        Reference.existingFigures.addAll(wBs);
        Reference.existingFigures.add(new King(4+56, IFigureHolder.KING, "", IFigureHolder.WHITE_FIGURE));
        Reference.existingFigures.add(new Queen(5+56, IFigureHolder.QUEEN, "", IFigureHolder.WHITE_FIGURE));
    }

    void drawing(Graphics2D g){
        for (IFigureHolder figure : Reference.existingFigures){

        }

        Reference.existingFigures.forEach(figure -> {
            figure.getAllowedPositionsIndex().forEach(index -> {
                Reference.placeHolderArrayList.forEach(square -> {
                    if (figure.getPositionIndex() == Reference.hlSelectedFigure) {
                        if (square.getPositionIndex() == index) {
                            System.out.print(index + " ");
                            System.out.println(figure.getFigureName() + " " + figure.getFigureColor());
                            g.setColor(Color.decode("#377341"));
                            int r = Reference.BS / 4;
                            g.fillOval(square.getPositionPoint().x * Reference.BS + (Reference.BS - r) / 2, square.getPositionPoint().y * Reference.BS + (Reference.BS - r) / 2, r, r);
                        }
                    }
                    if (figure.getPositionIndex() == square.getPositionIndex()){
                        if (figure.getFigureColor().equals(IFigureHolder.BLACK_FIGURE)) g.setColor(Color.BLACK);
                        if (figure.getFigureColor().equals(IFigureHolder.WHITE_FIGURE)) g.setColor(Color.WHITE);
                        g.fillRect(square.getPositionPoint().x * Reference.BS + Reference.BS/4, square.getPositionPoint().y*Reference.BS + Reference.BS/4, Reference.BS/2, Reference.BS/2);
                    }
                });
            });
        });
    }
}
