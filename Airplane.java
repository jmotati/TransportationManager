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
public class Airplane implements Vehicle {

    private int capacity; // The maximum number of passengers this vehicle can hold
    private Passenger[] passengers; // The array of Passengers that have booked a trip on this vehicle
    private int count; // The number of passengers currently assigned to this vehicle
    private Route route; // The route on which this Vehicle will travel

    /*
     * creates a new Airplane object and initializes the instance variables with
     * their respective arguments
     */
    public Airplane(Route route, int capacity) {
        this.route = route;
        this.capacity = capacity;
        passengers = new Passenger[0];
        this.count = 0;
    }

    /*
     * adds a Passenger object to the passengers array, returns true if
     * successful, false otherwise successful only of the Passenger's Route and
     * Airplane's Route matches AND the airplane capacity have not been exceeded
     */
    @Override
    public boolean addPassenger(Passenger person) {

        //If the capacity of the
        //Airplane has been exceeded, the Passenger's status will be updated to 
        //Canceled
        if (count >= capacity) {
            person.cancel();
            return false;
        }
        boolean sameRoute = this.route.equals(person.getRoute());

        if (sameRoute) {
            Passenger[] newPassengers = new Passenger[count + 1];
            for (int i = 0; i < passengers.length; i++) {
                newPassengers[i] = passengers[i];
            }

            passengers = newPassengers;
            passengers[count] = person;
            count = count + 1;
            person.confirm();
        } else {
            person.cancel();
        }
        return sameRoute;
    }

    /*
     * the method returns null as no upgrade is available.
     */
    @Override
    public Vehicle upgrade(int newCapacity) {
        return null;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public Passenger[] getPassengers() {
        return passengers;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Route getRoute() {
        return route;
    }

    @Override
    public boolean addPassenger(Passenger person, boolean waitingList) {

        if (count >= capacity) {
            person.cancel();
            return false; //no place on vehicle and no waitlisting allowed
        }

        return addPassenger(person); //no waiting list
    }

}
