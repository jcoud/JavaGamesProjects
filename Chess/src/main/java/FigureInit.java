//import figures.Pawn;

import java.awt.*;
import java.util.ArrayList;

class FigureInit {
    FigureInit(){
        for (Reference.figureIDList id : Reference.figureIDList.values()) {
            id.figureName = id.name();
            if (id.name().indexOf('b') >= 0)
                 id.figureColor = Reference.BLACK_FIGURE;
            else if (id.name().indexOf('w') >= 0)
                id.figureColor = Reference.WHITE_FIGURE;


        }
    setDefault();
    }
    private void setDefault(){
        /*SET BLACK FIGURES*/
        ArrayList<Reference.figureIDList> bPs = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) bPs.add(Reference.figureIDList.bP);
        bPs.forEach(figure -> figure.fieldPositionIndex = bPs.indexOf(figure)+9);

        ArrayList<Reference.figureIDList> bRs = new ArrayList<>(2);
        bRs.add(Reference.figureIDList.bR); bRs.add(Reference.figureIDList.bR);
        bRs.get(0).fieldPositionIndex = 1;  bRs.get(1).fieldPositionIndex = 8;

        ArrayList<Reference.figureIDList> bNs = new ArrayList<>(2);
        bNs.add(Reference.figureIDList.bN); bNs.add(Reference.figureIDList.bN);
        bNs.get(0).fieldPositionIndex = 2;  bNs.get(1).fieldPositionIndex = 7;

        ArrayList<Reference.figureIDList> bBs = new ArrayList<>(2);
        bBs.add(Reference.figureIDList.bB); bBs.add(Reference.figureIDList.bB);
        bBs.get(0).fieldPositionIndex = 3;  bBs.get(1).fieldPositionIndex = 6;

        Reference.figureIDList.bQ.fieldPositionIndex = 4;
        Reference.figureIDList.bK.fieldPositionIndex = 5;

        Reference.figureList.addAll(bPs);
        Reference.figureList.addAll(bRs);
        Reference.figureList.addAll(bNs);
        Reference.figureList.addAll(bBs);
        Reference.figureList.add(Reference.figureIDList.bQ);
        Reference.figureList.add(Reference.figureIDList.bK);

        /*SET WHITE FIGURES*/
        ArrayList<Reference.figureIDList> wPs = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) wPs.add(Reference.figureIDList.wP);
        wPs.forEach(figure -> figure.fieldPositionIndex = wPs.indexOf(figure)+9+40);

        ArrayList<Reference.figureIDList> wRs = new ArrayList<>(2);
        wRs.add(Reference.figureIDList.wR); wRs.add(Reference.figureIDList.wR);
        wRs.get(0).fieldPositionIndex = 1+56;  wRs.get(1).fieldPositionIndex = 8+56;

        ArrayList<Reference.figureIDList> wNs = new ArrayList<>(2);
        wNs.add(Reference.figureIDList.wN); wNs.add(Reference.figureIDList.wN);
        wNs.get(0).fieldPositionIndex = 2+56;  wNs.get(1).fieldPositionIndex = 7+56;

        ArrayList<Reference.figureIDList> wBs = new ArrayList<>(2);
        wBs.add(Reference.figureIDList.wB); wBs.add(Reference.figureIDList.wB);
        wBs.get(0).fieldPositionIndex = 3+56;  wBs.get(1).fieldPositionIndex = 6+56;

        Reference.figureIDList.wQ.fieldPositionIndex = 4+56;
        Reference.figureIDList.wK.fieldPositionIndex = 5+56;

        Reference.figureList.addAll(wPs);
        Reference.figureList.addAll(wRs);
        Reference.figureList.addAll(wNs);
        Reference.figureList.addAll(wBs);
        Reference.figureList.add(Reference.figureIDList.wQ);
        Reference.figureList.add(Reference.figureIDList.wK);
    }
    void paint(Graphics2D g){
        Reference.figureList.forEach(figure -> {
            Reference.placeHolderArrayList.forEach(fieldSquare -> {
                if (figure.fieldPositionIndex == fieldSquare.getPosiotionIndex()){
                    g.setColor(Color.GREEN);
                    g.fillRect(fieldSquare.getPositionPoint().x * Reference.BS + Reference.BS/4, fieldSquare.getPositionPoint().y*Reference.BS + Reference.BS/4, Reference.BS/2, Reference.BS/2);
                }
            });
        });
    }
}
