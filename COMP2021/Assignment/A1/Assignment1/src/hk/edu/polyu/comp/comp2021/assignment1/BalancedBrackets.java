// OOP_A1-3
// Made by Mike_Zhang(21098431d)
package hk.edu.polyu.comp.comp2021.assignment1;

import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        boolean check = true;
        Stack bStack = new Stack();
        String nowStr;
        if (str != null && !str.isEmpty() && isContains(str)){ // check condition 1) & 2)
            for (int j = 0; j < str.length(); j++){ // check condition 3) by using Stack
                nowStr = Character.toString(str.charAt(j));
                if (nowStr.equals("(") || nowStr.equals("[") || nowStr.equals("{")){ // when detect "([{", push it into the stack
                    bStack.push(nowStr);
                }
                // if it is balance, if detect ")" or "]" or "}", the latest one (peek) in the stack should be "(" or "[" or "{" respectively
                if (nowStr.equals(")")){
                    if (bStack.peek().equals("(") && !bStack.isEmpty()){bStack.pop();} // if it is '(' and the Stack is not empty, move it out (pop)
                    else {check = false; break;} // if not, it is NOT balanced, mark it false, break the loop
                }
                if (nowStr.equals("]")){
                    if (bStack.peek().equals("[") && !bStack.isEmpty()){bStack.pop();}
                    else {check = false; break;}
                }
                if (nowStr.equals("}")){
                    if (bStack.peek().equals("{") && !bStack.isEmpty()){bStack.pop();}
                    else {check = false; break;}
                }
            }
            if (!bStack.isEmpty()){check = false;} // after the loop, if the stark is NOT empty which means still remain not paired elements. So it is NOT balanced
        }
        else {check = false;}
        return check;
    }
    // for check condition 2)
    public static boolean isContains(String str){
        String bra = "(){}[]";
        boolean braCheck = true;
        for (int i = 0; i<str.length(); i++){
            if (!bra.contains(str.charAt(i)+"")) {
                braCheck = false;
                break;
            }
        }
        return braCheck;
    }
}
// OOP_A1-3
// Made by Mike_Zhang(21098431d)
