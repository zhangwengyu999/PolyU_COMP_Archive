public abstract class Expression {
    public abstract int evaluate();

    public abstract String toInfixString();
    public abstract String toPrefixString();
    public abstract String toPostfixString();

}
