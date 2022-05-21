// OOP_LAB2
// Made by Mike_Zhang(21098431d)
public class PrimeNum {
    public static void main(String[] args){
        int n = 40; // end number
        int[] intArray = new int[n-1]; // create an array size of n-1
        boolean[] marked = new boolean[n-1]; // create a boolean array to contain the marked number

        // creating an array
        for (int i = 0; i < n - 1; i++){
            intArray[i] = i + 2;
        }

        for (int j = 0; j < n - 1; j++){ // read the array from 2 to n
            if (marked[j] == false){ // go only it's not marked
                for (int k = j + intArray[j]; k < n-1; k = k + intArray[j]){ // loop for the multiples of the prime
                    marked[k] = true; // mark it as non-prime
                }
            }
        }

        // output
        System.out.println("The prime numbers between 2 and " + n +" are:");
        for (int p = 0; p < n-1; p++){
            if (marked[p] == false){
            System.out.println(intArray[p]);
            }
        }
    }
}
// OOP_LAB2
// Made by Mike_Zhang(21098431d)