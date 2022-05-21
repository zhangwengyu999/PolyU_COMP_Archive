// OOP_A1-1
// Made by Mike_Zhang(21098431d)
package hk.edu.polyu.comp.comp2021.assignment1;

import static java.lang.Integer.parseInt;

public class MiniFloat {

    public static void main(String[] args){
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        int exponentValue; // store exponent Value in Dec
        float mantissaValue; //store mantissa Value in Dec
        float miniFloatOut; // output in float
        String exponentBit = bitSequence.substring(1,5); // store the bits for exponent
        String exponentSignBit = bitSequence.substring(1,2); // store a bit for exponent's sign
        String signBit = bitSequence.substring(0,1); // store the bit for sign
        String mantissaBit = bitSequence.substring(5); // store the mantissa Bit
        // get the exponent
        if (exponentSignBit.equals("1")){ // "-" exponent
            exponentValue =  parseInt(exponentBit, 2) - 16;
        }
        else {
            exponentValue = parseInt(exponentBit, 2);
        }
        // get the mantissa
        mantissaValue = ((float) parseInt(mantissaBit, 2))/8 + 1;
        // get absolute result = mantissa * 2 ^ exponent
        miniFloatOut = (float)( mantissaValue * Math.pow(2, exponentValue));
        // add the sign
        if (signBit.equals("1")){
            miniFloatOut = 0-miniFloatOut;
        }
        return miniFloatOut;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        float delta;
        float base = 0f;
        // Task 2: return the number of integral miniFloat values
        for(String s: getValidMiniFloatBitSequences()){
            float miniFloatSample = miniFloatFromString(s);
            // Explicit type conversion from float to int may cause value loss (Round-off Errors)
            delta = miniFloatSample - (int) miniFloatSample;
            if (delta == base){ // if NOT detect value loss, it is integer miniFloat number
                // System.out.println(miniFloatSample); // list out all integer miniFloat numbers
                count++;
            }
        }
        return count;
    }



    /**
     * Get all valid bit sequences for miniFloat values.
     */
    private static String[] getValidMiniFloatBitSequences(){
        int nbrValues = (int)Math.pow(2, MINI_FLOAT_SIZE);

        String[] result = new String[nbrValues];
        for(int i = 0; i < nbrValues; i++){
            result[i] = String.format("%" + MINI_FLOAT_SIZE + "s", Integer.toBinaryString(i))
                    .replace(' ', '0');
        }
        return result;
    }

    private static int MINI_FLOAT_SIZE = 8;
}
// OOP_A1-1
// Made by Mike_Zhang(21098431d)