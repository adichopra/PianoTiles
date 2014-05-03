import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel{
	static int HEIGHT = 400;
	static int WIDTH = 400;
	static int TILE_SIZE = 100;
	static int[] keys = new int[]{
		KeyEvent.VK_A, 
		KeyEvent.VK_S, 
		KeyEvent.VK_D, 
		KeyEvent.VK_F
		};
	static Tile[][] board = new Tile[50][4];
	static boolean keypress = false;
	static int counter = 0;
	static long start = System.currentTimeMillis();
	public Game() {
		for (int i = 0; i < board.length; i++) {
			int random = (int) (Math.random() * 4);
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = new Tile(j * TILE_SIZE, (i - 46) * TILE_SIZE, j == random);
			}
		}
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for (Tile[] t: board) {
			for (Tile t1: t) {
				t1.draw(g2d);
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Piano Tiles in Java by Aditya Chopra");
		final Game game = new Game();
		frame.add(game);
//		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
//		frame.setUndecorated(true); 
		frame.setSize(WIDTH + 5, HEIGHT + 20);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int ID = e.getKeyCode();
                for (int i = 0; i < keys.length; i++) {
                	if (keys[i] == ID) {
                		ID = i;
                		break;
                	}
                }
                if (!board[board.length - 1 - counter][ID].getBlack()) lose();
                else {
                	keypress = true;
                }
            }
        });
		while (true) {
			game.update();
			Thread.sleep(3);
		}
	}
	private void update() {
		repaint();
		if (counter == 49) {
			System.out.println("YOUR TIME WAS: " + (double) (System.currentTimeMillis() - start) / 1000 + "SECONDS");
			System.exit(0);
		}
		for (Tile[] t: board) {
			for (Tile t1: t) {
				t1.update();
			}
		}
		if (keypress) {
			for (Tile[] t: board) {
				for (Tile t1: t) {
					t1.setdY(1);
				}
			}
			counter++;
			keypress = false;
		}
		if (board[board.length - 1 - counter][0].getY() > HEIGHT - TILE_SIZE) {
			for (Tile[] t: board) {
				for (Tile t1: t) {
					t1.setdY(0);
				}
			}
			keypress = false;
		}
	}
	private static void lose() {
		start -= 250;
		System.out.println("WRONG TILE! (+ .25 seconds)");
	}
}
