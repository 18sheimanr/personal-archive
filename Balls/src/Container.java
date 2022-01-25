
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Container extends JPanel implements MouseListener{
	
	private boolean lighter;
	private int gravity = 0;
	private Timer updater = new Timer(20, new Updater(this));
	private Timer oscillator = new Timer(70, new Oscillator(this));
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	
	public Container(int w, int h){
		setPreferredSize(new Dimension(w, h));
		setBackground(Color.BLACK);
		this.addMouseListener(this);
		updater.start();
		oscillator.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Ball b : balls){
			b.draw(g);
		}
	}
	
	public void update(){
		repaint();
		for(Ball b : balls){
			b.update(gravity, getWidth(), getHeight());
		}
		if(Math.random() < 0.03 && (!balls.isEmpty())){
			balls.remove(Math.random()*balls.size());
		}
	}
	
	public void oscillate(){
		if(lighter){
			//setBackground(new Color(randValue(0, 120), randValue(0, 120), randValue(0, 120)));
			setBackground(Color.BLACK);
			lighter = false;
		}else{
			setBackground(new Color(randValue(120, 256), randValue(120, 256), randValue(120, 256)));
			lighter = true;
		}
	}
	
	public int randValue(int from, int to){
		return (int) ((Math.random()*(to-from))+from);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < 15; i++){
			balls.add(new Ball(e.getX(), e.getY()));
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
