package hk.edu.polyu.comp.comp2021.assignment2.complex; // OOP_A2-1 Made by ZHANG Wengyu(21098431d)

public class Rational {
    // Task 1: add the missing fields

    private int numerator;
    private int denominator;

    public Rational(int numerator, int denominator) {
        // Task 2: complete the constructor
            this.numerator = numerator;
            this.denominator = denominator;
    }

    public Rational add(Rational other) {
        // Task 2: complete the method
        int outNumerator, outDenominartor;
        outNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        outDenominartor = this.denominator * other.denominator;
        return new Rational(outNumerator, outDenominartor);
    }

    public Rational subtract(Rational other) {
        // Task 2: complete the method
        // invoke add() method above because a/b - c/d = a/b + (-c/d) = a/b + ((0-c)/d)
        return this.add(new Rational((0 - other.numerator), other.denominator));
    }

    public Rational multiply(Rational other) {
        // Task 2: complete the method
            int outNumerator, outDenominartor;
            outNumerator = this.numerator * other.numerator;
            outDenominartor = this.denominator * other.denominator;
            return new Rational(outNumerator, outDenominartor);
    }

    public Rational divide(Rational other) {
        // Task 2: complete the method
        // invoke multiply() method above because (a/b)/(c/d) = a/b * 1/(c/d) = a/b * d/c
        return this.multiply(new Rational(other.denominator, other.numerator));
    }

    public String toString() {
        // Task 2: complete the method
        return numerator+"/"+denominator;
    }

    public void simplify() {
        // Task 2: complete the method
        if (numerator == 0){denominator = 1;} /** MARK DEDUCTION!! Missing deal with the 0 input, if the numerator is 0 then the whole rational number should be 0/1 */
        else {
            // Use Euclidean Algorithm to get the maximin common factor of numerator and denominator
            int maxComFact = Math.abs((Math.abs(numerator) < Math.abs(denominator))? numerator:denominator); // store the maximin common factor, first assign min(numerator, denominator) to it
            int dividend = Math.abs((Math.abs(numerator) > Math.abs(denominator))? numerator:denominator); // store the dividend, first assign max(numerator, denominator) to it
            int modVal = 1; // store the remainder
            do{
                modVal = dividend % maxComFact; // get the remainder (in the loop#1 )
                if (modVal == 0) break; // if the remainder = 0 means the maximin common factor is found then break
                else { // if not found yet
                    dividend = maxComFact; // let the current "maximin common factor" be the dividend in the next loop
                    maxComFact = modVal; // let the current remainder be the "maximin common factor" in the next loop
                }
            }
            while(modVal != 0); // loop until the remainder = 0
            // simplify
            numerator = numerator / maxComFact;
            denominator = denominator / maxComFact;
            // let the denominator always be positive
            if (denominator < 0){
                denominator = 0 - denominator;
                numerator = 0 - numerator;
            }
        }
    }

    // ==================================

} // OOP_A2-1 Made by ZHANG Wengyu(21098431d)
