package view;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.security.KeyStore;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionListener;

import view.*;
import walidator.PeselValidator;
import model.*;
import controller.*;


/**
 * klasa dziedziczaca po JFrame w której ³¹czone s¹ wszystkie panele 
 *
 */
public class Widok extends JFrame{
	
	private JMenuItem koniec;
	private BadanieView badanie;
	private DanePacjeta danePacjetaWidok;
	private ListaPacjentowView listaPacjentowWidok;
	/**
	 * konstruktor klasy Widok
	 */
	public Widok(){
		setMinimumSize(new Dimension(855, 540));
		setTitle("Rejestracja Wyników Badañ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		
		this.setJMenuBar(doMenu());
		badanie = new BadanieView();
		danePacjetaWidok = new DanePacjeta();
		Aplikacja aplikacja = new Aplikacja();
		setLayout(null);
		setLookAndFeel();
		aplikacja.setBounds(0, 0, 400, 10);
		danePacjetaWidok.setBounds(5, 10, 350, 300);
		danePacjetaWidok.setBorder(BorderFactory.createTitledBorder("DanePacjeta"));
		badanie.setBounds(5, 310, 350, 160);
		badanie.setBorder(BorderFactory.createTitledBorder("Badanie"));
		
		listaPacjentowWidok = new ListaPacjentowView();
		BazaDanych mModel = new BazaDanych();
		listaPacjentowWidok.setBounds(355, 10, 480, 460);
		listaPacjentowWidok.setBorder(BorderFactory.createTitledBorder("Lisata pacjetow"));
		
		
		//button.
		add(listaPacjentowWidok);
		add(aplikacja);
		add(danePacjetaWidok);
		add(badanie);
		
		ImageIcon img = new ImageIcon("ikonka.png");
        this.setIconImage(img.getImage());
		
        
        activateRemoveButton(false);
	}
	/**
	 * funkcja wybierajaca pacjenta(wiersz na liscie) z listy
	 */
	public Pacjent wybranyPacjent(){
		int row = listaPacjentowWidok.getmList().getSelectedRow();
		return listaPacjentowWidok.getModel().getPacjent(row);
		
	}
	/**
	 * funkcja nadajaca styl graficzny kontrolkom 
	 */
	private void setLookAndFeel(){
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * funkcja tworz¹ca menu u góry ekranu
	 * @return menu górne
	 */
	private JMenuBar doMenu(){
		JMenuBar menuBar = new JMenuBar();
		menuBar.setVisible(true);
		JMenu menu = new JMenu(Commons.MENU_APP);
		menuBar.add(menu);
		menu.setMnemonic(KeyEvent.VK_M);
		koniec = new JMenuItem(Commons.MENU_EXIT);
		menu.add(koniec);
		KeyStroke altF4 = KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK);
		koniec.setAccelerator(altF4);
        
        return menuBar;
	}

	/**
	 * funkcja dodajaca Listenera aby przycisk zareagowa³ na klikniecie
	 */
	public void setController(ActionListener c){
		 
		 this.koniec.addActionListener(c);
		 this.danePacjetaWidok.getZapiszButton().addActionListener(c);
		 this.danePacjetaWidok.getAnulujButton().addActionListener(c);
		 this.listaPacjentowWidok.getmAddButton().addActionListener(c);
		 this.listaPacjentowWidok.getmRemoveButton().addActionListener(c);
		 this.getBadanie().getZapiszBadanie().addActionListener(c);
		 this.getBadanie().getAnulujBadanie().addActionListener(c);
	}
	
	public void activateRemoveButton(boolean isActive){
		listaPacjentowWidok.getmRemoveButton().setEnabled(isActive);
	}
	/**
	 * sprawdzanie czy jakis wiersz nie jest zaznaczony
	 */
	public void setControllerForJTabel(ListSelectionListener lsl){
		listaPacjentowWidok.getSelectionModel().addListSelectionListener(lsl);
	}

	public DanePacjeta getDanePacjetaWidok() {
		return danePacjetaWidok;
	}
	
	
	
	public ListaPacjentowView getListaPacjentowWidok() {
		return listaPacjentowWidok;
	}
	
	

	public BadanieView getBadanie() {
		return badanie;
	}

	public JMenuItem getKoniec() {
		return koniec;
	}
	/**
	 * funkcja sprawdza czy wszystkie pola w panelu DanePacjenta sa uzupelnione
	 * @return zwraca true gdy sa
	 */
	public boolean czyUzupelnioneDanePacjeta(){
		if(danePacjetaWidok.getImie().getText().replaceAll("\\s", "").equals("") ){
			KomunikatView.infoBox("Brak imienia", "Uzupe³nij");
			return false;
		}
		else if(danePacjetaWidok.getNazwisko().getText().replaceAll("\\s", "").equals("")){
			KomunikatView.infoBox("Brak nazwiska", "Uzupe³nij");
			return false;
		}
		else if(danePacjetaWidok.getPesel().getText().replaceAll("\\s", "").equals("")){
			KomunikatView.infoBox("Brak pesel", "Uzupe³nij");
			return false;
		}
		else if(danePacjetaWidok.getPlecCheckBoxes().getSelection()==null){
			KomunikatView.infoBox("Nie zaznaczy³eœ p³uci", "Uzupe³nij");
			return false;
		}
		return true;
			
	}
	/**
	 * funkcja sprawdza czy wszystkie pola w panelu badanie sa uzupelnione
	 * @return zwraca true gdy sa
	 */
	public boolean czyUzupelnioneBadanie(){
		if(badanie.getLeukocyty().getText().replaceAll("\\s", "").equals("")){
			KomunikatView.infoBox("Nie uzupe³niona rubryka z Liczba Leukocytów", "Uzupe³nij");
			return false;
	}
		else if(badanie.getEozynofile().getText().replaceAll("\\s", "").equals("")){
			KomunikatView.infoBox("Nie uzupe³niona rubryka z Liczba Leukocytów", "Uzupe³nij");
			return false;
		}
		else if(badanie.getData().getDate() == null)
			return false;
		return true;
	}
	/**
	 * sprawdza czy podany przez uzytkownika pesel jest poprawny
	 */
	public boolean czyDobryPesel(String pesel){
		PeselValidator peselValidator = new PeselValidator(pesel);
		return peselValidator.isValid();
	}
	/**
	 funkcja sprawdza czy poziom Leukocytów we krwi jest odpowiedni 
	 * @return true gdy nasz poziom miesci sie w granicach
	 */
	public boolean czyDobreLeukocyty(){
		if(Integer.parseInt(badanie.getLeukocyty().getText())>10000 || Integer.parseInt(badanie.getLeukocyty().getText())<4500){
			KomunikatView.infoBox("Z³y poziom Leukocytów dobry znajduje siê miedzy 4500-10000", "Informacja");
			
				
				return false;
			}
			else
				return true;	
		}
	/**
	 * funkcja sprawdza czy poziom Eozynofilow we krwi jest odpowiedni 
	 * @return true gdy nasz poziom miesci sie w granicach 
	 */
	public boolean czyDobreEozynofile(){
		if(Integer.parseInt(badanie.getEozynofile().getText())>400 ||
				Integer.parseInt(badanie.getEozynofile().getText())<50){
			KomunikatView.infoBox("Z³y poziom Eozynofilów dobry znajduje siê miedzy 50-400", "Informacja");
				return false;
			}
			else
				return true;	
		}

	/**
	 * funkcja sprawdzajaca czy ciag znakow jest typu calkowitego
	 */
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	    	KomunikatView.infoBox("Liczba Leukocytów lub Liczba Eozynofili ma z³y format", "Informacja");
	        return false; 
	    }
	    return true;
	}
	/**
	 * funkcja sprawdzajaca czy w ciegu znakow nie ma innych znakow niz litery
	 * @param s s³owo które sprawdzamy
	 * @return zwracamy true gry slowo nie zawiera nic innego oprocz liter
	 */
	public boolean isOnlyLetter(String s) {
		    char[] chars = s.toCharArray();
		    for (char c : chars) {
		        if(!Character.isLetter(c)) {
		            return false;
		        }
		    }
		    return true;
		}
	

	
}
