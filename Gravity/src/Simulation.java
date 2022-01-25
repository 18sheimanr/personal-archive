import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Simulation extends JPanel implements ActionListener{
	
	private Controls controls;
	public static double gravityConstant = 2;
	private int width = 800;
	private int height = 600;
	private Timer clock = new Timer(16, this);
	private ArrayList<Planet> planets = new ArrayList<Planet>();
	
	public Simulation(){
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(width, height));
		clock.start();
	}

	public void setController(Controls c) {
		controls = c;
		controls.setSimulation(this);
	}

	public void addPlanet(Planet planet) {
		planets.add(planet);
	}

	public void clear() {
		planets.clear();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Planet p : planets){
			p.paint(g);
		}
	}
	
	public void update(){
		for(Planet p : planets){
			p.update(planets);
		}
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		update();
	}

}
