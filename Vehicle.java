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
public interface Vehicle {

    public int getCapacity();
//returns the capacity of the Vehicle.

    public Route getRoute();
//returns the Route to be serviced by the vehicle.

    public int getCount();
//returns the total number of passenger bookings on the vehicle.

    public Passenger[] getPassengers();
///returns an array of Passengers which are currently associated 
//to the Vehicle so far. The array returned has to be populated
//within the index 0 to getCount() - 1 and of size getCount().

    public boolean addPassenger(Passenger person, boolean waitingList);
//adds a Passenger object to the passengers array, returns true if successful, 
//false otherwise.
//It also updates the Passenger booking status. 
//The boolean variable indicates if the function is allowed to put the passenger in
//the waiting list or not (If the Vehicle doesn't have a waiting list, this parameter is not used).
//If there is no waiting list or the waiting list is not allowed, a Passenger
    //can only be Confirmed and added to the list of
//Passengers (function returns true), or Canceled (function returns false)

    public boolean addPassenger(Passenger person);
//has the same behavior as the previous function and always allows the Passenger to be put in waiting list.

    public Vehicle upgrade(int newCapacity);
//returns the Vehicle which is the upgrade of the given Vehicle with the given capacity.
}
