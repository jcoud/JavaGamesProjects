import javax.swing.*;
import java.awt.event.ActionListener;

import static main_test.reference.*;

/*
import static main_test.reference.GameParam.getGameMenuBar;
import static main_test.reference.GameParam.setGameMenuBar;
*/

class menuBar extends JMenuBar {
    static JMenuItem
            gmi_randColor, gmi_defColor,
            gmi_about, gmi_helpInfo,
            gmi_exit, gmi_newGame;
    menuBar(ActionListener listener) {
        //bar
        gameMenuBar = new JMenuBar();
        //labels
        JMenu gm_game = new JMenu("Game");
        JMenu gm_info = new JMenu("Info");
        //items:gm_game
        gmi_newGame = new JMenuItem("New game");
        gmi_exit = new JMenuItem("Exit");
        gmi_randColor = new JMenuItem("Randomize snake color");
        gmi_defColor = new JMenuItem("Default snake color");
        //items:gm_info
        gmi_about = new JMenuItem("About");
        gmi_helpInfo = new JMenuItem("Toggle game info");

        gmi_newGame.addActionListener(listener);
        gmi_exit.addActionListener(listener);
        gmi_randColor.addActionListener(listener);
        gmi_defColor.addActionListener(listener);

        gmi_about.addActionListener(listener);
        gmi_helpInfo.addActionListener(listener);

        gm_game.add(gmi_newGame);
        gm_game.add(gmi_exit);
        gm_game.add(gmi_randColor);
        gm_game.add(gmi_defColor);

        gm_info.add(gmi_about);
        gm_info.add(gmi_helpInfo);

        gameMenuBar.add(gm_game);
        gameMenuBar.add(gm_info);
    }
    JMenuBar getMenuBar(){
        return gameMenuBar;
    }
}
