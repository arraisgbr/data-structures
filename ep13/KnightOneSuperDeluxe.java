import edu.princeton.cs.algs4.StdOut;

public class KnightOneSuperDeluxe {

    static int[][] board;
    static int[][] visited;
    static int N;

    static int[] iMove = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] jMove = {1, 2, 2, 1, -1, -2, -2, -1};

    private static boolean isOk(int i, int j){
        if(i < 0 || j < 0 || i >= N || j >= N || visited[i][j] != 0) return false;
        return true;
    }

    private static void verifyPosition(int i, int j){
        for(int k = 0 ; k < 8 ; k++){
            if(isOk(i + iMove[k], j + jMove[k])) board[i][j]++;
        }
    }

    private static boolean solve(int index, int current, long start){
        
        long finish = System.nanoTime();

        if(finish - start >= 1000000000) return false;
        
        // recovering index
        int j = index % N;
        int i = (index - j) / N;
        visited[i][j] = current;

        // verifying if the knight's tour is over
        if(current == N*N){
            StdOut.printf("There is a Knight's tour on an %dx%d board\n", N, N);
            return true;
        }

        // Nao preciso criar isso tudo, só uma fila de 8 posicoes, já que só vai mudar o iMove e o jMove.
        IndexMinPQ<Integer> queue = new IndexMinPQ<Integer>(8);

        // verifying the neighborhood
        for(int k = 0 ; k < 8 ; k++){
            int iAux = i + iMove[k];
            int jAux = j + jMove[k];
            if(isOk(iAux, jAux)){
                board[iAux][jAux]--;
                queue.insert(k, board[iAux][jAux]);
            }
        }

        while(!queue.isEmpty()){
            int indexAux = queue.delMin();
            int iAux = i + iMove[indexAux];
            int jAux = j + jMove[indexAux];
            if(solve(iAux*N+jAux, current + 1, start)) return true;
        }

        visited[i][j] = 0;
        for(int k = 0 ; k < 8 ; k++){
            int iAux = i + iMove[k];
            int jAux = j + jMove[k];
            if(isOk(iAux, jAux)) board[iAux][jAux]++;
        }

        visited[i][j] = 0;
        return false;
    }

    public static void initialize(){
        boolean found = false;
        board = new int[N][N]; // creating the board
        visited = new int[N][N]; // creating the board of visited cells
        IndexMinPQ<Integer> queue = new IndexMinPQ<Integer>(N*N);
        
        // seting the number of valid cells the knight can go from the current cell
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++) verifyPosition(i, j);
        }

        StdOut.printf("%d: ", N);
        for(int j = 0 ; j < N/2 ; j++){
            for(int i = 0 ; i < j ; i++){
                queue.insert(i*N+j, board[i][j]);
            } 
        }

        while(!queue.isEmpty() && !found){
            int index = queue.delMin();
            int j = index % N;
            int i = (index - j) / N;
            StdOut.printf("(%d, %d) : ", i, j);
            long start = System.nanoTime();
            found = solve(index, 1, start);
        }

        // if no tour was found
        if(!found) StdOut.printf("Found no tour\n");
    } 

    public static void main(String[] args){
        int left = Integer.parseInt(args[0]);
        int right = Integer.parseInt(args[1]);
        for(int i = left ; i <= right ; i++){
            N = i;
            initialize();
        }
    }
}
