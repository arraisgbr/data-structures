import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

public class Patience{

    // inner class with a number and a reference to the top Node of the left stack
    public static class Node{
        int number, index;
        Node left;
        
        Node(int number, int index, Node left){
            this.number = number;
            this.index = index;
            this.left = left;
        }
    }

    // find the stack where the number fits
    public static int findStack(Stack<Node>[] stacks, int number){

        int l = 0; int r = stacks.length;
        int ans = -1;
        while(l <= r){
            int mid = l + (r-l) / 2;
            if(stacks[mid].peek().number < number) l = mid + 1;
            else{
                ans = mid;
                r = mid - 1;
            }
        }
        return ans;
    }

    // return the value of the LIS
    public static int getAns(Stack<Node>[] stacks){

        int ans = 0;
        for(int i = 0 ; i < stacks.length ; i++){
            if(stacks[i].peek().number != Integer.MAX_VALUE) ans++;
        }

        return ans;
    }

    // print the LIS
    public static Stack<Integer>[] getStacks(Stack<Node>[] stacks, int realLength){
        Node last = stacks[realLength-1].peek();

        Stack<Integer>[] stacksAux = (Stack<Integer>[]) new Stack[2];
        stacksAux[0] = new Stack<Integer>();
        stacksAux[1] = new Stack<Integer>();    

        StdOut.printf("LIS:\n");
        while(last != null){
            stacksAux[0].push(last.index);
            stacksAux[1].push(last.number);
            last = last.left;
        }

        return stacksAux;
    }

    // just print the stacks
    public static void printStacks(Stack<Node>[] stacks, int realLength){
        Stack<Integer>[] stacksAux = getStacks(stacks, realLength);

        int count = 0;
        while(!stacksAux[0].isEmpty()) StdOut.printf("%d: %d / %d\n", count++, stacksAux[0].pop(), stacksAux[1].pop());
    }

    // print the LIS and every final stack
    public static void printStacksPlus(Stack<Node>[] stacks, int realLength){

        Stack<Integer>[] stacksAux = getStacks(stacks, realLength);

        for(int i = 0 ; i < realLength ; i++){ 
            
            StdOut.printf("%d: ", i);
            Stack<Integer> sortedStack = new Stack<Integer>();

            while(!stacks[i].isEmpty()){
                if(stacks[i].peek().number != Integer.MAX_VALUE) sortedStack.push(stacks[i].pop().number);
                else stacks[i].pop();
            }

            // avoiding loitering
            stacks[i] = null;

            while(!sortedStack.isEmpty()) StdOut.printf("%d ", sortedStack.pop());
            StdOut.println();
        
        }

        StdOut.println("LIS:");
        int count = 0;
        while(!stacksAux[0].isEmpty()) StdOut.printf("%d: %d / %d\n", count++, stacksAux[0].pop(), stacksAux[1].pop());
    }

    public static void main(String[] args){
        // read all entry integers
        int[] seq = StdIn.readAllInts();

        // create an array of stacks
        Stack<Node>[] stacks = (Stack<Node>[]) new Stack[seq.length];

        for(int i = 0 ; i < seq.length ; i++){
            stacks[i] = new Stack<Node>();
            stacks[i].push(new Node(Integer.MAX_VALUE, -1, null));
        }

        // iterate over all the integers
        for(int i = 0 ; i < seq.length ; i++){

            // find the leftmost stack where the actual number fits
            int stackNumber = findStack(stacks, seq[i]);
            
            // push the new node on the correct stack
            Node node;
            if(stackNumber > 0){
                node = new Node(seq[i], i, stacks[stackNumber-1].peek());
            }
            else node = new Node(seq[i], i, null);

            stacks[stackNumber].push(node);
        
        }

        // args treatment
        int ans = getAns(stacks);

        if(args.length != 0){
            if(args[0].equals("+")) printStacks(stacks, ans);
            else printStacksPlus(stacks, ans);
        }

        StdOut.printf("LIS: %d elements\n", ans); 
    }
}