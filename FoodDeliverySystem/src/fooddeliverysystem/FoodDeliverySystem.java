package fooddeliverysystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author monish
 */
public class FoodDeliverySystem {
    
    // Attributes of FoodDeliverySystem
    ArrayList<DeliveryExecutive> DeliveryExecutives;
    private final int NO_OF_DELIVERY_EXECUTIVES = 5;
    
    // constructor to initialize the food delivery system with the no of deliveryExecutives
    FoodDeliverySystem(){
        DeliveryExecutives = new ArrayList<>();
        for(int i=0;i<NO_OF_DELIVERY_EXECUTIVES;i++){
            // Creating and adding the deliveryExecutives to the deliveryExecutives array list in our FoodDeliverySystem
            DeliveryExecutive newDeliveryExecutive = new DeliveryExecutive(i+1);
            DeliveryExecutives.add(newDeliveryExecutive);
        }
    }
    
    //  function to add new delivery booking
    public void addNewBooking(Scanner scan){    
        // Getting the Customer ID
        System.out.print("\nCustomer ID: ");
        String customerID = scan.nextLine();  
        // Getting the Restaurant Name (In our case, a letter Eg: A, B, C etc.,)
        System.out.print("Restaurant : ");
        char restaurant = Character.toUpperCase((scan.nextLine()).charAt(0));        
        // Getting the Destination (In our case, a letter Eg: A, B, C etc.,)
        System.out.print("Destination Point : ");
        char destination = Character.toUpperCase((scan.nextLine()).charAt(0));       
        //Getting the Time Input (InputFormat - XX.XX AM/PM)
        String time = "";
        while(true){
            System.out.print("Time (XX.XX AM/PM): ");
            time = (scan.nextLine()).toUpperCase();
            if(checkTime(time)){
                break;
            }
            else{
                System.err.println("Invalid time format! Enter valid time!");
            }
        }
        
        // Converting time to minutes for further processing (minutes in 24Hrs format) 
        int timeInMinutes = convertToMinutes(time);
        
        // find if it contains DeliveryExecutives with same delivery location within 15 mins span
        DeliveryExecutive DeliveryExecutiveAssigned = findDeliveryExecutivesAtTimeSpan(timeInMinutes,restaurant,destination);
        
        // if no deliveryExecutive assigned within 15 mins span then assign the delivery to the least charged deliveryExecutive
        if(DeliveryExecutiveAssigned==null){
            // finding the least charged deliveryExecutive and adding the booking to the assigned deliveryExecutive
            DeliveryExecutiveAssigned = findLeastChargedDeliveryExecutive();
            DeliveryExecutiveAssigned.addBooking(restaurant, destination,1,50,timeInMinutes+15);
        }
        
        System.out.println("\nSuccessfully assigned "+DeliveryExecutiveAssigned.getID());
        
    }
    
    // function to find the least charged Delivery Executive
    private DeliveryExecutive findLeastChargedDeliveryExecutive(){      
        DeliveryExecutive LeastChargedDeliveryExecutive = DeliveryExecutives.get(0);
        
        for(int i=0;i<DeliveryExecutives.size();i++){
           DeliveryExecutive currentDeliveryExecutive = DeliveryExecutives.get(i);
           if(currentDeliveryExecutive.getTotalDeliveryCharge()<LeastChargedDeliveryExecutive.getTotalDeliveryCharge()){
               LeastChargedDeliveryExecutive = currentDeliveryExecutive;
           }
        }
        
        // assigning the delivery charge and allowance to the least charged deliveryExecutive for the new Booking
        LeastChargedDeliveryExecutive.setTotalDeliveryCharge(50);
        LeastChargedDeliveryExecutive.setTotalAllowanceCharge(10);
        ArrayList<Booking> bookingList = LeastChargedDeliveryExecutive.getBookingList();
        return LeastChargedDeliveryExecutive;
        
    }
    
    // function to find the delivery executive assinged with the 15 mins time span of the current booking for the same destination
    private DeliveryExecutive findDeliveryExecutivesAtTimeSpan(int timeInMinutes, char restaurant,char destination){
        DeliveryExecutive DeliveryExecutiveAtTimeSpan = null;
        Booking bookingAtTimeSpan = null;
        
        for(int i=0;i<DeliveryExecutives.size();i++){
            int noOfBookings = 0;
            DeliveryExecutive currentDeliveryExecutive = DeliveryExecutives.get(i);
            
            for(int j=0;j<currentDeliveryExecutive.getBookingList().size();j++){
                Booking booking = currentDeliveryExecutive.getBookingList().get(j);
                if(booking.getRestaurent()!=restaurant || booking.getDestination()!=destination){
                    continue;
                }
                // checking whether the delivery executive current delivery 15 min time span for assigning the current booking
                if(booking.getPickUpTime()>=timeInMinutes && booking.getPickUpTime()-15<=timeInMinutes){
                    noOfBookings = booking.getNoOfOrders();
                    if(noOfBookings<5 && (bookingAtTimeSpan==null || booking.getDeliveryCharge()<bookingAtTimeSpan.getDeliveryCharge())){
                        DeliveryExecutiveAtTimeSpan = currentDeliveryExecutive;
                        bookingAtTimeSpan = booking;
                    }
                }  
            }
            
        }
        
        // If the booking is with in 15 time span of deliveryExecutive, then updating the delivery charge and details 
        if(bookingAtTimeSpan!=null){
            int noOfBookings = bookingAtTimeSpan.getNoOfOrders();
            int totalCharge = bookingAtTimeSpan.getDeliveryCharge()+5;
            bookingAtTimeSpan.setNoOfOrders(noOfBookings+1);
            bookingAtTimeSpan.setDeliveryCharge(totalCharge);
            int totalDeliveryExecutiveCharge = DeliveryExecutiveAtTimeSpan.getTotalDeliveryCharge()+5;
            DeliveryExecutiveAtTimeSpan.setTotalDeliveryCharge(totalDeliveryExecutiveCharge);
        }
        
        return DeliveryExecutiveAtTimeSpan;
    }
    
