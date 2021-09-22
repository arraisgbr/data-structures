public class Join{

    public static void main(String args[]){
 
        String s = StdIn.readString();
        String t = StdIn.readString();
        int sizeS = s.length();
        int sizeT = t.length();
        
        // Creating the 2d array sizeS x sizeT
        int memo[][] = new int[sizeS+1][sizeT+1];
        
        // LCS bottom-up of strings s and t 
        for(int i = sizeS - 1 ; i >= 0 ; i--){
            for(int j = sizeT - 1 ; j >= 0 ; j--){
                if(s.charAt(i) == t.charAt(j)) memo[i][j] = 1 + memo[i+1][j+1];
                else memo[i][j] = Math.max(memo[i+1][j], memo[i][j+1]);
            }
        }

        int sizeLCS = memo[0][0];

        // Creating array of the same size as lcs size
        char[] lcs = new char[sizeLCS + 1];
        lcs[sizeLCS] = '\0';

        // Recovering the lcs
        int indexS = 0 ; int indexT = 0 ; int indexLCS = 0;
        while(indexS < sizeS && indexT < sizeT){
            if(s.charAt(indexS) == t.charAt(indexT)){
                lcs[indexLCS] = s.charAt(indexS);
                indexS++; indexT++; indexLCS++;
            }
            else if(memo[indexS+1][indexT] > memo[indexS][indexT+1]) indexS++;
            else indexT++;
        }

        // Creating the minimal join of s and t
        String minJoin = new String();
        indexS = 0 ; indexT = 0;
        for(int i = 0 ; i < sizeLCS ; i++){
            while(s.charAt(indexS) != lcs[i] && indexS < sizeS){
                minJoin += s.charAt(indexS);
                indexS++;
            }
            while(t.charAt(indexT) != lcs[i] && indexT < sizeT){
                minJoin += t.charAt(indexT);
                indexT++;
            }
            minJoin += lcs[i];
            indexS++;
            indexT++;
        }
        
        // Printing the minimal join
        StdOut.println(minJoin);
    }
}