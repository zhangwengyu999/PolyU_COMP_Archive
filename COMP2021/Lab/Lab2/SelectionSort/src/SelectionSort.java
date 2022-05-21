// OOP_LAB2
// Made by Mike_Zhang(21098431d)
import java.util.Random;

public class SelectionSort {
    public static void main(String[] args){
        int n = 10; // array size
        int r = 99; // number range
        int[] sArray = new int[n]; // create an array size of n
        Random random = new Random();

        // creating an array
        System.out.println("The original array is:");
        for (int i = 0; i < n; i++){
            sArray[i] = random.nextInt(r);
            if (i != n-1) {
                System.out.print(sArray[i] + ",");
            }
            else {System.out.println(sArray[i]);}
        }

        for (int i = 0; i < n; i++){
            int minNumIndex = i;
            for (int j = i; j < n; j ++){
                if (sArray[j] < sArray[minNumIndex]){
                    minNumIndex = j;
                }
            }
            if (sArray[minNumIndex] != sArray[i]){
                int tamp = sArray[minNumIndex];
                sArray[minNumIndex] = sArray[i];
                sArray[i] = tamp;

            }
        }
        // output
        System.out.println("\nThe sorted array is:");
        for (int p = 0; p < n; p++){
            if (p != n-1) {
                System.out.print(sArray[p] + ",");
            }
            else {System.out.println(sArray[p]);}
        }
    }
}// OOP_LAB2
// Made by Mike_Zhang(21098431d)