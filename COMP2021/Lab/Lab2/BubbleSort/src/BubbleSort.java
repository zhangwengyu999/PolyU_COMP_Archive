// OOP_LAB2
// Made by Mike_Zhang(21098431d)
import java.util.Random;

public class BubbleSort {
    public static void main(String[] args){
        int n = 10; // array size
        int r = 99; // number range
        int[] bbArray = new int[n]; // create an array size of n
        Random random = new Random();

        // creating an array
        System.out.println("The original array is:");
        for (int i = 0; i < n; i++){
            bbArray[i] = random.nextInt(r);
            if (i != n-1) {
                System.out.print(bbArray[i] + ",");
            }
            else {System.out.println(bbArray[i]);}
        }
        // sorting
        for (int k = 0; k < n; k++){
            for (int j = 0; j < n-k-1; j++){
                if (bbArray[j] > bbArray[j+1]){
                    int temp = bbArray[j];
                    bbArray[j] = bbArray[j+1];
                    bbArray[j+1] = temp;
                }
            }
        }
        // output
        System.out.println("\nThe sorted array is:");
        for (int p = 0; p < n; p++){
            if (p != n-1) {
                System.out.print(bbArray[p] + ",");
            }
            else {System.out.println(bbArray[p]);}
        }
    }
}
// OOP_LAB2
// Made by Mike_Zhang(21098431d)