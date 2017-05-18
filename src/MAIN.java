import javax.swing.SwingUtilities;

import controller.Controller;
import model.BazaDanych;
import model.Pacjent;
import view.Widok;
/**
 *wywolanie klas 
 */
public class MAIN {
	
	public static void main(String[] args ){
		Runnable thread = new Runnable(){
			@Override
			public void run(){
				Widok widok = new Widok();
				BazaDanych bazaDanych= new BazaDanych();
				widok.setVisible(true);
				Controller control = new Controller(widok, bazaDanych);
				widok.setController(control);
				widok.setControllerForJTabel(control);
			}	
		};
		SwingUtilities.invokeLater(thread);
		
	}
	
	
	
	
	

}
