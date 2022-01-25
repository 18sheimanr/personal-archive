import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;


public class Controls extends JPanel{
	
	Simulation simulation;
	JButton update = new JButton("Update");
	JButton clear = new JButton("Clear All");
	JButton addPlanet = new JButton("Add Random Planet");
	JSlider gravSlider = new JSlider(1, 50, 20);
	
	public Controls(){
		setBackground(Color.WHITE);
		setLayout(new MigLayout("wrap 1"));
		gravSlider.addChangeListener(new ChangeListener(){ 
			public void stateChanged(ChangeEvent e) {
				Simulation.gravityConstant = (double) (gravSlider.getValue())/10.0;
			} 
		});
		update.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				//simulation.updateParameters();
			} 
		});
		clear.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				simulation.clear();
			} 
		});
		addPlanet.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				double radius = (Math.random()*20)+5;
				double mass = (Math.random()*20)+5;
				int x = (int) (Math.random()*800);
				int y = (int) (Math.random()*600);
				simulation.addPlanet(new Planet(radius, mass, new Point(x, y)));
			} 
		});
		add(gravSlider);
		add(update);
		add(clear);
		add(addPlanet);
	}

	public void setSimulation(Simulation s) {
		simulation = s;
	}

}
