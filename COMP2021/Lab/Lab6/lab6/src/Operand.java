public class Operand {
    private int value;
    public Operand(int value) {
        this.value = value;
    }
    int evaluate() {
        return value;
    }
    public String toString() {
        return String.valueOf(value);
    }
}
