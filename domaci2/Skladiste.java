package domaci2;

public class Skladiste {
    
	   private static int id=0;
	   private int Skladiste=++id;
	   private int[] niz;
	   private int ulaz;
	   private int izlaz;
	   private int stanje;
	   private int kapacitet;
	   
	   Skladiste(int kapacitet){
	       this.kapacitet = kapacitet;
	       niz = new int[kapacitet];
	   }

	    public static int getId() {
	        return id;
	    }

	    public int getStanje() {
	        return stanje;
	    }
	   
	   public synchronized void stavi(int element) throws InterruptedException{
	       while(stanje==kapacitet) wait();
	       niz[ulaz++] = element;
	       stanje++;
	       if(ulaz == kapacitet)ulaz=0;
	       notifyAll();
	   }
	   
	   public synchronized int uzmi() throws InterruptedException{
	       while(stanje==0) wait();
	       int element = niz[izlaz];
	       niz[izlaz++] = 0;
	       stanje --;
	       if(izlaz==kapacitet)izlaz=0;
	       notifyAll();
	       return element;
	   }
	}
