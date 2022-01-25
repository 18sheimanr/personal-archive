import java.awt.Color;
import java.awt.Graphics;


public class Ball {

	private int x, y;
	private int dx, dy;
	private int size;
	private Color color;
	
	public Ball(int x, int y){
		this.x = x;
		this.y = y;
		dx = (int) (Math.random()*18)-9;
		dy = (int) (Math.random()*18)-9;
		size = (int) (Math.random()*50)+10;
		color = randomColor();
	}
	
	public void update(int grav, int Hwall, int Vwall){
		x += dx;
		y += dy;
		dy += grav;
		if(x > Hwall || x < 0){
			dx = -dx;
		}
		if(y > Vwall || y < 0){
			dy = -dy;
		}
	}
	
	private Color randomColor(){
		int r = (int) (Math.random()*255);
		int g = (int) (Math.random()*255);
		int b = (int) (Math.random()*255);
		return new Color(r, g, b);
	}
	
	public void draw(Graphics g){
		g.setColor(color);
		g.fillOval(x, y, size, size);
	}
}
