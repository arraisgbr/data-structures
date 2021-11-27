import java.util.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Anagrams{

    public static void main(String[] args){
        
        String[] words = StdIn.readAllStrings(); // recebendo palavras
        SignedWord[] sw = new SignedWord[words.length]; // cria array de SignedWord
        
        // preenche o array de signedword
        for(int i = 0 ; i < words.length ; i++)
            sw[i] = new SignedWord(words[i]);
        
        mergeSort(sw, "Word"); // ordena sw através de SignedWord.word
        mergeSort(sw, "Signature"); // ordenada sw através de SignedWord.signature

        // percorre array de signedword criando as filas e a fila de filas
        int l = 0;
        int r = 1;
        Queue<Queue<SignedWord>> filaAnagramas;
        while(l < sw.length){
            Queue<SignedWord> anagramas;
            anagramas.add(sw[l]);
            while(r < sw.length && sw[r].signature().equals(sw[l].signature())){
                anagramas.add(sw[r]);
                r++;
            }
            l = r++;
            filaAnagramas.add(anagramas);
        }

        // cria array de anagramas
        Queue<SignedWord>[] arrayAnagramas = (Queue<SignedWord>[]) new Queue<?>[filaAnagramas.size()];
        int indice = 0;
        while(!filaAnagramas.isEmpty())
            arrayAnagramas[indice++] = filaAnagramas.peek();
        
        // ordena array de anagramas
        mergeSort(arrayAnagramas, "Word");

        // imprimir saída
        for(int i = 0 ; i < arrayAnagramas.length ; i++){
            while(!arrayAnagramas[i].isEmpty()) StdOut.printf("%s ", arrayAnagramas[i].peek());
            StdOut.println();
        }
    }
}