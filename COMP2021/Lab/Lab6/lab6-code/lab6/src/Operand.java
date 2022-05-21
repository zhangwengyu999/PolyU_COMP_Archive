public class Operand {
    private int value;

    public Operand(int value) {
        this.value = value;
    }

    public int evaluate() {
        return value;
    }

    public String toString(){
        return String.valueOf(this.value);
    }

    public static Operand fromString(String s){
        return new Operand(Integer.parseInt(s));
    }
}
