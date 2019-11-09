package ChessGame;

import ChessGame.utils.DrawUtils;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class ChessMain {
    private static boolean ENABLE_STREAM_PRINTLN_OUTPUT = false;
    static String UBUNTU_MONO_FONT;
    private void windowInit() {
        JFrame window = new JFrame();
        JPanel mainPanel = new JPanel();
        try {
            // set windows look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ignored) {}

        ChessBoard cb = new ChessBoard();
        MoveHistoryListPanel mp = new MoveHistoryListPanel(cb);
        InOut.setRepaintComponent(cb);
        Dimension d = new Dimension(cb.getPreferredSize().width + mp.getPreferredSize().width, cb.getPreferredSize().height);





        GridBagLayout ml = new GridBagLayout();
        GridBagConstraints mc = new GridBagConstraints();
        mainPanel.setLayout(ml);
        mainPanel.setSize(d);

        mainPanel.add(cb);
        mc.anchor = GridBagConstraints.EAST;
        ml.setConstraints(mp, mc);
        mainPanel.add(mp);

        window.setTitle("The Chess");
        window.setLayout(null);
        window.setSize(new Dimension(d.width + DrawUtils.GAP, d.height + 40));
        window.getContentPane().add(mainPanel);
        window.setVisible(true);
        window.setResizable(false);
//        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PawnInAge.setRepaintComponent(cb);
    }

    private ChessMain() {
        GameLogic.getInstance().newGame();
        UBUNTU_MONO_FONT = Font.decode("Ubuntu Mono").getFontName();
    }


    public static void main(String[] args) {
        ChessMain ch = new ChessMain();
        PrintStream origOut = System.out;
        PrintStream interceptor = new CPrint(origOut);
        System.setOut(interceptor);
        SwingUtilities.invokeLater(()->{
            ch.windowInit();
            PawnInAge.getInstance().initFrame();
        });
    }
    static class CPrint extends PrintStream {

        public CPrint(OutputStream out) {
            super(out, true);
        }

        @Override
        public void println(String  obj) {
            if (ChessMain.ENABLE_STREAM_PRINTLN_OUTPUT)
                super.println(obj);
        }
    }
}
