import java.awt.Color;
import java.awt.Graphics2D;

public class Tile {
	private double x;
	private double y;
	private boolean black;
	private double dy = 0;
	public Tile(int x, int y, boolean black) {
		this.x = x;
		this.y = y;
		this.black = black;
	}
	public void draw(Graphics2D g2d) {
		g2d.setColor(black ? Color.BLACK: Color.WHITE);
		g2d.fillRect(getX(), getY(), 100, 100);
	}
	public int getX() {
		return (int) x;
	}
	public int getY() {
		return (int) y;
	}
	public boolean getBlack() {
		return black;
	}
	public void setdY(double dy) {
		this.dy = dy;
	}
	public void update() {
		y += dy;
	}
}
