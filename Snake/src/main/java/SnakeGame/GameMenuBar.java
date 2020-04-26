package SnakeGame;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class GameMenuBar extends JMenuBar implements ActionListener {
    private static JComponent repaintModule;

    static void setModule(JComponent c) {
        repaintModule = c;
    }

    private JMenuItem
            gmi_randColor,
            gmi_about, gmi_helpInfo,
            gmi_exit, gmi_newGame;
    private GameLogic gl = GameLogic.getInstance();

    GameMenuBar() {
        //bar
        //labels
        JMenu gm_game = new JMenu("Game");
        JMenu gm_info = new JMenu("Info");
        //items:gm_game
        gmi_newGame = new JMenuItem("New game");
        gmi_exit = new JMenuItem("Exit");
        gmi_randColor = new JMenuItem("Randomize snake color");
        //items:gm_info
        gmi_about = new JMenuItem("About");
        gmi_helpInfo = new JMenuItem("Toggle game info");

        gmi_newGame.addActionListener(this);
        gmi_exit.addActionListener(this);
        gmi_randColor.addActionListener(this);

        gmi_about.addActionListener(this);
        gmi_helpInfo.addActionListener(this);

        gm_game.add(gmi_newGame);
        gm_game.add(gmi_exit);
        gm_game.add(gmi_randColor);

        gm_info.add(gmi_about);
        gm_info.add(gmi_helpInfo);

        add(gm_game);
        add(gm_info);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gmi_newGame) {
            GameLogic.getInstance().startNewGame();
        }
        if (e.getSource() == gmi_exit) {
            System.exit(0);
        }
        if (e.getSource() == gmi_about) {
            JOptionPane.showMessageDialog(null, new PopupMessage(), "About", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == gmi_helpInfo) {
            gl.show_gameStats = !gl.show_gameStats;
        }
        if (e.getSource() == gmi_randColor) {
            gl.colorNormal = !gl.colorNormal;
        }
        repaintModule.repaint();
    }

    private static class PopupMessage extends JEditorPane {
        static final String
                version = "#version",
                author = "Andrew Swan (aka jcoud)",
                link = "https://www.github.com/jcoud";
        PopupMessage() {
            super("text/html",
                    "<html><body style=\"font-family:consolas;\">" +
                    "<span style=\"font-family:arial;font-weight: bold\">Author: </span>" + author + "<br>" +
                    "<span style=\"font-family:arial;font-weight: bold\">Version: </span>" + version + "<br>" +
                    "<span style=\"font-family:arial;font-weight: bold\">Visit: </span><a href=" + link + ">" + link + "</a></body></html>");
            setEditable(false);
            setOpaque(false);
            addHyperlinkListener(hle -> {
                if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
                    open(hle.getURL());
                }
            });
        }
    }

    private static void open(URL url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(url.toURI());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Failed to launch the link, your computer is likely misconfigured.",
                        "Cannot Launch Link", JOptionPane.WARNING_MESSAGE);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Java is not able to launch links on your computer.",
                    "Cannot Launch Link", JOptionPane.WARNING_MESSAGE);
        }
    }
}
