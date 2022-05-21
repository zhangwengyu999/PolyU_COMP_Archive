package hk.edu.polyu.comp.comp2021.assignment2.charintmap; // OOP_A2-3 Made by ZHANG Wengyu(21098431d)

public class CharIntMap {

    private KeyValueEntry[] storage; // Internal storage of the map.

    private int count;// The number of key-value pairs currently stored in the map.

    public CharIntMap(int size) {
        // constructor
        if (size <= 52){
            storage = new KeyValueEntry[size]; // initialization for storage list with the length of size
        }
        else {
            System.out.println("Error! No more than 52!");
        }
    }

    public int getValue(char key){
        // Return the value associated to the key
        // This method should only be called when containsKey(key) is true
        if (containsKey(key)){ // check whether contain this key
            for(int i = 0; i < storage.length; i++){ // traverse all emements
                if (storage[i] == null){continue;} // if detect an empty element then skip it and continue to the next loop
                if (storage[i].getKey() == key){return storage[i].getValue();} // if found, get value
            }
        }
        else {System.out.printf("Error! This Key %c is NOT contained in the map!\n",key);}
        return 0;
    }

    public boolean isFull(){
        //Returns true if and only if the map contains the maximum number of keys.
        if (count == storage.length){return true;}
        return false;
    }
    public boolean containsKey(char key) {
        // Returns true if and only if this map contains a mapping for the specified key.
        for(int i = 0; i < storage.length; i++){
            if (storage[i] == null){continue;}
            if (storage[i].getKey() == key){return true;}
        }
        return false;
    }

    public boolean containsValue(int value) {
        // Returns true if and only if this map maps one or more keys to the specified value.
        for(int i = 0; i < storage.length; i++){
            if (storage[i] == null){continue;}
            if (storage[i].getValue() == value){return true;}
        }
        return false;
    }

    public void put(char key, int value) {
        //Associates the specified value with the specified key in this map.
        //If the map previously contained a mapping for the key, the old value is replaced by the specified value
        if (containsKey(key)){
            for(int i = 0; i < storage.length; i++){
                if (storage[i] == null){continue;}
                if (storage[i].getKey() == key){storage[i].setValue(value);}
            }
        }
        else {
            for(int i = 0; i < storage.length; i++){ // traverse the whole list from head to end
                if (storage[i] == null){ // once find the first empty KeyValueEntry, put the new key-value in
                    storage[i] = new KeyValueEntry(key,value);
                    count++; // count increase 1 after put 1 map in
                    break; // break the loop for only put one pair of key-value in the list
                }
            }
        }
    }

    public void remove(char key) {
        // Removes the mapping for a key from this map if it is present
        if (containsKey(key)){
            for(int i = 0; i < storage.length; i++){
                if (storage[i] == null){continue;}
                if (storage[i].getKey() == key){storage[i]=null; count--;} // count decrease 1 after remove 1 map out
            }
        }
        else {System.out.printf("Error! This Key %c is NOT contained in the map!\n",key);}
    }

    public void replace(char key, int value) {
        // Replaces the entry for the specified key if and only if it is currently mapped to some value.
        if (containsKey(key)){
            for(int i = 0; i < storage.length; i++){
                if (storage[i] == null){continue;}
                if (storage[i].getKey() == key){storage[i].setValue(value);}
            }
        }
        else {System.out.printf("Error! This Key %c is NOT contained in the map! \n",key);}
    }


    public void merge(char key, int value){
        //If the specified key is not in this map, associates it with the given non-null value.
        //Otherwise, replaces the associated value with the addition of the associated value and the given non-null value.
        if (containsKey(key)){
            for(int i = 0; i < storage.length; i++){
                if (storage[i] == null){continue;}
                if (storage[i].getKey() == key){storage[i].setValue(storage[i].getValue()+value);}
            }
        }
        else {
            put(key, value); // if not in the map, then invoke put() method to put this map in
        }

    }

    public String keyString() {
        // Returns a String concatenated from the keys in this map
        if (this != null) {
            StringBuilder keyString = new StringBuilder();
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] == null) {continue;}
                keyString.append(storage[i].getKey());
            }
            return keyString.toString();
        }
        return "";
    }

    public boolean hasSameKeys(CharIntMap map) {
        // Return true if and only if the keys of this map are exactly the same as those in the given map.
        if(this.count == map.count){ // check whether they have the same amount of maps
            for (int i = 0; i < count; i++){
                if(!this.containsKey(map.storage[i].getKey())){return false;}
            }
            return true;
        }
        return false;
    } // OOP_A2-3 Made by ZHANG Wengyu(21098431d)

    // ==================================

}

class KeyValueEntry {

    private char key;

    private int value;

    KeyValueEntry(char key, int value) {

        this.key = key;

        this.value = value;

    }

    public char getKey() {

        return this.key;

    }

    public int getValue() {

        return this.value;

    }

    public void setValue(int val) {

        this.value = val;

    }
}

