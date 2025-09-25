import java.util.ArrayList;

public class Display {

    private int[] orderNumbers;

    private String[] orderNames;

    int count = 0;

    ArrayList<Integer> removals;


    Order newOrder;

    public Display(Order order) {
        newOrder = order;
        this.orderNumbers = new int[newOrder.getCurrentSize() * 3];
        this.orderNames = new String[newOrder.getCurrentSize() * 3];
        this.defaultPopulate();
        this.populateArrays();
        this.quickSort(orderNumbers, 0, orderNumbers.length - 1);
        this.quickSort(orderNames, 0, orderNames.length - 1);
    }

    private void populateOrderNumber(int orderNumber) {
        this.orderNumbers[count] = orderNumber;

    }

    private void populateOrderName(String orderName) {
        this.orderNames[count] = orderName;
    }

    private void defaultPopulate() {
        for (int i = 0; i < orderNumbers.length; i++) {
            orderNumbers[i] = -999;
            orderNames[i] = "AAAA";
        }
    }

    private void populateArrays() {
        if (newOrder.getCurrentSize() != 0) {
            this.populateOrderNumber(newOrder.getOrderNumber());
            this.populateOrderName(newOrder.getName());
            newOrder.dequeue();
            count++;
            populateArrays();
        }
    }


    private void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private void quickSort(String arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }


    private int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] >= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }


    private int partition(String arr[], int begin, int end) {
        int review = 0;

        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            while ((arr[j].charAt(review) == arr[end].charAt(review)) && (review < arr[j].length()) &&
                    (review < arr[end].length()) && !(arr[end].equals("AAAA"))) {
                review++;
            }

            if (arr[j].charAt(review) >= arr[end].charAt(review)) {
                i++;

                String swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        String swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }


    public void removeOrder(int listNum) {


        for (int i = 0, k = 0; i < orderNumbers.length - 1; i++) {

            if (i == listNum - 1) {
                orderNumbers[i] = -999;
                orderNames[i] = "AAAA";
            }
        }

    }

    public void addNewOrder(Order newOrder) {

        for (int i = 0; i < orderNumbers.length - 1; i++) {
            if (orderNumbers[i] == -999) {
                this.orderNumbers[i] = newOrder.getOrderNumber();
                break;
            }

        }

        for (int i = 0; i < orderNames.length - 1; i++) {
            if (orderNames[i].equals("AAAA")) {
                this.orderNames[i] = newOrder.getName();
                break;
            }

        }

        this.quickSort(orderNumbers, 0, orderNumbers.length - 1);
        this.quickSort(orderNames, 0, orderNames.length - 1);





    }

    public void printArrays() {
        for (int i = 0, k = 0; i < orderNumbers.length; i++) {
            if (orderNumbers[i] != -999) {
                System.out.println(k + 1 + ": " + orderNumbers[i]);
                k++;
            }
        }

        for (int i = 0, k = 0; i < orderNumbers.length; i++) {
            if (!(orderNames[i].equals("AAAA"))) {
                System.out.println(k + 1 + ": " + orderNames[i]);
                k++;
            }
        }


    }
}



