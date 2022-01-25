import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


public class Updater implements ActionListener{

	Container c;
	
	public Updater(Container c){
		this.c = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		c.update();
	}
	
}
