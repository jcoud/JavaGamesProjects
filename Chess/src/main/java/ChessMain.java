import javax.swing.JFrame;
import java.awt.Dimension;

public class ChessMain{
    static Drawing drawing;
    {
        setupReference();
        JFrame window = new JFrame();
//        JFrame.setDefaultLookAndFeelDecorated(true);
        drawing = new Drawing();
        drawing.addMouseListener(new MouseAndKeys.MouseIOAdapter());
//        drawing.addMouseMotionListener(new MouseAndKeys.MouseIOMotionAdapter());
        window.setTitle("The Chess");
        window.add(drawing);
        window.addKeyListener(new MouseAndKeys.KeysIO());
        window.getContentPane().setPreferredSize(
            new Dimension(
                Reference.Canvas.x0 + Reference.Canvas.width + 200,
                Reference.Canvas.y0 + Reference.Canvas.height
            )
        );

        window.setVisible(true);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void setupReference(){
        Reference.fieldInitRef = new FieldInit();
        Reference.figureInitRef = new FigureInit();
    }
    public static void main(String[] args){ new ChessMain(); }
}
