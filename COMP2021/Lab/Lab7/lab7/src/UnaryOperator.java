public enum UnaryOperator {
    PLUS("#"), MINUS("~");
    private final String symbol;

    UnaryOperator(String symbol){
        this.symbol=symbol;
    }

    public int calculate(Expression op){
        int result=0;
        switch (this){
            case PLUS:
                result= +op.evaluate();
                break;
            case MINUS:
                result= -op.evaluate();
                break;
            default:
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

    public static UnaryOperator fromString(String symbol){
        for(UnaryOperator operator: UnaryOperator.values()){
            if(operator.getSymbol().equals(symbol)){
                return operator;
            }
        }
        System.exit(1);
        return null;
    }
}
