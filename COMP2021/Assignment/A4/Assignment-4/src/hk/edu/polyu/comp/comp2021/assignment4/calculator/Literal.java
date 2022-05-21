package hk.edu.polyu.comp.comp2021.assignment4.calculator;

/**
 * A literal int value.
 *
 */
public class Literal extends Expression{

    private final int value;

    public Literal(int value) {
        this.value = value;
    }

    public int evaluate(Environment env) {
        return value;
    }

    public String toString(){
        return String.valueOf(this.value);
    }



}
