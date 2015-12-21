class IntegerType implements AnyType {
    private int number;
    IntegerType() {
	number = 0;
    }
    IntegerType(int i) {
	number = i;
    }
    public String toString() {
	return(number + "");
    }
    public boolean isBetterThan(AnyType datum) {
	return(this.number > ((IntegerType)datum).number);
    }
}