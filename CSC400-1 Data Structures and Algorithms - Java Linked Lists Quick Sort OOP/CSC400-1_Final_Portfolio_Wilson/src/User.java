import java.util.Scanner;

public class User {


    String inputName;

    String inputNumber;

    String inputCost;

    String again;
    Boolean stop;

    public Order newOrder = new Order();


    Scanner input = new Scanner(System.in);


    public Boolean userAdd() {
        System.out.println("Please input the customer's last name:");
        this.inputName = input.nextLine();

        System.out.println("Please input the order number:");
        this.inputNumber = input.nextLine();

        System.out.println("Please enter the order cost:");
        this.inputCost = input.nextLine();

        newOrder.enqueue(inputName,Integer.parseInt(inputNumber),Double.parseDouble(inputCost));

        System.out.println("Would you like to add another order?");
        again = input.nextLine();

        if (again.toLowerCase().equals("yes") || again.toLowerCase().equals("y")) {
            stop = false;
        }
        else {
            stop = true;
        }

        return stop;


    }



}
