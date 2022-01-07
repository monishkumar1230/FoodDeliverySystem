1package fooddeliverysystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author monish
 */
public class Main {
    
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        FoodDeliverySystem FDS = new FoodDeliverySystem();
        System.out.println("Welcome to Food Delivery System!");
        while(true){
            System.out.println("\n1. Add Booking");
            System.out.println("2. Print Details ('Output.txt' in current folder)");
            System.out.println("3. Exit");
            switch(scan.nextLine()){
                case "1": 
                    FDS.addNewBooking(scan);
                    break;
                case "2":
                    FDS.printDetails();
                    break;
                case "3":
                    System.out.println("\n Thank you for using our Food Delivery System!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Input! Give a Valid Input!");
            }
        }
    }
    
}
