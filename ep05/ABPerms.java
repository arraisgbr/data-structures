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

    // Longest Increasing Subsequence and Longest Decreasing Subsequence
    public static void LISandLDS(char[] answer, int size, int[] ls){
        
        // Creating an array of subproblems
        int[] lis = new int[size];
        int[] lds = new int[size];
        for(int i = 0 ; i < size ; i++){
            lis[i] = 1;
            lds[i] = 1;
        }

        // LIS and LDS
        int j = 1;
        while(j < size){
            for(int i = 0 ; i < j ; i++){
                if(answer[j] > answer[i]) lis[j] = Math.max(lis[j], lis[i] + 1);
                if(answer[j] < answer[i]) lds[j] = Math.max(lds[j], lds[i] + 1);
            }
            j++;
        }

        int maxLIS = 0;
        int maxLDS = 0;
        for(int i = 0 ; i < size ; i++){
            maxLIS = Math.max(maxLIS, lis[i]);
            maxLDS = Math.max(maxLDS, lds[i]);
        }

        ls[0] = maxLIS;
        ls[1] = maxLDS;
    }

    // Find the permutations
    public static void enumerate(char[] answer, int size){

        int[] ls = {0, 0};

        LISandLDS(answer, size, ls);

        // If LIS > a or LDS > b we can't use this permutation
        if(ls[0] > ABPerms.a || ls[1] > ABPerms.b) return;

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

        // testing
        //char[] ABPerms = {'b','a','d','c'};
        //StdOut.println(LIS(ABPerms));
        //StdOut.println(LDS(ABPerms));
    
        // Final print
        if(verbose && args[3].compareTo("-v") < 0 || !verbose) StdOut.println(total);
    }

}