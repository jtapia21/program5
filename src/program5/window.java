package program5;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/*
 * this class is to set up the gui
 */

public class window extends JFrame{
	static window singleton = null;
	
	public window(String name, SDD sdd){
		if (singleton == null) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(name);
		
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.add(sdd);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		singleton = this;
		} else {
		  singleton = this;
		}
		
	}// End of window
}// End of window method.
