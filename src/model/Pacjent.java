package model;
import model.BadanieModel;

/**
 * Klasa model Pacjenta
 *
 */
public class Pacjent {
	
		private String imie;
		private String pesel;
		private String Nazwisko;
        private String ubezpiecznie;
        private String plec;
        private  BadanieModel badanie = null;
     
        /**
         * konstruktor klasy 
         * @param imie
         * @param nazwisko
         * @param pesel
         * @param ubezpiecznie
         * @param plec
         */
		public Pacjent(String imie, String nazwisko, String pesel, String ubezpiecznie, String plec) {
			super();
			this.imie = imie;
			this.pesel = pesel;
			this.Nazwisko = nazwisko;
			this.ubezpiecznie = ubezpiecznie;
			this.plec = plec;
		}
		
		public BadanieModel getBadanie() {
			return badanie;
		}
		public void setBadanie(BadanieModel badanie) {
			this.badanie = badanie;
		}
		public String getImie() {
			return imie;
		}
		public void setImie(String imie) {
			this.imie = imie;
		}
		public String getPesel() {
			return pesel;
		}
		public void setPesel(String pesel) {
			this.pesel = pesel;
		}
		public String getNazwisko() {
			return Nazwisko;
		}
		public void setNazwisko(String nazwisko) {
			Nazwisko = nazwisko;
		}
		public String getUbezpiecznie() {
			return ubezpiecznie;
		}
		public void setUbezpiecznie(String ubezpiecznie) {
			this.ubezpiecznie = ubezpiecznie;
		}
		public String getPlec() {
			return plec;
		}
		public void setPlec(String plec) {
			this.plec = plec;
		}
	

	

}
