import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Random;

import static main_test.menuBar.*;
import static main_test.reference.*;
/**
 *
 * @author Andrew Swan (a.k.a jcoud)
 * @version %I%, %G%
 * @link https://github.com/jcoud
 *
 */


@SuppressWarnings("serial")
public class sn_game_main extends JFrame implements ActionListener {
	private sn_game_main() {
		/*SETUP*/
		super("SNAKE GAME");
		grid_width = bs * 20;
		grid_height = bs * 20;
		try {
			InputStream imageInputStream = getClass().getResourceAsStream("/snake_icon.png");
			BufferedImage bufferedImage = ImageIO.read(imageInputStream);
			setIconImage(bufferedImage);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setJMenuBar(new menuBar(this).getMenuBar());
		timer = new Timer(delay, this);

		addKeyListener(new input_system());
		add(new sn_paint());
		/*GAME SYSTEM PARAMS*/
			/*INFO FIELD*/
			showInfoPanel();
			/*==========*/
			colorType_default = true;
			colorType_random = false;
			showControlInfo = true;
		/*==================*/
		getContentPane().setPreferredSize(new Dimension(grid_width + gap, grid_height + gap));
		pack();
		setDefaultLookAndFeelDecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas_startingPoint = new Point((getContentPane().getWidth() - grid_width)/2,(getContentPane().getHeight() - grid_height)/2);
		//game logic
		start_game();
		timer = new Timer(delay, this);
		timer.start();
	}

	private static void showInfoPanel(){
		message = new JPanel();
		JEditorPane jep = new JEditorPane("text/html",
				"<html><body style=\"font-family:consolas;\">" +
				"<span style=\"font-family:arial;font-weight: bold\">Author: </span>" + author + "<br>" +
				"<span style=\"font-family:arial;font-weight: bold\">Version: </span>" + version + "<br>" +
				"<span style=\"font-family:arial;font-weight: bold\">Visit: </span><a href=" + link + ">\"https://github.com/jcoud/\"</a></body></html>");
		jep.setEditable(false);
		jep.setOpaque(false);
		jep.addHyperlinkListener(hle -> {
			if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
				uri = URI.create(hle.getURL().toString());
			}
		});

		jep.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {open(uri);}
		});
		message.add(jep);
	}
	/**
	 * @param uri must be exactly the URI type,
	 *            otherwise to prevent error parameter need to be checked with IF statement
	 *            This is happening because when URI param was initiated it is null,
	 *            and param getting value only due to HyperlinkListener,
	 *            so when Mouse actions was detected, method called Open activated with null URI parameter
	 *            witch throwing errors.
	 */
	private static void open(URI uri) {
		if (uri.getClass().equals(URI.class)) {
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(uri);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,
							"Failed to launch the link, your computer is likely misconfigured.",
							"Cannot Launch Link", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Java is not able to launch links on your computer.",
						"Cannot Launch Link", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	static void start_game() {
		/*GAME STATS INIT*/
		score = 0;
		/*GAME ACTION INIT*/
		toggle_keys = true;
		st_newGame = true;
		st_running = false;
		st_pause = false;
		st_gameOver = false;
		/*DIRECTION INIT*/
		DIR_CURRENT = DIR_LEFT;

		/*SNAKE INIT*/
		snake.clear();
		head = new Point(5, 5);
		snake.add(new Point(head.x + 1, head.y));
		snakeLength = snake.size();
		setNewFruitPos();
	}

	private void snakeMove() {
		snake.add(head);
		if ((head.y - 1 >= 0) &&
			(head.y + 1 < grid_height / bs) &&
			(head.x - 1 >= 0) &&
			(head.x + 1 < grid_width / bs) &&
			isPointInTail(head))
		{
			if (DIR_CURRENT.equals(DIR_UP))     head = new Point(head.x, head.y - 1);
			if (DIR_CURRENT.equals(DIR_DOWN))   head = new Point(head.x, head.y + 1);
			if (DIR_CURRENT.equals(DIR_LEFT))   head = new Point(head.x - 1, head.y);
			if (DIR_CURRENT.equals(DIR_RIGHT))  head = new Point(head.x + 1, head.y);
		} else {
			st_running = false;
			st_gameOver = true;
		}
		if (head.equals(fruit)) {
			setNewFruitPos();
			snakeLength++;
			score++;
			if (score % 2 == 0 && delay > 5){
				level++;
				delay--;
			}
		}
		if (snake.size() > snakeLength) {
			snake.remove(0);
		}
	}
	private static void setNewFruitPos(){
		Random random = new Random();
		Point       p = new Point(random.nextInt(grid_width / bs), random.nextInt(grid_height / bs));
		while (isPointInTail(p) || (head.equals(p))) {
			p = new Point(random.nextInt(grid_width / bs), random.nextInt(grid_height / bs));
		}
		fruit = p;
	}
	private static boolean isPointInTail(Point point) {
		for (Point p : snake)
			if (p.equals(point)) return true;
		return false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gmi_newGame) {start_game();}
		if (e.getSource() == gmi_exit) {System.exit(0);}
		if (e.getSource() == gmi_about) {JOptionPane.showMessageDialog(null, message, "About", JOptionPane.INFORMATION_MESSAGE);}
		if (e.getSource() == gmi_helpInfo) {showGameText = !showGameText;}
		if (e.getSource() == gmi_randColor) {randomizeSnakeColor();}
		if (e.getSource() == gmi_defColor) {colorType_default = true;}
		tick = tick > 1000 ? 0 : tick+1;
		if (tick % delay == 0 && st_running) {
			snakeMove();
			toggle_keys = true;
		}

		repaint();
	}
	public static void main(String[] args) {
		new sn_game_main();
	}
}