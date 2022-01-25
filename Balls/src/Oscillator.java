import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Oscillator implements ActionListener{
	
	Container c;

	public Oscillator(Container c){
		this.c = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		c.oscillate();
	}
	
}