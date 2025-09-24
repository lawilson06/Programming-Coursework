import java.util.Scanner;

public class SortDetection {

    private int[] targetArray;
    private int temp;
    private int x;
    private int comparisons;
    private boolean needSort = false;
    private boolean swapped = true;
    private Scanner input = new Scanner(System.in);
    private int userValue;
    private int numOfElements;
    private int n = 0;
    private int outer;
    private int inner;

    public SortDetection(int y) {
        this.targetArray = new int[y];
        this.numOfElements = y;
        this.comparisons = targetArray.length - 1;
        this.fillArray();
    }

    private void fillArray() {
        System.out.println("Please enter a value");
        userValue = input.nextInt();
        targetArray[n] = userValue;
        while (n < numOfElements - 1) {
            n++;
            fillArray();
        }

    }

    private void swap() {
        temp = targetArray[x + 1];
        targetArray[x + 1] = targetArray[x];
        targetArray[x] = temp;
    }

    private boolean bubbleSort() {
        while (swapped & !needSort) {
            outer++;
            x = 0;
            swapped = false;
            while ((x < comparisons) && (!needSort)) {
                if (targetArray[x] > targetArray[x + 1]) {
                    swap();
                    swapped = true;
                    needSort = true;

                }
                x++;
                inner++;
            }
        }
     return needSort;
    }

    public void sortDetection() {

        if(!bubbleSort()) {
            System.out.println("Already sorted.");
            System.out.println("Outer: " + outer);
            System.out.println("Inner: " + inner);
        } else {
            System.out.println("Not sorted.");
            System.out.println("Outer: " + outer);
            System.out.println("Inner: " + inner);
        }
        needSort = false;
        swapped = true;

    }



}