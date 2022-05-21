package hk.edu.polyu.comp.comp2021.assignment4.calculator;

/**
 * Enumeration of binary operators.
 */
public enum BinaryOperator {
    ADD("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/");

    private final String symbol;

    BinaryOperator(String symbol) {
        this.symbol = symbol;
    }

    // Calculate the result of applying 'this' on 'left' and 'right' expressions, with their variables
    // defined in 'env'.
    public int calculate(Expression left, Expression right, Environment env){
        if (left == null || right == null)
            throw new IllegalArgumentException();

        int result=0;
        switch (this){
            case ADD:
                result= left.evaluate(env) + right.evaluate(env);
                break;
            case MINUS:
                result= left.evaluate(env) - right.evaluate(env);
                break;
            case MULTIPLY:
                result= left.evaluate(env) * right.evaluate(env);
                break;
            case DIVIDE:
                if (right.evaluate(env) == 0) 
                    throw new IllegalArgumentException();

                result = left.evaluate(env) / right.evaluate(env);
                break;
        }

        return result;
    }

    public String getSymbol(){
        return this.symbol;
    }

    public String toString(){
        return getSymbol();
    }


}
