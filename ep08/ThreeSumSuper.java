import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ThreeSumSuper {

    public static int count(int[] arr){    
        
        int ans = 0;
        int tam = arr.length;
        Arrays.sort(arr);

        for (int i = 0 ; i < tam ; i++) {
            int l = 0;
            int r = tam - 1;
            while(l < i && r > i){
                int sum = arr[l] + arr[r];
                if(sum + arr[i] == 0){
                    ans++;
                    if(arr[l+1] == arr[l]) l++;
                    else if(arr[r-1] == arr[r]) r--;
                    else{
                        l++; r--;
                    }
                }
                else if(sum + arr[i] > 0) r--;
                else l++; 
            }
        }
        
        return ans;

    }

    public static void printAll(int[] arr){
        
        Arrays.sort(arr);
        int tam = arr.length;

        for (int i = 0 ; i < tam ; i++) {
            int l = 0;
            int r = tam - 1;
            while(l < i && r > i){
                int sum = arr[l] + arr[r];
                if(sum + arr[i] == 0){
                    StdOut.printf("%d %d %d\n", arr[l], arr[r], arr[i]);
                    if(arr[l+1] == arr[l]) l++;
                    else if(arr[r-1] == arr[r]) r--;
                    else{
                        l++; r--;
                    }
                }
                else if(sum + arr[i] > 0) r--;
                else l++; 
            }
        }

    }

    public static void main(String[] args)  { 
        int[] a = StdIn.readAllInts();
	    int count = count(a);
        StdOut.println(count);
        if (count < 30) printAll(a);
    }
}