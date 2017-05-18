package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Aplikacja extends JPanel implements ActionListener{
	public Aplikacja(){
		//JLabel aplikacja = new JLabel("Aplikacja:");
		//aplikacja.setBounds(0, 0, 100, 20);
		//add(aplikacja);
		
		//JButton button = new JButton("ZAMKNIJ");
		//button.setBounds(220, 0, 100, 20);
		//add(button);
		//button.addActionListener(this);
			
		
	}
	
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(0);
		
	}

}
