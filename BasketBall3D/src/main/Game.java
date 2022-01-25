package main;

import java.awt.GraphicsConfiguration;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.vecmath.*;

public class Game extends JFrame implements ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double x;
	double y;
	double z;
	double vy;
	double vx;
	double vz;
	boolean thrown = false;
	boolean turnDone = false;
	double gravity = 0.01;
	int score;
	int xnaught;
	int ynaught;
	Sphere basketBall = new Sphere(0.2f);
	TransformGroup t;
	Transform3D t3d;
	Color3f white = new Color3f(1f,1f,1f);
	Color3f black = new Color3f(0f,0f,0f);
	Color3f orange = new Color3f(0.9f, 0.5f, 0.1f);
	Color3f gray = new Color3f(0.6f,0.6f,0.6f);
	BranchGroup balls;
	Timer timer = new Timer(40, this);
	public Game(){
		
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas = new Canvas3D(config);
		
		//Create universe and branchgroup
		SimpleUniverse world = new SimpleUniverse(canvas);
		world.getCanvas().addMouseListener(this);
		balls = new BranchGroup();
		
		
		
		//Add background
		Background background = new Background(white);
		BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 10000);
		background.setApplicationBounds(sphere);
		balls.addChild(background);
		
		createBall();
		
		//Create Basket
		TransformGroup tp = new TransformGroup();
		Transform3D t3dp = new Transform3D();
		t3dp.setTranslation(new Vector3f(0f, -0.5f, -3.8f));
		tp.setTransform(t3dp);
		tp.addChild(new Cylinder(0.15f, 2.5f));
		balls.addChild(tp);
		
		//create shiny appearance for backboard
		Appearance boardAppearance = new Appearance();
		boardAppearance.setColoringAttributes(new ColoringAttributes(gray, ColoringAttributes.NICEST));
		Material boardMaterial = new Material(white, black, white, white, 70f);
		boardAppearance.setMaterial(boardMaterial);
		
		Box backBoard = new Box(0.7f, 0.5f, 0.05f, boardAppearance);
		TransformGroup tback = new TransformGroup();
		Transform3D t3dback = new Transform3D();
		t3dback.setTranslation(new Vector3f(0f, 0.35f, -3.65f));
		tback.setTransform(t3dback);
		tback.addChild(backBoard);
		balls.addChild(tback);
		
		//add lighting
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);
		
		Vector3f light1Direction = new Vector3f(0f, -1f, 0f);
		DirectionalLight light1 = new DirectionalLight(white, light1Direction);
		light1.setInfluencingBounds(bounds);
		
		Vector3f light2Direction = new Vector3f(0f, 1.5f, -1f);
		DirectionalLight light2 = new DirectionalLight(white, light2Direction);
		light2.setInfluencingBounds(bounds);
		
		AmbientLight light3 = new AmbientLight(white);
		light2.setInfluencingBounds(bounds);
		
		balls.addChild(light1);
		balls.addChild(light2);
		balls.addChild(light3);
		
		//add the branchgroup and set visible
		world.addBranchGraph(balls);
		world.getViewingPlatform().setNominalViewingTransform();
		
		getContentPane().add(canvas);
		timer.start();
	}
	public void setLocation(double changeX, double changeY, double changeZ){
		t3d.setTranslation(new Vector3f((float) changeX, (float) changeY, (float) changeZ));
		t.setTransform(t3d);
	}
	@Override
	public void actionPerformed(ActionEvent a){
		if(thrown){
			vy -= gravity;
		}
		y += vy;
		x += vx;
		z += vz;
		if(y < -1.3f){
			if(turnDone){
				if(z > -2f || z < 3f){
					turnDone = false;
					thrown = false;
					x = 0;
					vx = 0;
					y = 0;
					vy = 0;
					z = 0;
					vz = 0;
					System.out.println("Starting new turn");
				}
			}else{
				y = -1.3f;
				vy = -vy-0.05f;
			}
		}
		setLocation(x, y, z);
		if(z < -3.6f){
			turnDone = true;
			if(x < 0.5f && x > -0.5f && y < 0.7f && y > -0.6f){
				vz = -vz-0.05f;
			}
			if(x > 0.16f || x < -0.16f){
				System.out.println("Horizontal location ("+x+"f) too far from basket.");
			}
			else if(y > 0.65f || y < -0.45f){
				System.out.println("Vertical location ("+y+"f) too far from basket");
			}else{
				score += 1;
				System.out.println("Ball in Basket! 1 point added. current score: "+score);
			}
		}
	}
	public static void main(String[] args){
		System.setProperty("sun.awt.noerasebackground", "true");
		Game frame=new Game();
	    frame.setTitle("BasketBall");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setBounds(0,0,600,450);
	    frame.setResizable(false);
	    frame.setVisible(true);
	}
	public void createBall(){
		Appearance ballAppearance = new Appearance();
		ballAppearance.setColoringAttributes(new ColoringAttributes(orange, ColoringAttributes.NICEST));
		ballAppearance.setMaterial(new Material(orange, black, orange, black, 0f));
		basketBall.setAppearance(ballAppearance);
		
		t = new TransformGroup();
		t.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		t3d = new Transform3D();
		t3d.setTranslation(new Vector3f(0f, 0f, 0f));
		t.setTransform(t3d);
		t.addChild(basketBall);
		balls.addChild(t);
	}
	public void createLighting(){
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Point p = MouseInfo.getPointerInfo().getLocation();
		xnaught = p.x;
		ynaught = p.y;
		System.out.println("Mouse down");
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse up");
		if(thrown == false){
			thrown = true;
			Point p = MouseInfo.getPointerInfo().getLocation();
			int newx = p.x;
			int newy = p.y;
			double xchange = newx-xnaught;
			double ychange = Math.abs(newy-ynaught);
			vx = xchange/1000;
			vy = ychange/1000;
			vz = -0.15;
		}
	}
}
