import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class World extends JPanel implements ActionListener{
	int width = 550;
	int height = 400;
	int view;
	Player player = new Player(this);
	ArrayList<NPC> cars = new ArrayList<NPC>();
	Timer clock = new Timer(30, this);
	Level level = new Level("Images/background.png", 1200, this);
	public World(){
		view = 0;
		setPreferredSize(new Dimension(width, height));
		clock.start();
	}
	
	public void update(){
		player.update();
		if(player.getY()+view < player.height && player.dir == Car.FORWARD && level.getY()+view < 0){
			view -= player.vy;
		}
		level.update();
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(level.getImage(), 0, level.getY()+view, null);
		g.drawImage(player.getImage(), player.getX(), player.getY()+view, null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		update();
	}

	public void KeyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'w'){
			player.dir = Car.FORWARD;
		}else if(e.getKeyChar() == 's'){
			player.dir = Car.BACKWARD;
		}else if(e.getKeyChar() == 'd'){
			player.vx = player.sideSpeed;
		}else if(e.getKeyChar() == 'a'){
			player.vx = player.sideSpeed*-1;
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == 'w'){
			player.dir = Car.NOT_MOVING;
		}else if(e.getKeyChar() == 's'){
			player.dir = Car.NOT_MOVING;
		}else if(e.getKeyChar() == 'd'){
			player.vx = 0;
		}else if(e.getKeyChar() == 'a'){
			player.vx = 0;
		}
	}
}
