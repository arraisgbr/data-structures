import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.ST;

public class GeoLocator{

    ST<String, Location> st = new ST<String, Location>();

    GeoLocator(String filename){
        try {
            FileReader arq = new FileReader(filename);
            BufferedReader lerArq = new BufferedReader(arq);

            String line = lerArq.readLine();
            while(line != null){
                montaST(line);
                line = lerArq.readLine();
            }
        } catch(IOException e){
            System.err.printf("Erro na abertura do arquivo", e.getMessage());
        }
    }

    Location location(String q){
        String key = this.st.floor(q);
        return this.st.get(key);
    }

    void montaST(String line){
        TokenFinder tf = new TokenFinder(line);
        String key = tf.tokenAt(0);
        String regiao = tf.tokenAt(2);
        String pais = tf.tokenAt(3);
        String estado = tf.tokenAt(4);
        String cidade = tf.tokenAt(5);
        Double latitude = Double.parseDouble(tf.tokenAt(6));
        Double longitude = Double.parseDouble(tf.tokenAt(7));

        Location loc = new Location(regiao, pais, estado, cidade, latitude, longitude);
        this.st.put(key, loc);
    }

    public static void main(String[] args){
        GeoLocator gl = new GeoLocator(args[0]);
    }
}