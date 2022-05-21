public class Operand extends Expression {
    private int value;

    public Operand(int value) {
        this.value = value;
    }

    public int evaluate() {
        return value;
    }

    public String toInfixString(){
        return String.valueOf(this.value);
    }

    public String toPrefixString(){
        return String.valueOf(this.value);
    }

    public String toPostfixString(){
        return String.valueOf(this.value);
    }

}
