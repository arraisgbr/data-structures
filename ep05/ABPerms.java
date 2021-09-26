public class ABPerms {

    public static int N;
    public static int a;
    public static int b;
    public static int total = 0;
    public static boolean verbose = false;

    // Show the answers
    public static void show(char[] answer){
        for(char c : answer) StdOut.print(c);
        StdOut.println();
    }

    // Find the increasing permutations
    public static void enumerateS(char[] answer, int size){
        // if the substring already has the maximun increasing sequence

        if(size == ABPerms.N){
            total++;
            if(verbose) show(answer);
            return;
        }

        for(int i = size ; i < ABPerms.N ; i++){
            swap(answer, size, i);
            enumerateS(answer, size + 1);
            swap(answer, size, i);
        }
    }

    private static void swap(char[] answer, int i, int j){
        char c = answer[i]; 
        answer[i] = answer[j];  
        answer[j] = c;   
    }

    // Find the decreasing permutations
    /*
    public static void enumerateF(char[] answer, int size, int limS, int limF){
        
        // if the substring already has the maximun decreasing sequence
        if(limF == ABPerms.b && size < ABPerms.N) return false;
        
        if(size == ABPerms.N){
            total++;
            if(verbose) show(answer);
            return true;
        }

        for(int i = ABPerms.N - 1 ; i > size ; i--){
            swap(answer, size, i);
            if(!enumerateF(answer, size + 1, limS, limF + 1)) return false;
            swap(answer, size, i);
        }
        return false;
    }
    */

    public static void main(String[] args) {
        
        // Geting the command line arguments
        ABPerms.N = Integer.parseInt(args[0]);
        ABPerms.a = Integer.parseInt(args[1]);
        ABPerms.b = Integer.parseInt(args[2]);

        // Checking the fourth argument
        if(args.length == 4) verbose = true;
        
        // Creating an array with the first N letters
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        char[] answer = new char[ABPerms.N];

        for(int i = 0 ; i < ABPerms.N ; i++) answer[i] = alpha.charAt(i);
        
        // Permutation function
        enumerateS(answer, 0);

        // Final print
        if(verbose && args[3].compareTo("-v") < 0 || !verbose) StdOut.println(total);
    }

}