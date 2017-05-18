package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import com.toedter.calendar.JCalendar;

import model.BazaDanych;
import model.Pacjent;
import view.KomunikatView;
import view.Widok;
/**
 * klasa odpowiadajaca za odpowiedzi po kliknieciach na przyciski w programie 
 */
public class Controller implements ActionListener, ListSelectionListener{
	
	private Widok widok;
	private BazaDanych bazaDanych;
	private boolean isRowSelected= false;
	/**
	 * konstruktor klasy
	 */
	public Controller(Widok widok, BazaDanych bazaDanych) {
		super();
		this.widok = widok;
		this.bazaDanych = bazaDanych;
		
	}
	

	/**
	 * funkcja ob³ugujaca przyciski
	 */
	public void actionPerformed(ActionEvent evt)
    {     
		Object source = evt.getSource();
		//w momencie gdy poprawnie uzupelnilismy panel DanePacjenta guzik Zapisz dodaje go do listy
		if(source == widok.getDanePacjetaWidok().getZapiszButton() && widok.czyUzupelnioneDanePacjeta() && widok.czyDobryPesel(widok.getDanePacjetaWidok().getPacjent().getPesel()) && widok.isOnlyLetter(widok.getDanePacjetaWidok().getPacjent().getImie()) && widok.isOnlyLetter(widok.getDanePacjetaWidok().getPacjent().getNazwisko())){
			System.out.println(isRowSelected);
			//jezeli wybralismy pacjenta z listy to mozemy go edytowac
			if(isRowSelected){
				System.out.println("WYPISUJEMY");
				int row = widok.getListaPacjentowWidok().getmList().getSelectedRow();
				bazaDanych.edytujPacjenta(widok.getDanePacjetaWidok().getPacjent() , row );
			}
			else{	//jezeli to nowy pacjent 
				System.out.println(widok.getDanePacjetaWidok().getPacjent().getNazwisko());
				if(bazaDanych.czyPeselSieNiePowtarza(widok.getDanePacjetaWidok().getPacjent().getPesel()))//pesel sie nie powtarza
						bazaDanych.getListaPacjentow().add(widok.getDanePacjetaWidok().getPacjent());
				else{
					KomunikatView.infoBox("Taki pesel juz istnieje", "Informacja");
					return;
				}
			}
			widok.getListaPacjentowWidok().getModel().setListaPacjentow(bazaDanych.getListaPacjentow());
			System.out.println(bazaDanych.getListaPacjentow());
			widok.getDanePacjetaWidok().clearPanel();
			widok.getBadanie().clearPanel();
			widok.getDanePacjetaWidok().setActive(false);
			KomunikatView.infoBox("Pacjent zosta³ dodany", "Informacja");
			
		}
		//przyciskiem anuluj w panelu DanePacjenta czyscimy wszystkie wybory w tym panelu oraz odznaczamy wybrany na liscie record
		if(source == widok.getDanePacjetaWidok().getAnulujButton()){
			widok.getDanePacjetaWidok().clearPanel();
			widok.getListaPacjentowWidok().getmList().clearSelection();
			
		}
		// aktywujemy panel DanePacjenta
		if(source == widok.getListaPacjentowWidok().getmAddButton()){
			
			widok.getDanePacjetaWidok().setActive(true);
		}
		//usuwanie z listy zaznaczonego pacjenta
		if(source == widok.getListaPacjentowWidok().getmRemoveButton()){
			bazaDanych.removePacjent(widok.getListaPacjentowWidok().getmList().getSelectedRow());
			
			widok.getListaPacjentowWidok().getModel().setListaPacjentow(bazaDanych.getListaPacjentow());
			widok.getDanePacjetaWidok().clearPanel();
			widok.getBadanie().clearPanel();
		}
		//przycisk zapisz w panelu Badanie zapisuje badz edytuje badanie pacjenta
		if(source == widok.getBadanie().getZapiszBadanie() && widok.czyUzupelnioneBadanie() && widok.isInteger(widok.getBadanie().getLeukocyty().getText()) && widok.isInteger(widok.getBadanie().getEozynofile().getText())){

			System.out.println("WYPISUJEMY");
			if(widok.getDanePacjetaWidok().getPacjent().getBadanie() != null){
				int row = widok.getListaPacjentowWidok().getmList().getSelectedRow();
				bazaDanych.edytujPacjenta(widok.getDanePacjetaWidok().getPacjent() , row );
			}
			else{
				bazaDanych.dodajBadanie(widok.wybranyPacjent(), widok.getBadanie().getBadanieModel() );
			}
				widok.getListaPacjentowWidok().getModel().setListaPacjentow(bazaDanych.getListaPacjentow());
				widok.czyDobreEozynofile();
				widok.czyDobreLeukocyty();
				widok.getBadanie().clearPanel();
				widok.getDanePacjetaWidok().clearPanel();
				KomunikatView.infoBox("Badanie zosta³o dodane", "Informacja");
		}
		//przycisk anuluj w panelu Badanie czyscie wypelnione pola w tym panelu
		if(source == widok.getBadanie().getAnulujBadanie()){
			widok.getBadanie().clearPanel();
		}
		//zakonczenie dzialania programu poprzez klikniecie przycisku zakoncz w menu górnym
    	if (evt.getActionCommand().equals(Commons.MENU_EXIT))
    	{
    		System.exit(0);
    	}
    	
    }
    


	/**
	 * obsluga tabeli
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) { 
		//gdy zosta³ wybrany ktorys wiersz
		if(widok.getListaPacjentowWidok().getmList().getSelectedRow()>=0){
			
			System.out.println(widok.getListaPacjentowWidok().getmList().getValueAt(widok.getListaPacjentowWidok().getmList().getSelectedRow(), 2));
			widok.getDanePacjetaWidok().ustawDane(widok.wybranyPacjent());
			widok.getBadanie().ustawDane(widok.wybranyPacjent());
			//widok.czyJestesZdrowy();
			widok.activateRemoveButton(true);
			widok.getBadanie().setActive(true);
			widok.getDanePacjetaWidok().setActive(true);
			isRowSelected = true;
			//pierwotnyPacjent = widok.wybranyPacjent();
		}
		// gdy nie zostal wybrany zadan wiersz w tabeli
		else{
			widok.activateRemoveButton(false);
			widok.getBadanie().setActive(false);
			widok.getDanePacjetaWidok().setActive(false);
			isRowSelected = false;
			//pierwotnyPacjent = null;
		}
			
		
	}

}
