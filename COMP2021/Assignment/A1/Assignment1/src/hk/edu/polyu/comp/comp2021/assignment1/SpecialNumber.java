// OOP_A1-2
// Made by Mike_Zhang(21098431d)
package hk.edu.polyu.comp.comp2021.assignment1;

public class SpecialNumber {

	public static boolean isSpecial(int num) {
		// Task 3: Return true if and only if 'num' is special
		boolean check = true; // store the result
		int count = 0; // count the number of different prime factors
		int i = 2; // start from the least prime number
		final int NUM = num;
		while (i < NUM){
			if (count > 3){ // if already counted 3 prime factors, break the loop for saving run time
				check = false; // mark it is NOT a Special number
				break;
			}
			if (isPrime(i)){ // check prime number
				if (num % i == 0){ // check the factor
						num = num/i; // if it is its factor, divided by i
						// System.out.println(i); // Test, print out the factor
						// NO i++, loop again with the same i, check whether 'i' can still be the factor of the new num
				}
				else{ // it is not the factor
					i++; // move to next i
					count++; // current "i" is NOT the factor indicate the former 'i' is the factor, then count 1, also avoid the repeat counting
					if (num == 1){break;} // as soon as the num becomes 1 (means no more factor), break the loop
				}
			}
			else {i++;} // if not a prime number, move to the next number
		}
		if (count != 3){ // if not exactly 3 different prime factors, mark 'false'
			check = false;
		}
		return check;
	}
	// checking the number is prime or not
	public static boolean isPrime(int num){
		boolean check = false;
		if (num == 2){
			check = true;
		}
		else{
			for (int i=2; i<num; i++){
				if(num % i == 0){
					check = false;
					break;
				}
				else {check = true;}
			}
		}
		return check;
	}
}
// OOP_A1-2
// Made by Mike_Zhang(21098431d)
