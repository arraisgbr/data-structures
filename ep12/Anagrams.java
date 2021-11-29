import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Anagrams{

    public static void mergeWord(SignedWord[] sw, SignedWord[] aux, int l, int m, int r){
        int i = l;
        int j = m + 1;
        for(int k = l ; k <= r ; k++){
            if(i > m) aux[k] = sw[j++];
            else if(j > r) aux[k] = sw[i++];
            else if(sw[j].word().compareTo(sw[i].word()) < 0) aux[k] = sw[j++];
            else aux[k] = sw[i++];
        }
    }

    public static void mergeSignature(SignedWord[] sw, SignedWord[] aux, int l, int m, int r){
        int i = l;
        int j = m + 1;
        for(int k = l ; k <= r ; k++){
            if(i > m) aux[k] = sw[j++];
            else if(j > r) aux[k] = sw[i++];
            else if(sw[j].compareTo(sw[i]) < 0) aux[k] = sw[j++];
            else aux[k] = sw[i++];
        }
    }

    public static void sort(SignedWord[] sw, SignedWord[] aux, int l, int r, String modo){
        if(r <= l) return;
        int m = l + (r - l) / 2;
        sort(aux, sw, l, m, modo);
        sort(aux, sw, m+1, r, modo);
        if(modo.equals("Word")) mergeWord(sw, aux, l, m, r);
        else mergeSignature(sw, aux, l, m, r);
    }

    public static void mergeSort(SignedWord[] sw, String modo){
        SignedWord[] aux = new SignedWord[sw.length];
        for(int i = 0 ; i < sw.length ; i++) aux[i] = sw[i];
        sort(aux, sw, 0, sw.length-1, modo);
    }

    public static void main(String[] args){
        
        // verifica entrada da linha de comando
        int k;
        if(args.length > 0) k = Integer.parseInt(args[0]); 
        else k = 2;

        String[] words = StdIn.readAllStrings(); // recebendo palavras
        SignedWord[] sw = new SignedWord[words.length]; // cria array de SignedWord
        
        // preenche o array de signedword
        for(int i = 0 ; i < words.length ; i++) sw[i] = new SignedWord(words[i]);
        
        mergeSort(sw, "Word"); // ordena sw através de SignedWord.word
        mergeSort(sw, "Signature"); // ordena sw através de SignedWord.signature de forma estável
        
        // percorre array de signedword criando as filas e a fila de filas
        int l = 0;
        int r = 1;
        Queue<Queue<SignedWord>> filaAnagramas = new Queue<Queue<SignedWord>>();
        while(l < sw.length){
            Queue<SignedWord> anagramas = new Queue<SignedWord>();
            anagramas.enqueue(sw[l]);
            while(r < sw.length && sw[r].signature().equals(sw[l].signature())){
                anagramas.enqueue(sw[r]);
                r++;
            }
            l = r++;
            filaAnagramas.enqueue(anagramas);
        }

        // cria array de anagramas
        Queue<SignedWord>[] arrayAnagramas = (Queue<SignedWord>[]) new Queue[filaAnagramas.size()];
        int indice = 0;
        while(!filaAnagramas.isEmpty())
            arrayAnagramas[indice++] = filaAnagramas.dequeue();
        
        // imprimir saída
        for(int i = 0 ; i < arrayAnagramas.length ; i++){
            if(arrayAnagramas[i].size() >= k){
                StdOut.printf("+ ");
                for(SignedWord item : arrayAnagramas[i]) StdOut.printf("%s ", item.word());
                StdOut.println();
            }
        }
    }
}