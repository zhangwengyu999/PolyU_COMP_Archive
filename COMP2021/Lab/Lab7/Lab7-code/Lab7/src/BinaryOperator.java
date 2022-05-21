public enum BinaryOperator {
    ADD("+"), SUB("-"), MUL("*"), DIV("/");
    private final String symbol;

    BinaryOperator(String symbol) {
        this.symbol = symbol;
    }

    //Adjust here!
    public int calculate(Expression left, Expression right){
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

    public String getSymbol(){
        return this.symbol;
    }

    public String toString(){
        return getSymbol();
    }

}

