package hk.edu.polyu.comp.comp2021.assignment3.baseN;
// OOP_A3-2 Made by ZHANG Wengyu(21098431d)
public class BaseNIntegerUnsigned {

    /**
     * Magnitude of the integer in String. 'magnitude' should always be valid w.r.t. 'base', and it should never
     * contain unnecessary leading zeros, i.e., DIGIT_ZEROs.
     */
    private final String magnitude;

    /** Base of the integer. Only valid base values are allowed. */
    private final int base;

    /**
     * Return the base of the integer.
     */
    public int getBase() {
        return base;
    }

    /**
     * Return the magnitude of the integer.
     */
    public String getMagnitude() {
        return magnitude;
    }


    /**
     * Instantiate an NBasedIntegerUnsigned. Throws IllegalArgumentException if 'base' or 'magnitude' is invalid.
     */
    public BaseNIntegerUnsigned(String magnitude, int base) {
        if(!isValidMagnitude(magnitude, base))
            throw new IllegalArgumentException();

        // Add missing code here.
        this.magnitude = withoutLeadingZeroes(magnitude); // eliminate the unnecessary leading zeros by invoking withoutLeadingZeroes()
        this.base = base; // set base
    }

    /** √
     * Can 'this' be represented as an int value?
     */
    public boolean canBeRepresentedInInteger() {
        // Add missing code here.
        // check whether it is greater than the maximun integer can be represented in this base
        if (this.compare(getMaximum(this.getBase())) <= 0){
            return true;
        }
        else {return false;} // if it is greater than the maximum integer, it CANNOT be represented in integer

    }

    /** √
     * Return the value of 'this' integer in decimal.
     * Throw IllegalStateException if 'this' is too large to be represented as an int value.
     */
    public int toInteger(){
        if(!canBeRepresentedInInteger())
            throw new IllegalStateException();

        // Add missing code here.
        int out = 0;
        for (int i=0; i < this.getNumberOfPositions(); i++){ // traverse from left to right, get the sum of (getValueFromDigitit * base^(length-i-1))
            out = out + getValueAtPosition(i) * (int) (Math.pow(getBase(),i));
        }
        return out;
    }

    /** √
     * Return the String representation of 'this' in the format "magnitude(base)".
     */
    public String toString(){
        // Add missing code here.
        return this.getMagnitude()+"("+this.getBase()+")"; // into the format "magnitude(base)"

    }

    /** √
     * Return true if 'this' and 'other' are both of type 'BaseNIntegerUnsigned' and they have equivalent 'base'
     * and 'magnitude'. Otherwise return false.
     */
    public boolean equals(Object other){
        // Add missing code here.
        if (other instanceof BaseNIntegerUnsigned && this.getBase() == ((BaseNIntegerUnsigned) other).getBase() && this.getMagnitude().equals(((BaseNIntegerUnsigned) other).getMagnitude())){
            return true;
        }
        else {return false;}

    }

    /**
     * Return an integer value to indicate the relation between 'this' and 'other'. The value should be 1) positive if
     * 'this' is greater than 'other', 2) zero if 'this' is equal to 'other', and 3) negative if 'this' is smaller
     * than 'other'. Throw IllegalArgumentException if 'other' is null or 'this' and 'other' have different bases.
     *
     * Note: Both 'this' and 'other' may not be representable as 'int' values,
     *       so neither of them should be converted to 'int' during the comparison.
     */
    public int compare(BaseNIntegerUnsigned other){
        if(other == null || getBase() != other.getBase())
            throw new IllegalArgumentException();

        // Add missing code here.
        if(this.getNumberOfPositions() > other.getNumberOfPositions()){return 1;} // if the length of 'this' is greater than length of 'other', means 'this' is greater than 'other'
        if(this.getNumberOfPositions() < other.getNumberOfPositions()){return -1;} // if the length of 'this' is smaller than length of 'other', means 'this' is smaller than 'other'
        for (int i = 0; i < this.getNumberOfPositions(); i++){ // when the length of 'this' and 'other' are same, compare each same position digit in them from left the right
            if (this.getMagnitude().charAt(i) > other.getMagnitude().charAt(i)){ // if the same position digit in 'this' is greater than the one in 'other', means 'this' is greater than 'other'
                return 1;
            }
            if (this.getMagnitude().charAt(i) < other.getMagnitude().charAt(i)){ // if the same position digit in 'this' is smaller than the one in 'other', means 'this' is smaller than 'other'
                return -1;
            }
        }
        return 0; // else they are the same

    }

