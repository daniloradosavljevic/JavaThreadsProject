package domaci2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
    
        Skladiste skladiste = new Skladiste(10);
        String fajl = "raspored.txt";
        
        for (int i = 0; i < 20; i++) {
            new Proizvodjac(skladiste, 500, 1000).start();
        }
        
        for(int i = 0; i < 30; i++) {
            new Potrosac(skladiste, 500, 1000).start();
        }
        
        upisiRaspored(fajl);
        List<List<Integer>> rasporedi = ucitajRaspored(fajl);
        
        for(int i = 0; i < rasporedi.size(); i++) {
            new Izvestac(skladiste, rasporedi.get(i)).start();
        }
    }
    
    private static List<List<Integer>> ucitajRaspored(String fajl){
        List<List<Integer>> rasporedi = new ArrayList<>();
        for (int i = 0; i < 3; i++) rasporedi.add(new ArrayList<>());
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(fajl));
            String linija;
            while ((linija = br.readLine()) != null) {
                String[] delovi = linija.split(" ");
                int vreme = Integer.parseInt(delovi[0]);
                int izvestacId = Integer.parseInt(delovi[1].replace("Izvestac", "")) - 1;
                rasporedi.get(izvestacId).add(vreme);
            }
            br.close();
        } catch (IOException e) {
            try {
                FileWriter writer = new FileWriter(fajl);
                for (int i = 0; i < 100; i++) {
                    int vreme = (int) (Math.random() * 100);
                    int izvestacId = (i % 3) + 1;
                    writer.write(vreme + " Izvestac" + izvestacId + "\n");
                }
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        return rasporedi;
    }
    private static void upisiRaspored(String fajl) {
        try {
            FileWriter writer = new FileWriter(fajl);
            
            for (int i = 1; i <= 100; i++) {
                int vreme = i; 
                int izvestacId = (i % 3) + 1; 
                writer.write(vreme + " Izvestac" + izvestacId + "\n");
            }            
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
