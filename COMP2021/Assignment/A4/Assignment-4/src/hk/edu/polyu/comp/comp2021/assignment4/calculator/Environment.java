package hk.edu.polyu.comp.comp2021.assignment4.calculator;

import java.util.HashMap;

/**
 * An environment defining values for variables.
 */
public class Environment {
    private HashMap<String, Integer> storage;

    public Environment(){
        // Add missing code here
        // ZHANG Wengyu 21098431d
        storage = new HashMap<String,Integer>();
    }

    /**
     * Has 'variable' been defined in this environment?
     */
    public boolean hasDefined(Variable variable){
        // Add missing code here
        if (storage.containsKey(variable.getName())) {
            return true;
        }
        return false;
    }

    /**
     * Define 'variable' with 'value'.
     * Throw IllegalArgumentException if 'variable' is null or the variable has been defined in 'this' already.
     */
    public void defineVariable(Variable variable, int value){
        // Add missing code here
        if (variable == null || hasDefined(variable)) {
            throw new IllegalArgumentException();
        }
        storage.put(variable.getName(),value);
    }

    /**
     * Get the value of 'variable'.
     * Throw IllegalArgumentException if 'variable' is null or the variable is not defined in 'this'.
     */
    public int getValue(Variable variable){
        // Add missing code here
        if (variable == null || !hasDefined(variable)) {
            throw new IllegalArgumentException();
        }
        return storage.get(variable.getName());
    } // ZHANG Wengyu 21098431d
}