    /**
     * Return the sum of 'this' and 'other'. The result integer should have the same base as 'this'.
     * Throw IllegalArgumentException if 'other' is null or 'this' and 'other' have different bases.
     *
     * Note: Both 'this' and 'other' may not be representable as 'int' values,
     *       so neither of them should be converted to 'int' during the calculation.
     */
    public BaseNIntegerUnsigned add(BaseNIntegerUnsigned other){
        if(other == null || getBase() != other.getBase())
            throw new IllegalArgumentException();

        // Add missing code here.

        // implement a fulladder
        StringBuilder outStr = new StringBuilder(); // store the output String
        String halfAdderOut; // store the halfadder result
        int carry = 0; // store the carry
        int p,q = 0; // store 2 added value

        // traverse from LSB to MSB until the get the MSB of longer one, fill the smaller one with 'A' untill two of them have same length
        for (int i = 0; i < (this.getNumberOfPositions() > other.getNumberOfPositions()? this.getNumberOfPositions():other.getNumberOfPositions()); i++){
            p = this.getValueAtPosition(i); // get the first added value
            q = other.getValueAtPosition(i); // get the second added value

            // use the halfAdder to ge the added result
            halfAdderOut = encode(p + q + carry,this.getBase());

            // separated the added result from halfAdder into output char and carry
            if (halfAdderOut.length()>1){
                carry = getValueFromDigit(halfAdderOut.charAt(0), other.getBase());
                outStr.insert(0,halfAdderOut.charAt(1));
            }
            else {
                carry = 0; // if added result from halfAdder only has one char, assign it to the output char, and no carry
                outStr.insert(0,halfAdderOut.charAt(0));
            }
        }
        // return the output, add the last carry before the current outStr when overflow occurs
        return new BaseNIntegerUnsigned(getDigitFromValue(carry,this.getBase()) + outStr.toString(),this.getBase());

    }

    /**
     * Return the result of subtracting 'other' from 'this'. The result integer should have the same base as 'this'.
     * Throw IllegalArgumentException if 1) 'other' is null, 2) 'this' and 'other' have different bases, or
     * 3) 'this' is smaller than 'other'.
     *
     * Note: Both 'this' and 'other' may not be representable as 'int' values,
     *       so neither of them should be converted to 'int' during the calculation.
     */
    public BaseNIntegerUnsigned subtract(BaseNIntegerUnsigned other) {
        if (other == null || getBase() != other.getBase() || compare(other) < 0)
            throw new IllegalArgumentException();

        // Add missing code here.

        // also implement a fulladder,
        // but some part is different from the add() method
        StringBuilder outStr = new StringBuilder();
        String halfAdderOut;
        int carry = 0;
        int p,q = 0;
        int encodeValue = 0;
        for (int i = 0; i < (this.getNumberOfPositions() > other.getNumberOfPositions()? this.getNumberOfPositions():other.getNumberOfPositions()); i++){
            p = this.getValueAtPosition(i); // get the first added value
            q = other.getValueAtPosition(i); // get the second added value

            encodeValue = p - q + carry; // do subtraction

            // if the subtracted value is negative, then borrow 1 from the next position and add it to the result, so set carry to -1
            if (encodeValue < 0){encodeValue = encodeValue + this.getBase(); carry = -1;}
            else {carry = 0;}

            halfAdderOut = encode(encodeValue,this.getBase());
            outStr.insert(0,halfAdderOut.charAt(0));
        }
        return new BaseNIntegerUnsigned(outStr.toString(),this.getBase());
    }

    //==================================================================================== Private members

    /** √
     * Return the number of positions, which is the same as the number of digits, in the 'magnitude' of 'this'.
     *
     * For example, magnitudes "BA" and "CBA" in base 6 have 2 and 3 positions, respectively.
     */
    private int getNumberOfPositions(){
        return getMagnitude().length();
    }

    /** √
     * Return the value of the digit at position 'pos'.
     * 'pos' should be non-negative. Return 0 if 'pos' is greater than the maximum position.
     *
     * For example, given magnitude "GECA" in base 9, the digits at positions 1, 3, and 5 are 'C', 'G', and 'A', and
     * their values are 2, 6, and 0, respectively.
     */
    private int getValueAtPosition(int pos){
        assert(pos >= 0);

        char digit;
        if(pos >= getNumberOfPositions())
            digit = DIGIT_ZERO;
        else
            digit = getMagnitude().charAt(getNumberOfPositions() - 1 - pos);

        return getValueFromDigit(digit, getBase());
    }

    //======================================================================================== Static members

    public static final int BASE_MINIMUM = 2;
    public static final int BASE_MAXIMUM = 26;
    public static final char DIGIT_ZERO = 'A';

