package model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import controller.Commons;
import view.BadanieView;

/**
 * Model danych aplikacji
 */
public class BazaDanych extends AbstractTableModel
{
	public static final int IMIE_NAZWISKO_INDEX = 0;
    public static final int PESEL_INDEX = 2;
    public static final int UBEZPIECZENIE_INDEX = 3;
    public static final int PLEC_INDEX = 1;
    public static final int BADANIE_INDEX = 4;
	
	protected String[] columnNames;
    protected List<Pacjent> listaPacjentow;
	
	public BazaDanych()
	{
		this.columnNames = columnNames;
        listaPacjentow = new ArrayList<Pacjent>();
	}
	
	/**
	 * nazwanie poszczegolnych kolumn w tabeli
	 */
	public String getColumnName(int column) {
        if(column == 0)
        	return Commons.IMIE_NAZWISKO;
        else if(column == 1)
        	return Commons.PESEL;
        else if(column == 2)
        	return Commons.PLEC;
        else if(column == 3)
        	return Commons.UBEZPIECZENIE;
        else if(column == 4)
        	return Commons.BADANIE;
		return null;
    }
	/**
	 * funkcja pozwalajaca edytowac istniejacego pacjenta 
	 * @param nowyPacjent
	 * @param row ktory wiersz 
	 * @return
	 */
	public boolean edytujPacjenta(Pacjent nowyPacjent, int row){
		if(nowyPacjent.getPesel().equals(listaPacjentow.get(row).getPesel())){
			updatePacjent(nowyPacjent, row);
			
			return true;
		}
		else{
			if(czyPeselSieNiePowtarza(nowyPacjent.getPesel())){
				updatePacjent(nowyPacjent, row);
				return true;
			}
			else{
				return false;
			}
		}	
	}
	/**
	 * funkcja aktualizujaca dane pacjenta po edycji
	 */
	public void updatePacjent(Pacjent pacjent, int row){
		listaPacjentow.get(row).setImie(pacjent.getImie());
		listaPacjentow.get(row).setNazwisko(pacjent.getNazwisko());
		listaPacjentow.get(row).setPesel(pacjent.getPesel());
		listaPacjentow.get(row).setPlec(pacjent.getPlec());
		listaPacjentow.get(row).setUbezpiecznie(pacjent.getUbezpiecznie());
		if(listaPacjentow.get(row).getBadanie() != null){
			listaPacjentow.get(row).getBadanie().setLiczbaEozynofili(pacjent.getBadanie().getLiczbaEozynofili());
			listaPacjentow.get(row).getBadanie().setLiczbaLeukocytow(pacjent.getBadanie().getLiczbaLeukocytow());
		}
	}
	/**
	 * funkcja sprawdzajaca czy osoba z podanym pesel nie istnieje juz na liscie
	 */
	public boolean czyPeselSieNiePowtarza(String pesel ){
		for(Pacjent pacjent : listaPacjentow){
			if(pesel.equals(pacjent.getPesel()))
					return false;
		}
		return true;
	}
	/**
	 * funkcja ustawajaca typu danych dla danej kolumny
	 */
    public Class getColumnClass(int column) {
        switch (column) {
            case 4:	
               return Boolean.class;
            default:
               return String.class;
        }
    }
    /**
     * funkcja pobierajaca dane z listy i przypisujaca je pacjentowi
     */
    public Object getValueAt(int row, int column) {
    	if(row>=listaPacjentow.size())
    		throw new IllegalArgumentException("Nieprawid³owy argument");
    	
        Pacjent record = (Pacjent)listaPacjentow.get(row);
        switch (column) {
            case 0:
               return String.join(" " ,record.getImie(), record.getNazwisko());
            case 1:
               return record.getPesel();
            case 2:
               return record.getPlec();
            case 3:
            	return record.getUbezpiecznie();
            case 4:
            	return record.getBadanie()!=null;
            default:
               throw new IllegalArgumentException("Niepawdi³owa kolumna");
        }
    }
    /**
     * usuwanie pacjenta z listy
     */
    public void removePacjent(int index){
    	if(listaPacjentow.size()>index)
    		listaPacjentow.remove(index);	
    }
    /**
     * pobieranie pacjenta z listy
     */
    public Pacjent getPacjent(int row){
    	if(row >= listaPacjentow.size())
    		return null;
    	return listaPacjentow.get(row);	
    }
    /**
     * liczenie rozmiaru listy
     */
    public int getRowCount() {
        return listaPacjentow.size();
    }
    /**
     * stala liczba kolumn 5
     */
    public int getColumnCount() {
        return 5;
    }
    /**
     * dodanie pacjentowi badania
     */
    public void dodajBadanie(Pacjent pacjent , BadanieModel badanie){
    	pacjent.setBadanie(badanie);
    }
  
	public List<Pacjent> getListaPacjentow() {
		return listaPacjentow;
	}

	
	public void setListaPacjentow(List<Pacjent> listaPacjentow) {
		this.listaPacjentow = listaPacjentow;
		fireTableDataChanged();
	}
    
	
}