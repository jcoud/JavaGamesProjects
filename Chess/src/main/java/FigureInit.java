//import figures.Pawn;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

class FigureInit {
    { setDefault(); }
    void setDefault() {
        Reference.existingFigures.clear();
        /*SET BLACK FIGURES*/
        ArrayList<Reference.FigureHolder> bPs = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) bPs.add(
            new Reference.FigureHolder(i+9, Reference.PAWN, Reference.PAWN.concat(" " + (i+1)),Reference.BLACK_FIGURE)
        );

        ArrayList<Reference.FigureHolder> bRs = new ArrayList<>(Arrays.asList(
            new Reference.FigureHolder(1, Reference.ROOK, Reference.ROOK.concat(" left"), Reference.BLACK_FIGURE),
            new Reference.FigureHolder(8, Reference.ROOK, Reference.ROOK.concat(" right"), Reference.BLACK_FIGURE)
        ));

        ArrayList<Reference.FigureHolder> bNs = new ArrayList<>(Arrays.asList(
            new Reference.FigureHolder(2, Reference.KNIGHT, Reference.KNIGHT.concat(" left"), Reference.BLACK_FIGURE),
            new Reference.FigureHolder(7, Reference.KNIGHT, Reference.KNIGHT.concat(" right"), Reference.BLACK_FIGURE)
        ));
        ArrayList<Reference.FigureHolder> bBs = new ArrayList<>(Arrays.asList(
            new Reference.FigureHolder(3, Reference.BISHOP, Reference.BISHOP.concat(" left"), Reference.BLACK_FIGURE),
            new Reference.FigureHolder(6, Reference.BISHOP, Reference.BISHOP.concat(" right"), Reference.BLACK_FIGURE)
        ));

        Reference.existingFigures.addAll(bPs);
        Reference.existingFigures.addAll(bRs);
        Reference.existingFigures.addAll(bNs);
        Reference.existingFigures.addAll(bBs);
        Reference.existingFigures.add(new Reference.FigureHolder(4, Reference.KING, Reference.KING, Reference.BLACK_FIGURE));
        Reference.existingFigures.add(new Reference.FigureHolder(5, Reference.QUEEN, Reference.QUEEN, Reference.BLACK_FIGURE));

        /*SET WHITE FIGURES*/
        ArrayList<Reference.FigureHolder> wPs = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) wPs.add(
            new Reference.FigureHolder(i+49, Reference.PAWN, Reference.PAWN.concat(Integer.toString(i)),Reference.WHITE_FIGURE)
        );

        ArrayList<Reference.FigureHolder> wRs = new ArrayList<>(Arrays.asList(
            new Reference.FigureHolder(1+56, Reference.ROOK, Reference.ROOK.concat(" left"), Reference.WHITE_FIGURE),
            new Reference.FigureHolder(8+56, Reference.ROOK, Reference.ROOK.concat(" right"), Reference.WHITE_FIGURE)
        ));

        ArrayList<Reference.FigureHolder> wNs = new ArrayList<>(Arrays.asList(
            new Reference.FigureHolder(2+56, Reference.KNIGHT, Reference.KNIGHT.concat(" left"), Reference.WHITE_FIGURE),
            new Reference.FigureHolder(7+56, Reference.KNIGHT, Reference.KNIGHT.concat(" right"), Reference.WHITE_FIGURE)
        ));
        ArrayList<Reference.FigureHolder> wBs = new ArrayList<>(Arrays.asList(
            new Reference.FigureHolder(3+56, Reference.BISHOP, Reference.BISHOP.concat(" left"), Reference.WHITE_FIGURE),
            new Reference.FigureHolder(6+56, Reference.BISHOP, Reference.BISHOP.concat(" right"), Reference.WHITE_FIGURE)
        ));

        Reference.existingFigures.addAll(wPs);
        Reference.existingFigures.addAll(wRs);
        Reference.existingFigures.addAll(wNs);
        Reference.existingFigures.addAll(wBs);
        Reference.existingFigures.add(new Reference.FigureHolder(4+56, Reference.KING, Reference.KING, Reference.WHITE_FIGURE));
        Reference.existingFigures.add(new Reference.FigureHolder(5+56, Reference.QUEEN, Reference.QUEEN, Reference.WHITE_FIGURE));
    }
    void paint(Graphics2D g){
        Reference.existingFigures.forEach(figure -> {
            Reference.placeHolderArrayList.forEach(fieldSquare -> {
                figure.getAllowedPositionsIndex(Reference.hlSelectedFigure).forEach(indexPoint -> {
                    if (fieldSquare.getPositionIndex() == indexPoint){
                        g.setColor(Color.decode("#377341"));
                        g.fillOval(fieldSquare.getPositionPoint().x * Reference.BS + Reference.BS / 8,fieldSquare.getPositionPoint().y * Reference.BS + Reference.BS / 8, Reference.BS/4, Reference.BS/4);
                    }

                });
                if (figure.getPositionIndex() == fieldSquare.getPositionIndex()){
                    if (figure.getFigureColor().equals(Reference.BLACK_FIGURE)) g.setColor(Color.RED);
                    else g.setColor(Color.blue);
                    g.fillRect(fieldSquare.getPositionPoint().x * Reference.BS + Reference.BS/4, fieldSquare.getPositionPoint().y*Reference.BS + Reference.BS/4, Reference.BS/2, Reference.BS/2);
                }
            });
        });
    }
}
