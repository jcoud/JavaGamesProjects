import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class MouseAndKeys {
    private static Drawing drawing = ChessMain.drawing;
    private static final int _BS_ = Reference.BS;
    private static ArrayList<Reference.PlaceHolderClass> phl = Reference.placeHolderArrayList;

    static class KeysIO extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            if (e.getKeyCode() == KeyEvent.VK_X) System.exit(0);
        }
    }
    static class MouseIOMotionAdapter extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {

            phl.forEach((p) -> {
                if (e.getX() > p.getPositionPoint().x*_BS_ && e.getX() < p.getPositionPoint().x*_BS_+_BS_)
                    if (e.getY() > p.getPositionPoint().y*_BS_ && e.getY() < p.getPositionPoint().y*_BS_+_BS_)
                        Reference.hlHoveredPlace = new Point(p.getPositionPoint());
            });
            drawing.repaint();
        }
    }
    static class MouseIOAdapter extends MouseAdapter {
        private Point p1 = Reference.hlSelectedPlace.get(0);
        private Point pt;
        @Override
        public void mousePressed(MouseEvent e) {
            phl.forEach(p -> {
                if (e.getX() > p.getPositionPoint().x*_BS_ && e.getX() < p.getPositionPoint().x*_BS_+_BS_)
                    if (e.getY() > p.getPositionPoint().y*_BS_ && e.getY() < p.getPositionPoint().y*_BS_+_BS_)
                        pt = new Point(p.getPositionPoint());
            });
            if (p1.equals(pt)) {
                Reference.hlSelectedPlace.get(0).setLocation(p1);
                Reference.hlSelectedPlace.get(1).setLocation(pt);
                Reference.toggleHeightLightedPlace = !Reference.toggleHeightLightedPlace;
            } else {
                Reference.hlSelectedPlace.get(0).setLocation(pt);
                Reference.hlSelectedPlace.get(1).setLocation(p1);
                Reference.toggleHeightLightedPlace = true;
            }

            if (Reference.toggleHeightLightedPlace) {
                phl.forEach(p -> {
                    if (p.getPositionPoint().equals(p1)){
                        System.out.println("=======================");
                        System.out.println(p.getPosiotionIndex());
                        System.out.println(p.getPositionPoint());
                        System.out.println(p.getSquareColorName());
                        System.out.println(p.getFigureName());
                        System.out.println(p.getFigureColor());
                    }
                });
            }
            drawing.repaint();
        }
    }
}
