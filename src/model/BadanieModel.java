package model;

import java.time.LocalDate;
import java.util.Date;

import view.BadanieView;
/**
 * klasa zawierajaca model badania
 */
public class BadanieModel {
	private int liczbaLeukocytow;
	private int liczbaEozynofili;
	private LocalDate data;
	private boolean isIgE;
	/**
	 * konstruktor klasy
	 * @param liczbaLeukocytow
	 * @param liczbaEozynofili
	 * @param data
	 * @param isIgE
	 */
	public BadanieModel(int liczbaLeukocytow, int liczbaEozynofili, LocalDate data, boolean isIgE) {
		super();
		this.liczbaLeukocytow = liczbaLeukocytow;
		this.liczbaEozynofili = liczbaEozynofili;
		this.data = data;
		this.isIgE = isIgE;
	}
	
	
	
	
	public int getLiczbaLeukocytow() {
		return liczbaLeukocytow;
	}
	public void setLiczbaLeukocytow(int liczbaLeukocytow) {
		this.liczbaLeukocytow = liczbaLeukocytow;
	}
	public int getLiczbaEozynofili() {
		return liczbaEozynofili;
	}
	public void setLiczbaEozynofili(int liczbaEozynofili) {
		this.liczbaEozynofili = liczbaEozynofili;
	}
	public boolean isIgE() {
		return isIgE;
	}
	public void setIgE(boolean isIgE) {
		this.isIgE = isIgE;
	}




	public LocalDate getData() {
		return data;
	}




	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
}
