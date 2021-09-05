import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Ortho {

  public static void main(String[] args) throws IOException {

    List<String> dicionario = Files.readAllLines(Paths.get(args[0]));
    String[] words = new String[dicionario.size()];
    dicionario.toArray(words);

    String texto = StdIn.readAll();
    String[] text = GetWords.words(texto);

    for(String palavra : text){
      String p2 = palavra.toLowerCase();
      String p3 = palavra.substring(0, 1) + p2.substring(1);
      if(!Ortho.binarySearch(palavra, words) && !Ortho.binarySearch(p2, words) && !Ortho.binarySearch(p3, words)) StdOut.println(palavra);
    }

  }

  public static boolean binarySearch(String palavra, String[] words){
    
    int inicio = 0;
    int fim = words.length - 1;

    while(inicio <= fim){
      int meio = (inicio + fim) / 2;
      if(palavra.compareTo(words[meio]) == 0) return true;
      else if(palavra.compareTo(words[meio]) > 0) inicio = meio + 1;
      else fim = meio - 1;
    }

    return false;
  }
  
}
