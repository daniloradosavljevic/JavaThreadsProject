package domaci1;

public class main {
    public static void main(String[] args) {
    
    	String nazivRampe = "Test rampa";
    	int brojStanica = 3;
    	double tsr = 2.0;
    	
    	int[] cene1 = {100, 200, 300};
    	Cenovnik cenovnik1 = new Cenovnik(cene1);
    	NaplatnaStanica teststanica = new NaplatnaStanica(cenovnik1);
    	NaplatnaRampa rampa = new NaplatnaRampa(nazivRampe,brojStanica,teststanica,tsr);
    	
    	int[] cene2 = {120, 220, 320};
    	Cenovnik cenovnik2 = new Cenovnik(cene2);
    	
    	System.out.println("Otvaranje rampe");
    	rampa.otvori(cenovnik2);
    	 
    	try {
             Thread.sleep(10000);
         } catch (InterruptedException e) {
             System.err.println("Glavni thread prekinut: " + e.getMessage());
         }
    	System.out.println("Stanje rampe nakon 10 sekundi rada: ");
    	System.out.println(rampa);
    	
    	System.out.println("Zatvaranje rampe");
    	
    	try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.err.println("Glavni thread prekinut: " + e.getMessage());
        }
    	
    	 System.out.println("Stanje rampe nakon zatvaranja: ");
         System.out.println(rampa);

         System.out.println("Unistavanje rampe...");
         rampa.unisti();
         System.out.println("Gotovo izvrsavanje programa.");
    	
    	
    	
    	
    	
    }
}
