import javax.swing.*;
import java.awt.*;

public class Drawing extends JComponent {
    private final Reference.CanvasRefClass canvas = Reference.getCanvasProp();
    private static final int bs = Reference.BS;
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        setBounds(canvas.x0, canvas.y0, canvas.width, canvas.height);
//        grid(g2);

        Reference.fieldInitBlackRef.paint(g2);
        Reference.fieldInitWhiteRef.paint(g2);
        Reference.figureInitRef.paint(g2);

        squareSelection(g2);
    }
    private void squareSelection(Graphics2D g){
        if (Reference.toggleHeightLightedPlace) {
            g.setColor(new Color(130, 230, 255, 70));
            g.fillRect(Reference.hlSelectedPlace.get(0).x * bs, Reference.hlSelectedPlace.get(0).y * bs, bs, bs);
            g.setColor(new Color(130, 230, 255));
            g.setStroke(new BasicStroke(4));
            g.drawRect(Reference.hlSelectedPlace.get(0).x * bs, Reference.hlSelectedPlace.get(0).y * bs, bs, bs);
            g.setStroke(new BasicStroke(1));
        }
        g.setColor(new Color(255,130,130,70));
        g.fillRect(Reference.hlHoveredPlace.x*bs, Reference.hlHoveredPlace.y*bs,bs,bs);
    }
    private void grid(Graphics2D g) {
        g.setColor(new Color(255,255,255).darker());
        g.drawRect(0, 0, canvas.width, canvas.height);
        for (int i = 1; i <= canvas.width / bs; i++)
            g.drawLine(i * bs,0,i * bs, canvas.height);
        for (int i = 1; i <= canvas.height / bs-1; i++)
            g.drawLine(0,i * bs, canvas.width,i * bs);
    }
}
