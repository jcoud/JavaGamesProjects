package ChessGame;

import ChessGame.utils.DrawUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MoveHistoryListPanel extends JPanel {

    public MoveHistoryListPanel(Component chessBoardComponent) {
        JPanel lb = new JPanel();
        JList<String> jl = createMoveList();

        lb.setPreferredSize(new Dimension(DrawUtils.MoveList.width, DrawUtils.MoveList.height));
        setSize(new Dimension(lb.getPreferredSize().width + DrawUtils.GAP * 2, lb.getPreferredSize().height + DrawUtils.GAP * 2));

        String s = "code writer - jcoud @";
        JLabel tag = new JLabel(s);
        tag.setForeground(Color.GRAY);
        tag.setFont(new Font(ChessMain.UBUNTU_MONO_FONT, Font.PLAIN, tag.getFont().getSize()));
        tag.setPreferredSize(new Dimension(tag.getFontMetrics(tag.getFont()).stringWidth(s+"___"), DrawUtils.GAP));
//        tag.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        s = "https://github.com/jcoud";
        JButton tagLink = new JButton(s);
        tagLink.setFont(new Font(ChessMain.UBUNTU_MONO_FONT, Font.PLAIN, tagLink.getFont().getSize()));
        tagLink.setOpaque(false);
        tagLink.setForeground(Color.GRAY);
        tagLink.setFocusable(false);
        tagLink.setContentAreaFilled(false);
        tagLink.setHorizontalAlignment(JButton.RIGHT);
        tagLink.setMargin(new Insets(0,0,0,0));
        tagLink.setPreferredSize(new Dimension(tagLink.getFontMetrics(tagLink.getFont()).stringWidth(s+"_"), DrawUtils.GAP));
        tagLink.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                tagLink.setForeground(Color.BLUE);
            }
        });
        tagLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                tagLink.setForeground(Color.GRAY);
            }
        });
        tagLink.addActionListener(l -> {
            try {
                open(new URI(l.getActionCommand()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

//        setBorder(BorderFactory.createLineBorder(Color.RED));
//        lb.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        lb.setLayout(gbl);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = gbc.weighty = 0f;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.SOUTH;
        setBtsPanel(lb, gbc, gbl, jl, chessBoardComponent);

        gbc.gridy = 0;
        gbc.weightx = gbc.weighty = 1f;
        gbc.insets.bottom = DrawUtils.GAP;
        gbc.anchor = GridBagConstraints.NORTH;
        setMoveList(lb, gbc, gbl, jl);

        GridBagLayout ml = new GridBagLayout();
        GridBagConstraints mc = new GridBagConstraints();
        setLayout(ml);


        mc.insets.right = DrawUtils.GAP;
        mc.insets.top = DrawUtils.GAP;
        mc.anchor = GridBagConstraints.NORTH;
        mc.gridwidth = 2;
        ml.setConstraints(lb, mc);
        add(lb);


        mc.insets.top = 0;
        mc.insets.bottom = 0;
        mc.gridy = 1;
        mc.gridx = 1;
        mc.anchor = GridBagConstraints.EAST;
        ml.setConstraints(tagLink, mc);
        add(tagLink);

        mc.gridx = 0;
        mc.insets.right = tagLink.getPreferredSize().width;
        ml.setConstraints(tag, mc);
        add(tag);
    }

    private static void open(URI uri) {
        if (uri.getClass().equals(URI.class)) {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(uri);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,
                            "Failed to launch the link, your computer is likely misconfigurated.",
                            "Cannot Launch Link", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Java is not able to launch links on your computer.",
                        "Cannot Launch Link", JOptionPane.WARNING_MESSAGE);
            }
        }
    }


    private JList<String> createMoveList() {
        JList<String> jl = new JList<>(GameLogic.getInstance().moveDataInfo);

        jl.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        jl.setFont(new Font(ChessMain.UBUNTU_MONO_FONT, Font.BOLD, 16));
        jl.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JPanel cell = new JPanel();
                JLabel l = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                l.setText((String) value);
                l.setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));
                cell.setOpaque(true);
                cell.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
                cell.setBackground(Color.WHITE);
                cell.add(l);
                return l;
            }
        });
        jl.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        jl.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        jl.setVisibleRowCount(-1);
        jl.setSelectedIndex(jl.getModel().getSize());
        jl.addMouseListener(new MouseAdapter() {
            Integer s;
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jl.getModel().getSize() == 0) return;
                if (s != null && s == jl.getSelectedIndex()) {
                    jl.clearSelection();
                    s = null;
                    return;
                }
                s = jl.getSelectedIndex();
                GameLogic gl = GameLogic.getInstance();
                MoveData data = gl.moveDataList.get(gl.moveDataInfo.indexOf(jl.getSelectedValue()));
                if (data != null) jl.setSelectionInterval(s, jl.getModel().getSize());
            }
        });
        return jl;
    }

    private void setMoveList(JPanel root, GridBagConstraints constraints, GridBagLayout layout,  JList jl) {
        JScrollPane sp = new JScrollPane(jl);
        layout.setConstraints(sp, constraints);
        root.add(sp);

    }

    private void setBtsPanel(JPanel root, GridBagConstraints constraints, GridBagLayout layout, JList<String> jl, Component c) {
        JPanel btn_p = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints constraints_btn = new GridBagConstraints();
        btn_p.setLayout(gridbag);
        constraints_btn.fill = GridBagConstraints.HORIZONTAL;
        constraints_btn.weightx  = 1f;
        constraints_btn.insets.right = DrawUtils.GAP;
        btRevOne(btn_p, constraints_btn, gridbag, jl, c);
        constraints_btn.insets.right = 0;
        btRevSelected(btn_p, constraints_btn, gridbag, jl, c);
//        btn_p.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        layout.setConstraints(btn_p, constraints);
        root.add(btn_p);
    }

    private void btRevOne(JPanel root, GridBagConstraints constraints, GridBagLayout layout, JList<String> jl, Component c) {
        JButton btn_revOne = new JButton("Reverse one");
        btn_revOne.addActionListener((e)-> {
            if (jl.getModel().getSize() == 0) return;
            int idx = jl.getModel().getSize() - 1;
            GameLogic gl = GameLogic.getInstance();
            gl.reverseMove(gl.moveDataList.get(gl.moveDataInfo.indexOf(jl.getModel().getElementAt(idx))));
            gl.updateBoard();
            c.repaint();
        });
        btn_revOne.setPreferredSize(new Dimension(120, 30));
        layout.setConstraints(btn_revOne, constraints);
        root.add(btn_revOne);
    }

    private void btRevSelected(JPanel root, GridBagConstraints constraints, GridBagLayout layout, JList<String> jl, Component c) {
        JButton btn_revSelected = new JButton("Reverse selected");
        btn_revSelected.addActionListener((e)-> {
            GameLogic gl = GameLogic.getInstance();
            if (jl.getModel().getSize() == 0) return;
            MoveData data = gl.moveDataList.get(gl.moveDataInfo.indexOf(jl.getModel().getElementAt(jl.getSelectedIndex())));
            if (data == null) return;
            final int a = jl.getLastVisibleIndex();
            final int s = jl.getSelectedIndex();
            for (int i = a; i >= s; i--) {
                gl.reverseMove(gl.moveDataList.get(gl.moveDataInfo.indexOf(jl.getModel().getElementAt(i))));
            }
            gl.updateBoard();
            c.repaint();
        });
        btn_revSelected.setPreferredSize(new Dimension(120, 30));
        layout.setConstraints(btn_revSelected, constraints);
        root.add(btn_revSelected);
    }
}
