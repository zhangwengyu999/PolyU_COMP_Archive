public interface Operand {
    int evaluate();
    String toString();
    static Operand fromString(String s){
        String[] tmp = s.split(" ");
        if (tmp.length == 1) {
            return new SingleOperand(Integer.parseInt(s));
        }
        else {
            SingleOperand l;
            SingleOperand r;
            BinaryOperator bop;

            if (tmp.length == 2) {
                bop=BinaryOperator.fromString(tmp[0]);
                r=new SingleOperand(Integer.parseInt(tmp[1]));
                return new UnaryExpression(bop,r);
            }
            else {
                l=new SingleOperand(Integer.parseInt(tmp[0]));
                bop=BinaryOperator.fromString(tmp[1]);
                r=new SingleOperand(Integer.parseInt(tmp[2]));
                return new BinaryExpression(l,r,bop);
            }

        }
    }
}

class SingleOperand implements Operand {
    private int value;

    public SingleOperand(int value) {
        this.value = value;
    }

    public int evaluate() {
        return value;
    }

    public String toString(){
        return String.valueOf(this.value);
    }

}

class BinaryExpression implements Operand {
    private Operand left;
    private Operand right;
    private BinaryOperator binaryOperator;


    public BinaryExpression(Operand left, Operand right,
                            BinaryOperator binaryOperator) {
        this.left = left;
        this.right = right;
        this.binaryOperator = binaryOperator;
    }
    public int evaluate() {
        return binaryOperator.calculate(left, right);
    }

    public String toString(){
        return left.toString()+" "+binaryOperator.toString()+" "+right.toString();
    }
}

class UnaryExpression extends BinaryExpression {
    public UnaryExpression(BinaryOperator binaryOperator,Operand right) {
        super(new SingleOperand(0),right,binaryOperator);
    }
}