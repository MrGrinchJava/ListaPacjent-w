package view;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import controller.Commons;
import model.BadanieModel;
import model.Pacjent;

/**
 * Klasa zawierajaca widok panelu Badanie
 *
 */
public class BadanieView extends JPanel {
	private JDateChooser data;
	private JTextField leukocyty, eozynofile;
	private JLabel dataLabel, leukocytyLabel, eozynofileLabel, checkBoxLabel;
	private JButton zapiszBadanie, anulujBadanie;
	Date date = null;
	private JCheckBox checkBox;
	private ArrayList<JLabel> labels;
	private ArrayList<JTextField> textFields;
	
	public BadanieView(){
		labels = new ArrayList<JLabel>();
		textFields = new ArrayList<JTextField>();
		setSize(400,150);
		setLayout(null);
		
		dataLabel = new JLabel("DATA:");
		dataLabel.setBounds(0,20,120,20);
		add(dataLabel);
		labels.add(dataLabel);
		data = new JDateChooser(new Date());
		data.setBounds(140, 20, 140, 20);
		add(data);
		data.setMinSelectableDate(new Date());
		
		
		leukocytyLabel = new JLabel("Liczba Leukocytów:");
		leukocytyLabel.setBounds(0,40,120,20);
		add(leukocytyLabel);
		labels.add(leukocytyLabel);
		leukocyty = new JTextField("");
		leukocyty.setBounds(140, 40, 140, 20);
		leukocyty.setToolTipText("Prawid³owy zakres to 4500-10000");
		add(leukocyty);
		textFields.add(leukocyty);
		eozynofileLabel = new JLabel("Liczba Eozynofilów:");
		eozynofileLabel.setBounds(0,60,120,20);
		add(eozynofileLabel);
		labels.add(eozynofileLabel);
		eozynofile = new JTextField("");
		eozynofile.setBounds(140, 60, 140, 20);
		eozynofile.setToolTipText("Prawid³owy zakres to 50-400");
		add(eozynofile);
		textFields.add(eozynofile);
		
		checkBoxLabel = new JLabel("Przeciwcia³a IgE:");
		checkBoxLabel.setBounds(0, 80, 120, 20);
		add(checkBoxLabel);
		labels.add(checkBoxLabel);
		checkBox = new JCheckBox();
		checkBox.setBounds(140, 80, 20, 20);
		add(checkBox);
		
		
		zapiszBadanie = new JButton("ZAPISZ");
		zapiszBadanie.setBounds(20, 120, 80, 20);
		add(zapiszBadanie);
		anulujBadanie = new JButton("ANULUJ");
		anulujBadanie.setBounds(110, 120, 80, 20);
		add(anulujBadanie);
		
		setActive(false);
		
		
	}
	/**
	 * funkcja ustawiajaca dane( jego badanie ) spowrotem do panelu Badanie
	 * @param pacjent 
	 */
	public void ustawDane(Pacjent pacjent){
		if(pacjent.getBadanie()!=null){
			eozynofile.setText(String.valueOf(pacjent.getBadanie().getLiczbaEozynofili()));
			leukocyty.setText(String.valueOf(pacjent.getBadanie().getLiczbaLeukocytow()));
			if(pacjent.getBadanie().isIgE())
				checkBox.setSelected(true);
			else
				checkBox.setSelected(false);
			data.setDate( Date.from(pacjent.getBadanie().getData().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
	}
	/**
	 * funkcja czyszczaca Panel badanie
	 */
	public void clearPanel(){
		leukocyty.setText("");
		eozynofile.setText("");
		data.setDate(new Date());
		checkBox.setSelected(false);	
	}
	/**
	 * utworzenie obiektu badanie na podstawie danych z panelu
	 * @return Badanie pacjenta
	 */
	public BadanieModel getBadanieModel(){
		int leukocytyInt = Integer.parseInt(leukocyty.getText());
		int eozynofileInt = Integer.parseInt(eozynofile.getText());
		boolean klasaIgE = checkBox.isSelected();
		LocalDate dataString = data.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		return new BadanieModel(leukocytyInt, eozynofileInt,dataString , klasaIgE);	
	}
	/**
	 * aktywuje i deaktuwuje panel Badanie
	 */
	public void setActive(boolean isActive){
		this.setEnabled(isActive);
		this.zapiszBadanie.setEnabled(isActive);
		this.anulujBadanie.setEnabled(isActive);
		this.checkBox.setEnabled(isActive);
		this.data.setEnabled(isActive);
		for(JTextField textField : textFields){
			textField.setEnabled(isActive);
		}
		
		for(JLabel label: labels){
			label.setEnabled(isActive);
		}
	}
	
	public JButton getZapiszBadanie() {
		return zapiszBadanie;
	}

	public JButton getAnulujBadanie() {
		return anulujBadanie;
	}

	public JDateChooser getData() {
		return data;
	}

	public void setData(JDateChooser data) {
		this.data = data;
	}

	public JTextField getLeukocyty() {
		return leukocyty;
	}

	public void setLeukocyty(JTextField leukocyty) {
		this.leukocyty = leukocyty;
	}

	public JTextField getEozynofile() {
		return eozynofile;
	}

	public void setEozynofile(JTextField eozynofile) {
		this.eozynofile = eozynofile;
	}

	
	
	
	
	

}
