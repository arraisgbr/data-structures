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

    // Longest Increasing Subsequence
    public static int LIS(char[] answer){
        // Creating an array of subproblems
        int size = answer.length;
        int[] lis = new int[size];
        for(int i = 0 ; i < size ; i++) lis[i] = 1;

        // LIS
        int j = 1;
        while(j < size){
            for(int i = 0 ; i < j ; i++){
                if(answer[j] > answer[i]) lis[j] = Math.max(lis[j], lis[i] + 1);
            }
            j++;
        }

        int max = 0;
        for(int i = 0 ; i < size ; i++) max = Math.max(max, lis[i]);

        return max;
    }

    // Longest Decreasing Subsequence
    public static int LDS(char[] answer){
        // Creating an array of subproblems
        int size = answer.length;
        int[] lds = new int[size];
        for(int i = 0 ; i < size ; i++) lds[i] = 1;

        // LDS
        int j = 1;
        while(j < size){
            for(int i = 0 ; i < j ; i++){
                if(answer[j] < answer[i]) lds[j] = Math.max(lds[j], lds[i] + 1);
            }
            j++;
        }

        int max = 0;
        for(int i = 0 ; i < size ; i++) max = Math.max(max, lds[i]);

        return max;
    }
    
    // Find the permutations
    public static void enumerate(char[] answer, int size){

        // If the permutation has the correct size show it
        if(size == ABPerms.N){
            total++;
            if(verbose) show(answer);
            return;
        }

        for(int i = size ; i < ABPerms.N ; i++){
            swap(answer, size, i);
            enumerate(answer, size + 1);
            swap(answer, size, i);
        }
    }

    // Swap two elements of an array
    private static void swap(char[] answer, int i, int j){
        char c = answer[i]; 
        answer[i] = answer[j];  
        answer[j] = c;   
    }

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
        enumerate(answer, 0);

        // Final print
        if(verbose && args[3].compareTo("-v") < 0 || !verbose) StdOut.println(total);
    }

}