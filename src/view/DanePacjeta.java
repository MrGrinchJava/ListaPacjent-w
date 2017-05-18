package view;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Commons;
import model.Pacjent;
/**
 * panel Badania
 *
 */
public class DanePacjeta extends JPanel{
	
	private JTextField imie;
	private JTextField nazwisko;
	private JTextField pesel;
	private JCheckBox kobietaCheckBox;
	private JCheckBox mezczyznaCheckBox;
	private JComboBox rodzajUbez;
	private JButton zapiszButton, anulujButton;
	
	private ButtonGroup plecCheckBoxes;
	private ArrayList<JLabel> labels;
	private ArrayList<JTextField> textFields;
	/**
	 * konstruktor klasy
	 */
	public DanePacjeta(){
		
		
		setSize(400,400);
		setLayout(null);
		
		labels = new ArrayList<JLabel>();
		textFields = new ArrayList<JTextField>();
		
		JLabel imieLabel = new JLabel("IMIE:");
		imieLabel.setBounds(0,20,120,20);
		add(imieLabel);
		labels.add(imieLabel);
		imie = new JTextField("");
		imie.setBounds(140, 20, 140, 20);
		add(imie);
		
		
		JLabel nazwiskoLabel = new JLabel("Nazwisko:");
		nazwiskoLabel.setBounds(0,60,120,20);
		add(nazwiskoLabel);
		labels.add(nazwiskoLabel);
		nazwisko = new JTextField("");
		nazwisko.setBounds(140, 60, 140, 20);
		add(nazwisko);
		
		JLabel peselLabel = new JLabel("PESEL:");
		peselLabel.setBounds(0,100,120,20);
		add(peselLabel);
		labels.add(peselLabel);
		pesel = new JTextField("");
		pesel.setBounds(140, 100, 140, 20);
		add(pesel);
		
		plecCheckBoxes = new ButtonGroup();
		JLabel plecLabel = new JLabel("Podaj Pleæ:");
		plecLabel.setBounds(0,140,120,20);
		add(plecLabel);
		labels.add(plecLabel);
		kobietaCheckBox = new JCheckBox();
		kobietaCheckBox.setBounds(130, 140, 20, 20);
		plecCheckBoxes.add(kobietaCheckBox);
		JLabel kobieta = new JLabel("Kobieta");
		kobieta.setBounds(160,140,80,20);
		add(kobieta);
		labels.add(kobieta);
		mezczyznaCheckBox = new JCheckBox();
		mezczyznaCheckBox.setBounds(220, 140, 20, 20);
		plecCheckBoxes.add(mezczyznaCheckBox);
		JLabel mezczyzna = new JLabel("Mê¿czyzna");
		mezczyzna.setBounds(260,140,120,20);
		add(mezczyzna);
		labels.add(mezczyzna);
		add(kobietaCheckBox);
		add(mezczyznaCheckBox);
		
		JLabel ubezLabel = new JLabel("Rodzaj Ubezpiczenia:");
		ubezLabel.setBounds(0,180,120,20);
		add(ubezLabel);
		labels.add(ubezLabel);
		rodzajUbez = new JComboBox();
		Object[] rodzaje = {"Brak", "Prywatne", "NFZ"};
		rodzajUbez = new JComboBox(rodzaje);
        rodzajUbez.setSelectedIndex(0);
        rodzajUbez.setBounds(140, 180, 120, 20);
        add(rodzajUbez);
        
        zapiszButton = new JButton("ZAPISZ");
        zapiszButton.setBounds(20, 220, 80, 20);
        add(zapiszButton);
        anulujButton = new JButton("ANULUJ");
        anulujButton.setBounds(115, 220, 80, 20);
        add(anulujButton);

        groupTextFields();
        setActive(false);
	}
	/**
	 * czysci  wypelniene pól panelu 
	 */
	public void clearPanel(){
		imie.setText("");
		nazwisko.setText("");
		pesel.setText("");
		rodzajUbez.setSelectedIndex(0);
		plecCheckBoxes.clearSelection();
		
		
	}
	/**
	 * uzupelnia pola danymi pacjenta z listy
	 * @param pacjent
	 */
	public void ustawDane(Pacjent pacjent){
		imie.setText(pacjent.getImie());
		nazwisko.setText(pacjent.getNazwisko());
		pesel.setText(pacjent.getPesel());
		if(pacjent.getUbezpiecznie() == "NFZ")
			rodzajUbez.setSelectedIndex(2);
		else if(pacjent.getUbezpiecznie() == "Prywatne")
			rodzajUbez.setSelectedIndex(1);
		else
			rodzajUbez.setSelectedIndex(0);
		
		if(pacjent.getPlec() == Commons.KOBIETA)
			kobietaCheckBox.setSelected(true);
		else
			mezczyznaCheckBox.setSelected(true);
		
    	
    	
    	
    }
	/**
	 * aktywacja i deaktywacja panelu
	 * @param isActive
	 */
	public void setActive(boolean isActive){
		this.setEnabled(isActive);
		for(JTextField textField : textFields){
			textField.setEnabled(isActive);
		}
		this.rodzajUbez.setEnabled(isActive);
		this.kobietaCheckBox.setEnabled(isActive);
		this.mezczyznaCheckBox.setEnabled(isActive);
		this.zapiszButton.setEnabled(isActive);
		this.anulujButton.setEnabled(isActive);
		
		for(JLabel label: labels){
			label.setEnabled(isActive);
		}
	}
	/**
	 * stworzenie grupy zawierajacej same JTextFields
	 */
	private void groupTextFields(){
		
		textFields.add(imie);
		textFields.add(nazwisko);
		textFields.add(pesel);
		
	}
	
	/**
	 * pobranie pacjenta
	 * @return
	 */
	public Pacjent getPacjent(){
		return new Pacjent(imie.getText(), nazwisko.getText(), pesel.getText(), rodzajUbez.getSelectedItem().toString(), getSex());
	}
	
	public String getSex(){
		if(kobietaCheckBox.isSelected()){
			return Commons.KOBIETA;
		}
		else if(mezczyznaCheckBox.isSelected())
			return Commons.MEZCZYZNA;
		else
			return null;
	}

	public JButton getZapiszButton() {
		return zapiszButton;
	}


	public JButton getAnulujButton() {
		return anulujButton;
	}

	public JTextField getImie() {
		return imie;
	}

	public void setImie(JTextField imie) {
		this.imie = imie;
	}

	public JTextField getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(JTextField nazwisko) {
		this.nazwisko = nazwisko;
	}

	public JTextField getPesel() {
		return pesel;
	}

	public void setPesel(JTextField pesel) {
		this.pesel = pesel;
	}

	public JComboBox getRodzajUbez() {
		return rodzajUbez;
	}

	public void setRodzajUbez(JComboBox rodzajUbez) {
		this.rodzajUbez = rodzajUbez;
	}

	public ButtonGroup getPlecCheckBoxes() {
		return plecCheckBoxes;
	}

	public void setPlecCheckBoxes(ButtonGroup plecCheckBoxes) {
		this.plecCheckBoxes = plecCheckBoxes;
	}
	
	
	


}
