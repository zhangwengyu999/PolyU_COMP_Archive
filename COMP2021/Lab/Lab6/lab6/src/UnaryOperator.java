public enum UnaryOperator {
    POS("+"), NEG("-");
    private String symbol;

    UnaryOperator(String symbol) {this.symbol = symbol;}

    public int calculator(Operand op) {
        int result = 0;
        if(op != null) {
            switch (this) {
                case POS:
                    result = op.evaluate();
                    break;
                case NEG:
                    result = 0-op.evaluate();
                    break;
                default:
                    System.exit(1);
            }
        }
        else {
            System.exit(1);
        }
        return result;
    }
    public static UnaryOperator getOperator(String inStr) {
        if (inStr.equals("+")) {return UnaryOperator.POS;}
        else if (inStr.equals("-")) {return UnaryOperator.NEG;}
        else {return null;}
    }

    public String toString() {
        return symbol;
    }


}
