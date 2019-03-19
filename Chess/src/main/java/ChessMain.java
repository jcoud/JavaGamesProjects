import javax.swing.*;
import java.awt.*;

public class ChessMain{
//    private final int bs = reference.getBS();
    static Drawing drawing;
    {
        setupReference();
        JFrame window = new JFrame();
        JFrame.setDefaultLookAndFeelDecorated(true);
        drawing = new Drawing();
        window.setTitle("The Chess");
        window.add(drawing);
        drawing.addMouseListener(new MouseAndKeys.MouseIOAdapter());
        drawing.addMouseMotionListener(new MouseAndKeys.MouseIOMotionAdapter());
        window.addKeyListener(new MouseAndKeys.KeysIO());
        window.getContentPane().setPreferredSize(
            new Dimension(
            Reference.getCanvasProp().x0 + Reference.getCanvasProp().width + 200,
            Reference.getCanvasProp().y0 + Reference.getCanvasProp().height));
        window.setVisible(true);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void setupReference(){
        Reference.figureInitRef = new FigureInit();
        Reference.hlSelectedPlace.add(new Point(0,0));
        Reference.hlSelectedPlace.add(new Point(0,0));
        Reference.fieldInitBlackRef = new FieldInit(Reference.BLACK_SQUARE);
        Reference.fieldInitWhiteRef = new FieldInit(Reference.WHITE_SQUARE);
        //Reference.placeHolderMap
        for (Reference.figureIDList id : Reference.figureList) {
            Reference.addToField(id.fieldPositionIndex, id.figureName, id.figureColor);
        }
    }
    public static void main(String[] args){
        new ChessMain();
    }
}
