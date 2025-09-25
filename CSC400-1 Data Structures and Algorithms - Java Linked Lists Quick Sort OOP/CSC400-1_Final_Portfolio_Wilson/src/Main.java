import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Boolean stop = false;
        String action = "XX";

        Scanner input = new Scanner(System.in);

        User userOrder = new User();


        while (!stop) {
            stop = userOrder.userAdd();
        }

        Display newDisplay = new Display(userOrder.newOrder);
        System.out.println("List has been initialized");
        newDisplay.printArrays();



        while (!(action.toLowerCase().equals("quit"))) {
            int listNumber;
            System.out.println("Would you like to add, remove, print, or quit?");
            action = input.nextLine();

            if(action.toLowerCase().equals("add")) {
                userOrder.userAdd();
                newDisplay.addNewOrder(userOrder.newOrder);
                newDisplay.printArrays();
            }

            if (action.toLowerCase().equals("remove")) {
                System.out.println("Please enter the list number of the item you wish to remove.");
                listNumber = Integer.parseInt(input.nextLine());
                newDisplay.removeOrder(listNumber);
                newDisplay.printArrays();
            }

            if(action.toLowerCase().equals("print")) {
                newDisplay.printArrays();
            }


        }

    }



    }


