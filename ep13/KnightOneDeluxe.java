import edu.princeton.cs.algs4.StdOut;

public class KnightOneDeluxe {

    static boolean verbose = true;
    static int[][] board;
    static int[][] visited;
    static int N;

    static int[] iMove = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] jMove = {1, 2, 2, 1, -1, -2, -2, -1};

    private static void showBoard(){
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++) StdOut.printf("%d ", visited[i][j]);
            StdOut.println();
        }
    }
    
    private static boolean isOk(int i, int j){
        if(i < 0 || j < 0 || i >= N || j >= N || visited[i][j] != 0) return false;
        return true;
    }

    private static void verifyPosition(int i, int j){
        for(int k = 0 ; k < 8 ; k++){
            if(isOk(i + iMove[k], j + jMove[k])) board[i][j]++;
        }
    }

    private static void solve(int index, int current){
        // recovering index
        int j = index % N;
        int i = (index - j) / N;
        visited[i][j] = current;

        // verifying if the knight's tour is over
        if(current == N*N){
            if(verbose) showBoard();
            else StdOut.printf("There is a Knight's tour on an %dx%d board\n", N, N);
            System.exit(0);
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
            solve(iAux*N+jAux, current + 1);
        }

        visited[i][j] = 0;
        for(int k = 0 ; k < 8 ; k++){
            int iAux = i + iMove[k];
            int jAux = j + jMove[k];
            if(isOk(iAux, jAux)) board[iAux][jAux]++;
        }

        return;
    }

    public static void initialize(){
        board = new int[N][N]; // creating the board
        visited = new int[N][N]; // creating the board of visited cells
        IndexMinPQ<Integer> queue = new IndexMinPQ<Integer>(N*N);
        
        // seting the number of valid cells the knight can go from the current cell
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++) verifyPosition(i, j);
        }

        solve(0, 1);

        // if no tour was found
        StdOut.printf("Found no tour\n");
    } 

    public static void main(String[] args){
        if(args.length == 2) verbose = false;
        N = Integer.parseInt(args[0]);
        initialize();
    }
}
