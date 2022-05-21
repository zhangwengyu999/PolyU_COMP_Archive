public enum BinaryOperator {
    ADD("+"), SUB("-"), MUL("*"), DIV("/");
    private String symbol;

    BinaryOperator(String symbol) {
        this.symbol = symbol;
    }

    public int calculate(Operand left, Operand right){
        int result=0;
        if (left != null && right != null){
            switch (this){
                case ADD:
                    result= left.evaluate() + right.evaluate();
                    break;
                case SUB:
                    result= left.evaluate() - right.evaluate();
                    break;
                case MUL:
                    result= left.evaluate() * right.evaluate();
                    break;
                case DIV:
                    if (right.evaluate() != 0) {
                        result = left.evaluate() / right.evaluate();
                    }
                    break;
                default:
                    System.exit(1);
            }
        }
        else{
            System.exit(1);
        }
        return result;
    }
    public static BinaryOperator getOperator(String inStr) {
        if (inStr.equals("+")) {return BinaryOperator.ADD;}
        else if (inStr.equals("-")) {return BinaryOperator.SUB;}
        else if (inStr.equals("*")) {return BinaryOperator.MUL;}
        else if (inStr.equals("/")) {return BinaryOperator.DIV;}
        else {return null;}
    }

    public String toString() {
        return symbol;
    }
}
