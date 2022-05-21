package hk.edu.polyu.comp.comp2021.assignment4.compset;

import java.util.ArrayList;
import java.util.List;

class CompSet<T> {

    /** Each CompSet uses at most 1023 buckets.   */
    private static final int NUBMER_OF_BUCKETS = 1023;

    /** An array of buckets as the storage for each set. */
    private List<T>[] storage;


    public CompSet() {
        // Add missing code here
        // ZHANG Wengyu 21098431d
        storage = new ArrayList[NUBMER_OF_BUCKETS];
    }

    /**
     * Initialize 'this' with the unique elements from 'elements'.
     * Throw IllegalArgumentException if 'elements' is null.
     */
    public CompSet(List<T> elements) {
        // Add missing code here
        if (elements == null) {
            throw new IllegalArgumentException();
        }
        storage = new ArrayList[NUBMER_OF_BUCKETS];

        for (T ele:elements) {
            if (storage[getIndex(ele)] == null) {
                storage[getIndex(ele)] = new ArrayList<T>();
            }
            storage[getIndex(ele)].add(ele);
        }
    }

    /**
     * Get the total number of elements stored in 'this'.
     */
    public int getCount() {
        // Add missing code here
        int total = 0;
        for (List<T> ele : storage) {
            if (ele == null) {continue;}
            total = total + ele.size();
        }
        return total;
    }

    public boolean isEmpty() {
        // Add missing code here
        if (this.getCount() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Whether 'element' is contained in 'this'?
     */
    public boolean contains(T element) {
        // Add missing code here
        for (List<T> ele : storage) {
            if (ele == null) {continue;}
            if (ele.contains(element)) {return true;}
        }
        return false;
    }

    /**
     * Get all elements of 'this' as a list.
     */
    public List<T> getElements() {
        // Add missing code here
        List<T> outListT = new ArrayList<T>();
        for (List<T> ele : storage) {
            if (ele == null) {continue;}
            outListT.addAll(ele);
        }
        return outListT;
    }

    /**
     * Add 'element' to 'this', if it is not contained in 'this' yet.
     * Throw IllegalArgumentException if 'element' is null.
     */
    public void add(T element) {
        // Add missing code here
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (!contains(element)) {
            if (storage[getIndex(element)] == null) {
                storage[getIndex(element)] = new ArrayList<T>();
            }
            storage[getIndex(element)].add(element);
        }
    }

    /**
     * Two CompSets are equivalent is they contain the same elements.
     * The order of the elements inside each CompSet is irrelevant.
     */
    public boolean equals(Object other){
        // Add missing code here

        if (!(other instanceof CompSet)) {
            return false;
        }
        for (T ele:this.getElements()) {
            if (!((CompSet)other).getElements().contains(ele)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Remove 'element' from 'this', if it is contained in 'this'.
     * Throw IllegalArgumentException if 'element' is null.
     */
    public void remove (T element) {
        // Add missing code here
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (contains(element)) {
            storage[getIndex(element)].remove(element);
        }
    } // ZHANG Wengyu 21098431d

    //========================================================================== private methods

    private int getIndex(T element) {
        return element.hashCode() % NUBMER_OF_BUCKETS;
    }

}


