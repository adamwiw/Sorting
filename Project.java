import java.io.*;
import java.util.*;

public class Project {
    public static void saveData(File file, AnyType[] array) {
	BufferedWriter output = null;
	try {
	    output = new BufferedWriter(new FileWriter(file));
	    for(AnyType number: array) {
		output.write(number.toString() + " ");
	    }
	}
	catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (output != null) {
		try {
		    output.close();
		} catch(IOException e) {
		    e.printStackTrace();
		}
	    }
        }
    }

    public static AnyType[] generateData(int type, int order, int size) {
	AnyType data[] = new AnyType[size];

	switch(order) {
	    case 0:
		if(type==0) {
		    for(int n = 0; n < size; n++) {
			data[n] = new IntegerType(n);
		    }
		}
		else {
		    char a = 97;
		    char b = 97;
		    char c = 97;
		    for(int n = 0; n < size; n++) {
			if(c == 123) {
			    c = 97;
			    b++;
			}
			if(b == 123) {
			    b = 97;
			    a++;
			}
			data[n] = new StringType(new StringBuilder().append(a).append(b).append(c++).toString());
		    }
		}
		break;
	    case 1:
		if(type==0) {
		    int i = size - 1;
		    for(int n = 0; n < size; n++) {
			data[n] = new IntegerType(i--);
		    }
		} else {
		    char a = 122;
		    char b = 122;
		    char c = 122;
		    for(int n = 0; n < size; n++) {
			if(c == 96) {
			    c = 122;
			    b--;
			}
			if(b == 96) {
			    b = 122;
			    a--;
			}
			data[n] = new StringType(new StringBuilder().append(a).append(b).append(c--).toString());
		    }
		}
		break;
	    case 2:
		if(type==0) {
		    Random generator = new Random();
		    for(int n = 0; n < size; n++) {
			data[n] = new IntegerType(generator.nextInt(size));
		    }
		} else {
		    char a;
		    char b;
		    char c;
		    Random generator = new Random();
		    for(int n = 0; n < size; n++) {
			a = (char) (generator.nextInt(25) + 97);
			b = (char) (generator.nextInt(25) + 97);
			c = (char) (generator.nextInt(25) + 97);
			data[n] = new StringType(new StringBuilder().append(a).append(b).append(c).toString());
		    }
		}
		break;
	    default:
		if(type==0) {
		    for(int n = 0; n < size; n++) {
			data[n] = new IntegerType(n);
		    }
		}
		else {
		    char a = 97;
		    char b = 97;
		    char c = 97;
		    for(int n = 0; n < size; n++) {
			if(c == 123) {
			    c = 97;
			    b++;
			}
			if(b == 123) {
			    b = 97;
			    a++;
			}
			data[n] = new StringType(new StringBuilder().append(a).append(b).append(c++).toString());
		    }
		}
		break;
	}
	return data;
    }

    public static void main(String args[]) {
	String result[][] = new String[7][5];
	Scanner input = new Scanner(System.in);
	Scanner data;
	File file = null;
	Sort sorting = new Sort();
	AnyType array[] = new AnyType[2000];
	int choice = 0;
	int go = 1;
	int sort = 0;
	int type;
	String typeString;
	System.out.print("Select data type:\n\t[0] Integer\n\t[1] String\n\t[-1] Exit\nYour choice: ");
	type = input.nextInt();	
	if(type==-1)
	    System.exit(0);
	while(choice < 6) {
	    switch(type) {
		case 0:
		    typeString = "integer_";
		    break;
		case 1:
		    typeString = "string_";
		    break;
		default:
		    typeString = "integer_";
		    break;
	    }
	    switch(sort) {
		case 0:
		    file = new File(typeString + "ascending.txt");
		    break;
		case 1:
		    file = new File(typeString + "descending.txt");
		    break;
		case 2:
		    file = new File(typeString + "random.txt");
		    break;
		 default:
		    file = new File(typeString + "ascending.txt");
		    break;
	    }
	    try {
		data = new Scanner(file);
		if(type==0) {
		    for(int n = 0; data.hasNextInt(); n++) {
			array[n] = new IntegerType(data.nextInt());
		    }
		} else {
		    for(int n = 0; data.hasNext(); n++) {
			array[n] = new StringType(data.next());
		    }
		}
	    }
	    catch (FileNotFoundException e) {
		array = generateData(type, sort, 2000);
		saveData(file, array);
	    }
	    switch(choice) {
		case 0:
		    sorting.bubbleSort(array);
		    result[choice + 1][0] = "BubbleSort";
		    break;
		case 1:
		    sorting.insertionSort(array);
		    result[choice + 1][0] = "Insertion";
		    break;
		case 2:
		    sorting.selectionSort(array);
		    result[choice + 1][0] = "Selection";
		    break;
		case 3:
		    sorting.shellSort(array);
		    result[choice + 1][0] = "ShellSort";
		    break;
		case 4:
		    sorting.quickSort(array);
		    result[choice + 1][0] = "QuickSort";
		    break;
		case 5:
		    sorting.heapSort(array);
		    result[choice + 1][0] = "HeapSort";
		    break;
		default:
		    System.out.println("Input a number from zero to two, or -1 to exit!");
	    }
	    result[choice + 1][sort + 1] = String.format("%d/%-4d", sorting.getComparisons(), sorting.getExchanges());
	    go++;
	    if(sort==2) {
		sort=0;
		choice++;
	    } else {
		sort++;
	    }
	}
	result[0][0] = "Algorithm";
	result[0][1] = "Best Case(c/e)";
	result[0][2] = "Worst Case(c/e)";
	result[0][3] = "Random(c/e)";
	result[0][4] = "Average(c/e)";
	for(int n = 1; n < result.length; n++) {
	    try {
		String compAvg = String.valueOf((Integer.parseInt(result[n][1].substring(0, result[n][1].indexOf("/"))) + Integer.parseInt(result[n][2].substring(0, result[n][2].indexOf("/"))) + Integer.parseInt(result[n][3].substring(0, result[n][3].indexOf("/")))) / 3);
		String exAvg = String.valueOf((Integer.parseInt(result[n][1].substring(result[n][1].indexOf("/") + 1).trim()) + Integer.parseInt(result[n][2].substring(result[n][2].indexOf("/") + 1).trim()) + Integer.parseInt(result[n][3].substring(result[n][3].indexOf("/") + 1).trim())) / 3);
		result[n][4] = compAvg + "/" + exAvg;
	    }
	    catch(NumberFormatException e) {
		result[n][4] = "n/a";
	    }
	    catch(NullPointerException e) {
		result[n][4] = "n/a";
	    }
	}
	for(String line[]: result) {
	    System.out.println(line[0] + "\t" + line[1] + "\t" + line[2] + "\t" +  line[3] + "\t" + line[4]);
	}
    }
}
