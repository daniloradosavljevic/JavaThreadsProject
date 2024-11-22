package domaci1;

public class NaplatnaStanica {
	
	private static int idBrojac = 0;
	private int id;
	private Cenovnik cenovnik;
	private int naplacenaPutarina = 0;
	private int naplacenoOdPostavljanjaCenovnika;
	
	public NaplatnaStanica(Cenovnik cenovnik) {
        this.id = idBrojac++;
        this.cenovnik = cenovnik;
    }
	
	public Cenovnik getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(Cenovnik cenovnik) {
		this.cenovnik = cenovnik;
		naplacenoOdPostavljanjaCenovnika = 0;
	}

	public NaplatnaStanica kopirajStanicu() {
		return new NaplatnaStanica(this.cenovnik);
	}
	
	public void naplatiPutarinu(Vozila vozilo) throws RuntimeException {
		if(cenovnik == null) {
			throw new NullPointerException();
		}
		this.naplacenaPutarina += this.cenovnik.getPutarina(vozilo.getKategorija());
		this.naplacenoOdPostavljanjaCenovnika += this.cenovnik.getPutarina(vozilo.getKategorija());
	}

	public int getIznosOdPostavljanja() {
		return this.naplacenoOdPostavljanjaCenovnika;
	}
	
	@Override
	public String toString() {
		return id + "(" + naplacenaPutarina + ")";
	}
	
}
