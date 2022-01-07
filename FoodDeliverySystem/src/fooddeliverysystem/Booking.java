package fooddeliverysystem;

/**
 *
 * @author monish
 */

public class Booking {
        
    // Attributes of Booking class
    private char restaurent;
    private char destination;
    private int noOfOrders;
    private int deliveryCharge;
    private int pickUpTime;

    // Constructor to create a new Booking
    Booking(char restaurent, char destination, int noOfOrders, int deliveryCharge, int pickUpTime){
        this.restaurent = restaurent;
        this.deliveryCharge = deliveryCharge;
        this.pickUpTime = pickUpTime;
        this.noOfOrders = noOfOrders;
        this.destination = destination;
    }
    
    /**
     * @return the restaurent
     */
    public char getRestaurent() {
        return restaurent;
    }
    
    /**
     * @param restaurent the restaurent to set
     */
    public void setRestaurent(char restaurent) {
        this.restaurent = restaurent;
    }

    /**
     * @return the destination
     */
    public char getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(char destination) {
        this.destination = destination;
    }

    /**
     * @return the noOfOrders
     */
    public int getNoOfOrders() {
        return noOfOrders;
    }

    /**
     * @param noOfOrders the noOfOrders to set
     */
    public void setNoOfOrders(int noOfOrders) {
        this.noOfOrders = noOfOrders;
    }

    /**
     * @return the deliveryCharge
     */
    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    /**
     * @param deliveryCharge the deliveryCharge to set
     */
    public void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    /**
     * @return the pickUpTime
     */
    public int getPickUpTime() {
        return pickUpTime;
    }

    /**
     * @param pickUpTime the pickUpTime to set
     */
    public void setPickUpTime(int pickUpTime) {
        this.pickUpTime = pickUpTime;
    }
}
