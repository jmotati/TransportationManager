/**
 * Project #3 - Transportation Company
 * <p>
 * This program is used to manage a transportation company.
 *
 * @author Jitesh Motati, Lab Section 17
 * @version 03/22/19
 *
 *
 */
public class Passenger {

    public static final String CANCELED = "Canceled";
    public static final String WAITLIST = "Waitlist";
    public static final String CONFIRMED = "Confirmed";
    private String passengerName; // The name of the Passenger
    private String bookingStatus; // The status of the Passenger's booking
    private Route route; // The Route on which the passenger wishes to travel

    /**
     * creates a new Passenger object and initializes
     * the instance variables with their respective arguments
     *
     * @param passengerName
     * @param route
     */
    public Passenger(String passengerName, Route route) {
        this.passengerName = passengerName;
        this.route = route;
        bookingStatus = WAITLIST;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public Route getRoute() {
        return route;
    }

    /**
     * Updates the booking status of the passenger to "Confirmed".
     */

    public void confirm() {
        if (bookingStatus.equals(WAITLIST)) {
            bookingStatus = CONFIRMED;
        }


    }

    /**
     * Updates the booking status of the passenger to "Canceled"
     */
    public void cancel() {
        if (bookingStatus.equals(WAITLIST)) {
            bookingStatus = CANCELED;

        }
    }


}
