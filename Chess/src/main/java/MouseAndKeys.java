import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

class MouseAndKeys {
    private static Drawing drawing = ChessMain.drawing;
    private static final int _BS_ = Reference.BS;

    static class KeysIO extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent key){
            if (key.getKeyCode() == KeyEvent.VK_X) System.exit(0);
            if (key.getKeyCode() == KeyEvent.VK_N) {Reference.figureInitRef.setDefault(); drawing.repaint();}
        }
    }
    static class MouseIOMotionAdapter extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent mouse) {
            Reference.placeHolderArrayList.forEach((square) -> {
                if (mouse.getX() > square.getSquarePositionPoint().x * _BS_  && mouse.getX() < square.getSquarePositionPoint().x * _BS_ + _BS_ )
                    if (mouse.getY() > square.getSquarePositionPoint().y * _BS_  && mouse.getY() < square.getSquarePositionPoint().y * _BS_ + _BS_ )
                        Reference.hlHoveredSquare = square.getPositionIndex();
            });
            drawing.repaint();
        }
    }
    static class MouseIOAdapter extends MouseAdapter {
        private int pt;
        @Override
        public void mousePressed(MouseEvent mouse) {
            Reference.placeHolderArrayList.forEach(square -> {
                if (mouse.getX() > square.getSquarePositionPoint().x * _BS_  && mouse.getX() < square.getSquarePositionPoint().x * _BS_ + _BS_ )
                    if (mouse.getY() > square.getSquarePositionPoint(). y * _BS_  && mouse.getY() < square.getSquarePositionPoint().y * _BS_ + _BS_ )
                        pt = square.getPositionIndex();
            });
            if (Reference.hlSelectedSquare[0] != pt && Reference.hlSelectedSquare[1] != pt) {
                Reference.hlSelectedSquare[0] = pt;
                Reference.toggleHeightLightedPlace = true;
            } else {
                Reference.hlSelectedSquare[0] = 0;
                Reference.hlSelectedSquare[1] = Reference.hlSelectedSquare[0];
                Reference.toggleHeightLightedPlace = !Reference.toggleHeightLightedPlace;
            }
            Reference.existingFigures.forEach(figure -> {
                if (Reference.toggleHeightLightedPlace) {
                    if (figure.getPositionIndex() == Reference.hlSelectedSquare[0]) {
                        Reference.hlSelectedFigure = Reference.hlSelectedSquare[0];

                        Reference.updateField();
                    }
                } else
                    Reference.hlSelectedFigure = 0;
            });
            System.out.println(Reference.hlSelectedSquare[0] + " " + Reference.hlSelectedSquare[1]);
                Reference.placeHolderArrayList.forEach(square -> {
                    if (square.getPositionIndex() == Reference.hlSelectedSquare[0]) {
                        System.out.println("=======================");
                        System.out.println(square.getPositionIndex());
                        System.out.println(square.getSquarePositionPoint());
                        System.out.println(square.getSquareColorName());
                        System.out.println(square.getFigurePositionPoint());
                        System.out.println(square.getFigureName());
                        System.out.println(square.getFigureColor());
                    }
                });
            drawing.repaint();
        }
    }
}
