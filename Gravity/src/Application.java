import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;


public class Application extends JFrame{
	
	private Simulation sim = new Simulation();
	private Controls controls = new Controls();
	
	public Application(){
		getContentPane().setBackground(Color.WHITE);
		setLayout(new MigLayout());
		sim.setController(controls);
		add(controls);
		add(sim);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new Application();
	}

}
