import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class Window implements KeyListener{
	
	World w = new World();
	
	public static void main(String[] args){
		new Window().init();
	}
	
	public void init(){
		JFrame frame = new JFrame("Car Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(w);
		frame.addKeyListener(this);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		w.KeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		w.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
