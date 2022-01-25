import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class Planet {
	
	private double radius;
	private double mass;
	private Point location; //represents center of planet
	private double dx;
	private double dy;
	private Color color = Color.YELLOW;

	public Planet(double r, double m, Point p) {
		radius = r;
		mass = m;
		location = p;
	}

	public void update(ArrayList<Planet> planets) {
		double forceX = 0;
		double forceY = 0;
		for(Planet p : planets){
			if(!p.equals(this)){
				double dist = location.distance(p.getLocation());
				double force = (Simulation.gravityConstant*mass*p.getMass())/dist;
				forceX += (p.getLocation().getX()-location.getX())*(force/dist);
				forceY += (p.getLocation().getY()-location.getY())*(force/dist);
			}
		}
		dx += forceX/mass;
		dy += forceY/mass;
		if(location.getX() > 800-radius){
			location.setLocation(800-radius, location.getY());
			dx = -dx;
		}
		if(location.getX() < 0){
			location.setLocation(0, location.getY());
		}
		if(location.getY() > 600-radius){
			 dy = -dy;
			 location.setLocation(location.getX(), 600-radius);
		}
		if(location.getY() < 0){
			 dy = -dy;
			 location.setLocation(location.getX(), 0);
		}
		for(Planet p : planets){
			if(!p.equals(this)){
				double dist = location.distance(p.getLocation());
				if(dist <= radius+p.getRadius()){
					location.setLocation(location.getX()-dx, location.getY()-dy);
					dx = -dx;
					dy = -dy;
				}
			}
		}
		location.setLocation(location.getX()+dx, location.getY()+dy);
	}

	private double getRadius() {
		return radius;
	}

	private double getMass() {
		return mass;
	}

	private Point2D getLocation() {
		return location;
	}

	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval((int) (location.getX()-radius),(int) (location.getY()-radius),(int) radius*2,(int) radius*2);
	}

}
