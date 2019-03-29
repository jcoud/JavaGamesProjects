
import figures.FigureInit;
import figures.IFigureHolder;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

class FigureSet {

    { setDefault(); }
    void setDefault() {
        Reference.existingFigures.clear();

        Reference.existingFigures.addAll(new ArrayList<>(Arrays.asList(FigureInit.pawn)));
        Reference.existingFigures.addAll(new ArrayList<>(Arrays.asList(FigureInit.rook)));
        Reference.existingFigures.addAll(new ArrayList<>(Arrays.asList(FigureInit.knight)));
        Reference.existingFigures.addAll(new ArrayList<>(Arrays.asList(FigureInit.bishop)));
        Reference.existingFigures.addAll(new ArrayList<>(Arrays.asList(FigureInit.king)));
        Reference.existingFigures.addAll(new ArrayList<>(Arrays.asList(FigureInit.queen)));
    }

    void drawing(Graphics2D g){
        Reference.existingFigures.forEach(figure -> {
            if (figure.getFigureColor().equals(IFigureHolder.BLACK_FIGURE)) {
                g.drawImage(Reference.iconList.get(figure.getFigureID() + figure.getFigureColor()).getImage(), figure.getPp().x * Reference.BS,figure.getPp().y * Reference.BS, null);
            }
            if (figure.getFigureColor().equals(IFigureHolder.WHITE_FIGURE)) {
                g.drawImage(Reference.iconList.get(figure.getFigureID() + figure.getFigureColor()).getImage(), figure.getPp().x * Reference.BS,figure.getPp().y * Reference.BS, null);
            }
        });
    }
}
