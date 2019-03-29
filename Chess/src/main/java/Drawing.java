import javax.swing.JComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Drawing extends JComponent {
    private static final int _BS_ = Reference.BS;
    int i =0;
    @Override
    protected void paintComponent(Graphics g) {
        //todo probably change Graphics2D to Graphics

        Graphics2D g2 = (Graphics2D) g;
        setBounds(Reference.Canvas.x0, Reference.Canvas.y0, Reference.Canvas.width, Reference.Canvas.height);
//        grid(g2);
        Reference.fieldInitRef.drawing(g2);
        Reference.figureSetRef.drawing(g2);
        squareSelection(g2);
        figureSelection(g2);
        i++;
//        System.out.println(i);
    }
    private void figureSelection(Graphics2D g) {
        int r = Reference.BS / 4;
        Reference.existingFigures.forEach(figure -> {
            if (figure.getPi() == Reference.hlSelectedFigure){
                figure.getAllowedPositionsIndex(-1, null).forEach(index -> {
                    if (index > 0) {
                        Point p = Reference.getPointFromIndex(index);
                        System.out.print(index + " ");
                        System.out.print(figure.getFigureName() + " ");
                        System.out.print(figure.getFigureColor() + " ");
                        System.out.println(p);
                        g.setColor(Color.decode("#377341"));
                        g.fillOval(p.x * Reference.BS + (Reference.BS - r) / 2, p.y * Reference.BS + (Reference.BS - r) / 2, r, r);
                    }
                });
            }
        });
    }
    private void squareSelection(Graphics2D g){
        Reference.placeHolderArrayList.forEach(square -> {
            if (square.getPositionIndex() == Reference.hlSelectedSquare[1]) {
                g.setColor(new Color(130, 230, 155, 70));
                g.fillRect(square.getSquarePositionPoint().x * _BS_, square.getSquarePositionPoint().y * _BS_, _BS_, _BS_);
                g.setColor(new Color(130, 230, 155));
                g.setStroke(new BasicStroke(4));
                g.drawRect(square.getSquarePositionPoint().x * _BS_, square.getSquarePositionPoint().y * _BS_, _BS_, _BS_);
                g.setStroke(new BasicStroke(1));
            }
            if (Reference.toggleHeightLightedPlace) {
                if (square.getPositionIndex() == Reference.hlSelectedSquare[0]) {
                    g.setColor(new Color(130, 230, 255, 70));
                    g.fillRect(square.getSquarePositionPoint().x * _BS_, square.getSquarePositionPoint().y * _BS_, _BS_, _BS_);
                    g.setColor(new Color(130, 230, 255));
                    g.setStroke(new BasicStroke(4));
                    g.drawRect(square.getSquarePositionPoint().x * _BS_, square.getSquarePositionPoint().y * _BS_, _BS_, _BS_);
                    g.setStroke(new BasicStroke(1));
                }
            }

            if (square.getPositionIndex() == Reference.hlSelectedFigure) {
                g.setColor(new Color(30, 30, 155, 70));
                g.fillRect(square.getSquarePositionPoint().x * _BS_ + _BS_ / 8, square.getSquarePositionPoint().y * _BS_ + _BS_ / 8, _BS_ /4, _BS_ /4);
            }
            if (square.getPositionIndex() == Reference.hlHoveredSquare){
                g.setColor(new Color(255,130,130,70));
                g.fillRect(square.getSquarePositionPoint().x * _BS_, square.getSquarePositionPoint().y * _BS_, _BS_, _BS_);
            }
        });
    }
    private void grid(Graphics2D g) {
        g.setColor(new Color(255,255,255).darker());
        g.drawRect(0, 0, Reference.Canvas.width, Reference.Canvas.height);
        for (int i = 1; i <= Reference.Canvas.width / _BS_; i++)
            g.drawLine(i * _BS_,0,i * _BS_, Reference.Canvas.height);
        for (int i = 1; i <= Reference.Canvas.height / _BS_ -1; i++)
            g.drawLine(0,i * _BS_, Reference.Canvas.width,i * _BS_);
    }
}
