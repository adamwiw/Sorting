public class Sort {
    private int comparisons;
    private int exchanges;

    public Sort() {
	comparisons = 0;
	exchanges = 0;
    }
    
    public int getComparisons() {
	return(comparisons);
    }

    public int getExchanges() {
	return(exchanges);
    }
    
    private void reset() {
	comparisons = 0;
	exchanges = 0;
    }
    
    public void bubbleSort(AnyType[] array) {
	AnyType temp;
	int n = array.length;
	boolean cont = true;
	reset();
	for(int i = 0; i < n; i++) {
	    if(cont) {
		cont = false;
		for(int j = 1; j < n - i; j++) {
		    comparisons++;
		    if(array[j-1].isBetterThan(array[j])) {
			exchanges++;
			temp = array[j];
			array[j-1] = array[j];
			array[j] = temp;
			cont = true;
		    }
		}
	    }
	    else {
		break;
	    }
	}
    }
    
    public void insertionSort (AnyType[] array) {
	int n = array.length;
	reset();
        for(int i = 1; i < n; i++) {
	    int j = i;
            AnyType x = array[i];
            while(j > 0) {
		comparisons++;
		if(array[j-1].isBetterThan(x)) {
		    exchanges++;
		    array[j] = array[j-1];
		    j--;
		}
		else {
		    break;
		}
            }
            array[j] = x;
        }
    }

    public void selectionSort (AnyType[] array) {
	int n = array.length;
	reset();
	for(int i = 0; i < n - 1; i++) {
	    int min = i;
	    for(int j = i+1; j < n; j++) {
		comparisons++;
		if(array[min].isBetterThan(array[j])) {
		    min = j;
		}
	    }
	    exchanges++;
	    AnyType temp = array[min];
	    array[min] = array[i];
	    array[i] = temp;
	}
    }

    private int[] sequence1(int n) {
	int[] result = new int[n];
	int i = 0;
	do {
	    result[i] = n / (int) Math.pow(2, i + 1);
	} while (result[i++] > 1);
	return result;
    }
    
    private int[] sequence2(int n) {
	int[] result = new int[n];
	int i = 0;
	do {
	    result[i] = 2 * (n / (int) Math.pow(2, i + 2)) + 1;
	} while (result[i++] > 1);
	return result;
    }

    private int[] sequence3(int n) {
	int[] result = new int[n];
	int i = 0;
	do {
	    result[i] = (int) Math.pow(2, i + 1) - 1;
	} while ((int) Math.pow(2, ++i + 1) - 1 < n);
	return result;
    }
    
    private int[] sequence4(int n) {
	int[] result = new int[n];
	int i = 0;
	do {
	    result[i] = (int) (Math.pow(3, i + 1) - 1) / 2;
	} while ((int) (Math.pow(3, ++i + 1) - 1) / 2 < n);
	return result;
    }

    public void shellSort(AnyType[] array) {
	reset();
	int n = array.length;
	int[] seq = sequence4(n);
	for(int h = 0; seq[h] > 0; h++) {
	    for (int i = seq[h]; i < n; i++) {
		int j = i;
		AnyType temp = array[i];
		while (j >= seq[h]) {
		    comparisons++;
		    if(array[j - seq[h]].isBetterThan(temp)) {
			exchanges++;
			array[j] = array[j - seq[h]];
			j = j - seq[h];
		    } else {
			break;
		    }
		}
		array[j] = temp;
	    }
	}
    }

    private void quickSortRec(AnyType[] array, int lo, int hi) {
	int i = lo;
	int j = hi;
	AnyType pivot = array[lo + (hi - lo) / 2];
	while(i <= j) {
	    comparisons++;
	    while(pivot.isBetterThan(array[i]))
		i++;
	    while(array[j].isBetterThan(pivot))
		j--;
	    if(i <= j) {
		exchanges++;
		AnyType temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		i++;
		j--;
	    }
	}
	if(lo < j)
	    quickSortRec(array, lo, j);
	if(i < hi)
	    quickSortRec(array, i, hi);
    }
    
    public void quickSort(AnyType[] array) {
	reset();
	quickSortRec(array, 0, array.length - 1);
    }
    
    public void heapSort(AnyType[] array) {
	int i, j, leftChild, rightChild, betterChild, root;
	AnyType temp;
	int n = array.length - 1;
	
	reset();
	
	root = (n-1)/2;

	for(j = root; j >= 0; j--) {
	    for(i = root; i >= 0; i--) {
		leftChild = (2*i)+1;
		rightChild = (2*i)+2;
		if((leftChild <= n) && (rightChild <= n)) {
		    comparisons++;
		    if(array[rightChild].isBetterThan(array[leftChild]))
			betterChild = rightChild;
		    else
			betterChild = leftChild;
		}
		else{
		    if(rightChild > n)
			betterChild = leftChild;
		    else
			betterChild = rightChild;
		}

		if(array[betterChild].isBetterThan(array[i])){
		    exchanges++;
		    comparisons++;
		    temp = array[i];
		    array[i] = array[betterChild];
		    array[betterChild] = temp;
		}
	    }
	}
	temp = array[0];
	array[0] = array[n];
	array[n] = temp;
    }
//     private void upheap(AnyType[] array, int j) {
// 	int l = j;
// 	int key = array[l];
// 	int k = l/2;
// 	while(k>=1) {
// 	    comparisons++;
// 	    if(key.isBetterThan(array[k])) {
// 		array[l] = array[k];
// 		l = k;
// 		k = l/2;
// 	    }
// 	}
//     }
//     private void insertItem(AnyType[] H, AnyType Item) {
// 	if(
//     }
}
