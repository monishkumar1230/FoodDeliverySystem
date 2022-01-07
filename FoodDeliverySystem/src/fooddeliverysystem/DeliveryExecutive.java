package fooddeliverysystem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author monish
 */
public class DeliveryExecutive {
    
    // Attributes of DeliveryExecutive
    private ArrayList<Booking> BookingList;
    private int totalDeliveryCharge = 0;
    private int totalAllowanceCharge = 0;
    private String ID;
    
    // Constructor to create a new DeliveryExecutive
    DeliveryExecutive(int ID){
        this.ID = "DE"+ID;
        BookingList = new ArrayList<Booking>();
    }
    
    // Creates a Booking and Adds it to the BookingList
    public void addBooking(char restaurent, char destination, int noOfOrders, int deliveryCharge, int pickUpTime){
        BookingList.add(new Booking(restaurent, destination,1,50,pickUpTime));
    }
    
    /**
     * @return the BookingList
     */
    public ArrayList<Booking> getBookingList() {
        return BookingList;
    }

    /**
     * @param BookingList the BookingList to set
     */
    public void setBookingList(ArrayList<Booking> BookingList) {
        this.BookingList = BookingList;
    }

    /**
     * @return the totalDeliveryCharge
     */
    public int getTotalDeliveryCharge() {
        return totalDeliveryCharge;
    }

    /**
     * @param totalDeliveryCharge the totalDeliveryCharge to set
     */
    public void setTotalDeliveryCharge(int totalDeliveryCharge) {
        this.totalDeliveryCharge = totalDeliveryCharge;
    }

    /**
     * @return the totalAllowanceCharge
     */
    public int getTotalAllowanceCharge() {
        return totalAllowanceCharge;
    }

    /**
     * @param totalAllowanceCharge the totalAllowanceCharge to set
     */
    public void setTotalAllowanceCharge(int totalAllowanceCharge) {
        this.totalAllowanceCharge = totalAllowanceCharge;
    }

    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    
}
