package hk.edu.polyu.comp.comp2021.assignment4.calculator;

/**
 * A variable expression.
 */
public class Variable extends Expression {
    private final String name;

    /**
     * Create a new variable with 'name'.
     * Throw IllegalArgumentException if 'name' is null or empty or 'name' contains other characters
     * than lower case English letters.
     */
    public Variable(String name){
        // Add missing code here
        // ZHANG Wengyu 21098431d
        if (name == null || name == "" || name.compareTo("z") > 0 || name.compareTo("a") < 0) {
            throw new IllegalArgumentException("Null or empty variable name");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return getName();
    }

    /**
     * Return the value of 'this' as defined in 'env'.
     * Throw IllegalArgumentException if 'env' is null or 'this' is not defined in 'env'.
     */
    public int evaluate(Environment env){
        // Add missing code here
        if (env == null || !env.hasDefined(this)) {
            throw new IllegalArgumentException("env is null or variable is not defined in env");
        }
        return env.getValue(this);

    } // ZHANG Wengyu 21098431d


}
