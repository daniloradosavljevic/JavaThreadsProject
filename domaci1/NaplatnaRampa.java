package domaci1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NaplatnaRampa extends Thread {
	
	private String naziv;
	private List<NaplatnaStanica> stanice;
	private double tsr;
	private boolean otvorena = false;
	private int ukupnoNaplaceno; 
	private Random random;
	
	public NaplatnaRampa(String naziv,int brojStanica,NaplatnaStanica stanicaZaKopiranje, double tsr) {
		this.naziv = naziv;
		this.tsr = tsr;
		this.stanice = new ArrayList<>();
		this.random = new Random();
		
		for(int i = 0; i < brojStanica; i++ ) {
			this.stanice.add(stanicaZaKopiranje.kopirajStanicu());
		}
	}
	
	public synchronized void otvori(Cenovnik noviCenovnik) {
		if(!otvorena) {
			for(NaplatnaStanica stanica : stanice) {
				stanica.setCenovnik(noviCenovnik);}
			}
		
		ukupnoNaplaceno = 0;
		otvorena = true;
		start();
		}
	
	
	public synchronized void zatvori() {
		otvorena = false;
		interrupt();
	}
	
	public synchronized void unisti() {
		zatvori();
		stanice.clear();
	}
	
	
	public void simulirajDolaskeVozila() {
		
		NaplatnaStanica randomStanica = stanice.get(random.nextInt(stanice.size()));
		Cenovnik cenovnik = randomStanica.getCenovnik();
		
		int brojKategorija = cenovnik.getBrojKategorija();
		int kategorija = 1 + random.nextInt(brojKategorija);
		
		Vozila vozilo = new Vozila();
		vozilo.setKategorija(kategorija);
		
		try {
			randomStanica.naplatiPutarinu(vozilo);
            synchronized (this) {
                ukupnoNaplaceno += cenovnik.getPutarina(kategorija);
            }
			} catch (IllegalArgumentException e) {
				System.err.println("Greska pri naplati putarine: " + e.getMessage());
			}
	}
	
	@Override
    public void run() {
        try {
            while (!interrupted() && otvorena) {
                simulirajDolaskeVozila(); 
                double interval = tsr * (1 + (random.nextDouble() - 0.5) * 0.6); 
                sleep((long) (interval * 1000)); 
            }
        } catch (InterruptedException e) {
        }
    }
	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(naziv).append("(").append(ukupnoNaplaceno).append("):");
		for (NaplatnaStanica stanica : stanice) {
			sb.append(stanica).append(",");
		}
        if (sb.charAt(sb.length() - 1) == ',') {
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	
	
	
}
