public abstract class Expression {
    public abstract int evaluate();

    public abstract String toInfixString();
    public abstract String toPrefixString();
    public abstract String toPostfixString();
}

class Operand extends Expression {
    private int value;

    Operand (int value) {
        this.value = value;
    }

    public int evaluate() {
        return value;
    }

    public String toInfixString() {
        return String.valueOf(value);
    }
    public String toPrefixString() {
        return String.valueOf(value);
    }
    public String toPostfixString() {
        return String.valueOf(value);
    }
}

class BinaryExpression extends Expression {
    private Expression left;
    private Expression right;
    private BinaryOperator binaryOperator;

    public BinaryExpression(Expression left, Expression right,
                            BinaryOperator binaryOperator) {
        this.left = left;
        this.right = right;
        this.binaryOperator = binaryOperator;
    }

    public int evaluate() {
        return binaryOperator.calculate(left, right);
    }

    public String toInfixString() {
        return ("("+left.toInfixString()+" "+binaryOperator.toString()+" "+right.toInfixString()+")");
    }
    public String toPrefixString() {
        return (binaryOperator.toString()+" "+left.toPrefixString()+" "+right.toPrefixString());
    }
    public String toPostfixString() {
        return (left.toPostfixString()+" "+right.toPostfixString()+" "+binaryOperator.toString());
    }
}

class UnaryExpression extends Expression {
    private Expression right;
    private UnaryOperator unaryOperator;

    public UnaryExpression(UnaryOperator unaryOperator, Expression right){
        this.unaryOperator = unaryOperator;
        this.right = right;
    }

    public int evaluate() {
        return unaryOperator.calculate(right);
    }

    public String toInfixString() {
        return (unaryOperator.toString()+" "+right.toInfixString());
    }
    public String toPrefixString() {
        return (unaryOperator.toString()+" "+right.toPrefixString());
    }
    public String toPostfixString() {
        return (right.toPostfixString()+" "+unaryOperator.toString());
    }
}
