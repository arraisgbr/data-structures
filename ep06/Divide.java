public class Divide{

    public static int sum = 0;
    public static int max;
    public static int min;
    public static int[] numbers;
    public static int[] aNumbers;
    public static int[] bNumbers;
    public static int[] cNumbers;
    public static int length;
    public static boolean verbose;

    public static void showAnswer(boolean exists, int aIndex, int bIndex, int cIndex){
        int a, b, c;
        a = b = c = 0;
        if(exists) StdOut.println("There is a solution");
        else StdOut.println("There is no solution");
        if(verbose){
            StdOut.printf("0: ");
            for(int i = 0 ; i < aIndex ; i++){
                if(exists){
                    StdOut.printf("%d ", aNumbers[i]);
                    a += aNumbers[i];
                }
                else{
                    StdOut.printf("%d ", numbers[i]);
                    a += numbers[i];
                }
            }
            StdOut.printf("(sum: %d)\n", a);
        
            StdOut.printf("1: ");
            for(int i = 0 ; i < bIndex ; i++){
                StdOut.printf("%d ", bNumbers[i]);
                b += bNumbers[i];
            }
            StdOut.printf("(sum: %d)\n", b);
            
            StdOut.printf("2: ");
            for(int i = 0 ; i < cIndex ; i++){
                StdOut.printf("%d ", cNumbers[i]);
                c += cNumbers[i];
            }
            StdOut.printf("(sum: %d)\n", c);
            
            if(exists) StdOut.printf("Optimal value: %d (sum: %d / want: %d / sum mod 3 = %d)\n", min, sum, min, sum%3);
            else StdOut.printf("Best I found (not necessarily optimal): %d (sum: %d / want: %d / sum mod 3 = %d)\n", 0, sum, min, sum%3);
        }
    }

    public static boolean divide(int index, int a, int b, int c, int aIndex, int bIndex, int cIndex){
        
        if(index == length){
            if((a >= min) && (b >= min) && (c >= min)){
                showAnswer(true, aIndex, bIndex, cIndex);
                System.exit(0);
            }
            return false;
        }
        
        if((a > max || b > max || c > max)) return false;

        int limit = Math.min(a + numbers[index], b + numbers[index]);
        limit = Math.min(limit, c + numbers[index]);
        if(limit > max) return false;

        aNumbers[aIndex] = numbers[index]; 
        divide(index+1, a+numbers[index], b, c, aIndex+1, bIndex, cIndex);
        aNumbers[aIndex] = 0;
        
        bNumbers[bIndex] = numbers[index];
        divide(index+1, a, b+numbers[index], c, aIndex, bIndex+1, cIndex);
        bNumbers[bIndex] = 0;
        
        cNumbers[cIndex] = numbers[index];
        divide(index+1, a, b, c+numbers[index], aIndex, bIndex, cIndex+1);
        cNumbers[cIndex] = 0;

        return false;

    }

    public static void divide(int[] numbers){    
        sum = sum(numbers);

        length = numbers.length;

        min = sum / 3;

        
        if(sum % 3 == 0) max = min;
        else max = min + 1;

        aNumbers = new int[length];
        bNumbers = new int[length];
        cNumbers = new int[length];

        if(!divide(0, 0, 0, 0, 0, 0, 0)){
            showAnswer(false, length, 0, 0);
            System.exit(0);
        }
    }

    public static int sum(int[] numbers){
        int sum = 0;
        for(int number : numbers) sum += number;
        return sum;
    }

    public static void main(String[] args){

        verbose = args.length > 0;

        numbers = StdIn.readAllInts();

        divide(numbers);

    }

}