enum BinaryOperator {
    ADD,SUB,MUL,DIV;
    public int calculate (Operand o1, Operand o2){
        if (this == ADD) {
            return (o1.evaluate() + o2.evaluate());
        }
        else if (this == SUB) {
            return (o1.evaluate() - o2.evaluate());
        }
        else if (this == MUL) {
            return (o1.evaluate() * o2.evaluate());
        }
        else if (this == DIV) {
            return (o1.evaluate() / o2.evaluate());
        }
        else {System.exit(1);return 0;}
    }
}