    // function to check whether the time is valid or not
    private static boolean checkTime(String time){
        time = time.replace(".", " ");
        String timeArr[] = time.split("\\s+");
        if(timeArr.length == 3){
             if(Integer.parseInt(timeArr[0]) <= 12 && Integer.parseInt(timeArr[0]) >= 1){
                 if(Integer.parseInt(timeArr[1]) < 60 && Integer.parseInt(timeArr[0]) > 0){
                     if(timeArr[2].length() == 2){
                         if(timeArr[2].equals("AM") || timeArr[2].equals("PM")){
                             return true;
                         }
                     }
                 }
             }
        }    
        return false;
    }
    
    // function to parse the time and convert it to minutes for easy processing 
    private static int convertToMinutes(String time){
        time = time.replace(".", " ");
        String timeArr[] = time.split("\\s+");
     
        int hour = Integer.parseInt(timeArr[0]);
        int min = Integer.parseInt(timeArr[1]);
        
        if(hour==12 && timeArr[2].compareTo("AM")==0)
        {
            hour=0;
        }
        
        int timeInMinutes = hour*60+min;
        
        if(timeArr[2].compareTo("PM")==0 && hour!=12){
            //adding no of minutes in 12 hours to compensate the AM -> PM
            timeInMinutes+=720; 
        }
        
        
        return timeInMinutes;
    }
    
    //  fucntion to print the details of the deliveryExecutives
    public void printDetails() throws FileNotFoundException, IOException{
        
        // Creating a filewriter and printWriter to write the output to the file
        File output = new File("output.txt");
        FileWriter fileWriter = new FileWriter(output);
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println("\nDelivery History");
            System.out.println("\nDelivery History");
            int trip=1;
            printWriter.printf("%5s %12s %12s %18s %8s %15s %15s %17s\n","Trip", "Executive", "Restaurant", "Destination Point", "Orders", "Pickup Time", "Delivery Time", "Delivery Charge");
            System.out.printf("%5s %12s %12s %18s %8s %15s %15s %17s\n","Trip", "Executive", "Restaurant", "Destination Point", "Orders", "Pickup Time", "Delivery Time", "Delivery Charge");
            
            for(int i=0;i<DeliveryExecutives.size();i++){
                for(int j=0;j<DeliveryExecutives.get(i).getBookingList().size();j++){
                    Booking booking = DeliveryExecutives.get(i).getBookingList().get(j);
                    int hour = booking.getPickUpTime()/60;
                    int minutes = booking.getPickUpTime()%60;
                    int hourDelivery = (booking.getPickUpTime()+15)/60;
                    int minuteDelivery = (booking.getPickUpTime()+15)%60;
                    printWriter.printf("%5d %9s %11c %16c %12d %12d:%d %13d:%d %15d\n",trip, DeliveryExecutives.get(i).getID(), booking.getRestaurent(), booking.getDestination(), booking.getNoOfOrders(),(hour), minutes,(hourDelivery),minuteDelivery,booking.getDeliveryCharge());
                    System.out.printf("%5d %9s %11c %16c %12d %12d:%d %13d:%d %15d\n",trip++, DeliveryExecutives.get(i).getID(), booking.getRestaurent(), booking.getDestination(), booking.getNoOfOrders(),(hour), minutes,(hourDelivery),minuteDelivery,booking.getDeliveryCharge());
                }
            }
            
            printWriter.println("\nTotal Earned");
            System.out.println("\nTotal Earned");
            printWriter.printf("%10s %11s %18s %6s\n","Executive","Allowance","Delivery Charges", "Total");
            System.out.printf("%10s %11s %18s %6s\n","Executive","Allowance","Delivery Charges", "Total");
            for(int i=0;i<DeliveryExecutives.size();i++){
                DeliveryExecutive DeliveryExecutive= DeliveryExecutives.get(i);
                int total = DeliveryExecutive.getTotalAllowanceCharge()+DeliveryExecutive.getTotalDeliveryCharge();
                printWriter.printf("%7s %11d %13d %12d\n",DeliveryExecutive.getID(),DeliveryExecutive.getTotalAllowanceCharge(),DeliveryExecutive.getTotalDeliveryCharge(),total);
                System.out.printf("%7s %11d %13d %12d\n",DeliveryExecutive.getID(),DeliveryExecutive.getTotalAllowanceCharge(),DeliveryExecutive.getTotalDeliveryCharge(),total);
            }
            
            printWriter.flush();
        }
    }
    
}
