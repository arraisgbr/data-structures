import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TokenFinder{
    
    StringBuilder[] tokens;
    int quantidadeTokens = 1;
    int tokenAtual = 0;

    TokenFinder(String line){
        boolean aspas = false;
        // encontrando a quantidade de tokens
        for(int i = 0 ; i < line.length() ; i++){
            char c = line.charAt(i);
            if(c == '"') aspas = !aspas;
            else if(c == ',' && !aspas) this.quantidadeTokens++;
        }

        // criando array de tokens
        this.tokens = new StringBuilder[this.quantidadeTokens];

        // inicializando cada token
        for(int i = 0 ; i < this.quantidadeTokens ; i++) this.tokens[i] = new StringBuilder("");
        
        // separando os tokens
        this.separaTokens(line);

        // eliminando aspas
        this.eliminaAspas();
    }

    void separaTokens(String line){
        // separando os tokens
        boolean aspas = false;
        int indice  = 0;
        for(int i = 0 ; i < line.length() ; i++){
            char c = line.charAt(i);
            if(c == ','){
                if(aspas) this.tokens[indice].append(c);
                else indice++;
            }
            else{
                if(c == '"') aspas = !aspas;
                this.tokens[indice].append(c);
            }
        }
    }

    void eliminaAspas(){
        for(int i = 0 ; i < this.quantidadeTokens ; i++){
            if(this.tokens[i].charAt(0) == '"') this.tokens[i].deleteCharAt(0);
            if(this.tokens[i].charAt(this.tokens[i].length() - 1) == '"') this.tokens[i].deleteCharAt(this.tokens[i].length() - 1);
            int j = 1;
            while(j < this.tokens[i].length() - 1){
                if(this.tokens[i].charAt(j) == '"'){
                    this.tokens[i].deleteCharAt(j);
                    j++;
                }
                else j++;
            }
        }
    }

    String nextToken(){
        if(tokenAtual >= quantidadeTokens) return null;
        String retorno = this.tokens[this.tokenAtual++].toString();
        return retorno;
    }

}