public class Prototype {
    public static void main(String args[]) {
	AnyType array[] = new AnyType[10];
	for(int n=0; n<array.length; n++)
	    array[n] = new IntegerType(10-n);
	Sort sorting = new Sort();
	sorting.selectionSort(array);
	System.out.println("Comparisons: " + sorting.getComparisons() + "\nExchanges: " + sorting.getExchanges());
    }
}