    /** √
     * The largest BaseNIntegerUnsigned objects that can be represented as int values.
     */
    private static final BaseNIntegerUnsigned[] MAX_VALUE_BASE_N;

    static{
        // To initialize MAX_VALUE_BASE_N.
        MAX_VALUE_BASE_N = new BaseNIntegerUnsigned[27];
        int maxInt = Integer.MAX_VALUE;
        for(int base = BASE_MINIMUM ; base <= BASE_MAXIMUM ; base++ ){
            MAX_VALUE_BASE_N [base] = new BaseNIntegerUnsigned(encode(maxInt, base), base);
        }
    }

    /** √
     * Return the greatest BaseNIntegerUnsigned object with 'base' that represent Integer.MAX_VALUE.
     *
     * For example, the object will be "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB(2)", i.e., 31 ones, when base is 2.
     */
    public static BaseNIntegerUnsigned getMaximum(int base){
        if(!isValidBase(base))
            throw new IllegalArgumentException();

        return MAX_VALUE_BASE_N [base];
    }

    /** √
     * Return the encoding of 'value' in 'base'.
     *
     * For example, 16 will encoded as "BAAAA" when base is 2 and "DB" when base is 5.
     */
    public static String encode(int value, int base){
        if(value < 0 || !isValidBase(base))
            throw new IllegalArgumentException();

        if(value == 0)
            return DIGIT_ZERO + "";

        StringBuilder builder = new StringBuilder();
        int quotient = value;
        int remainder = 0;
        while(quotient != 0){
            remainder = quotient % base;
            quotient /= base;
            char digit = getDigitFromValue(remainder, base);
            builder.insert(0, digit);
        }
        return builder.toString();
    }

    /** √
     * Return true if 'base' is between BASE_MINIMUM and BASE_MAXIMUM (both inclusive); Otherwise, return false.
     */
    public static boolean isValidBase(int base){
        return BASE_MINIMUM <= base && base <= BASE_MAXIMUM;
    }

    /** √
     * Return true if 'base' is valid and 'magnitude' is valid for 'base'.
     * 'magnitude' is valid for 'base' if 1) it is not null, 2) it is not empty, and 3) it contains only digits
     * that are valid for the base.
     */
    public static boolean isValidMagnitude(String magnitude, int base){
        if(!isValidBase(base) || magnitude == null || magnitude.isEmpty())
            return false;

        // Add missing code here.
        for (int i=0; i < magnitude.length(); i++){
            // check whether the digit is in the range of ['A', the largest digit of the base]
            if (magnitude.charAt(i) < DIGIT_ZERO || magnitude.charAt(i) > getLargestDigit(base)){
                return false;
            }
        }
        return true;
    }

    /** √
     * Return the simplified magnitude string by removing unnecessary leading zeros, i.e., DIGIT_ZEROs,
     * from 'magnitudeStr'.
     */
    public static String withoutLeadingZeroes(String magnitudeStr){
        assert(magnitudeStr != null && !magnitudeStr.isEmpty());

        int pos = 0;
        for(; pos < magnitudeStr.length(); pos++){
            if(magnitudeStr.charAt(pos) != DIGIT_ZERO)
                break;
        }
        if(pos < magnitudeStr.length())
            return magnitudeStr.substring(pos);
        else
            return DIGIT_ZERO + "";
    }

    /** √
     * Return the largest digit allowed by 'base'. Throw IllegalArgumentException if 'base' is invalid.
     *
     * For example, the largest digits for base 4 and 6 are 'D' and 'F', respectively.
     */
    public static char getLargestDigit(int base){
        if(!isValidBase(base))
            throw new IllegalArgumentException();

        return (char)(DIGIT_ZERO + base - 1);
    }

    /** √
     * Return the digit in 'base' with the specified 'value'. Throw IllegalArgumentException if 'base' is invalid or
     * 'value' cannot be represented using a single digit in 'base'.
     *
     * For example, value 3 is represented using digit 'D' in base 5.
     */
    public static char getDigitFromValue(int value, int base){
        if(!isValidBase(base) || value < 0 || value >= base)
            throw new IllegalArgumentException();

        return (char)(DIGIT_ZERO + value);
    }

    /** √
     * Return the value of 'digit' in 'base'. Throw IllegalArgumentException if 'base' is invalid or 'digit' is not
     * a valid digit for 'base'.
     *
     * For example, digit 'C' in base 6 has value 2.
     */
    public static int getValueFromDigit(char digit, int base){
        if(!isValidBase(base) || digit < DIGIT_ZERO || digit > getLargestDigit(base))
            throw new IllegalArgumentException();

        return digit - DIGIT_ZERO;
    }

} // OOP_A3-2 Made by ZHANG Wengyu(21098431d)
