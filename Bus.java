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
public class Bus implements Vehicle {

    private int capacity; // The maximum number of passengers this vehicle can hold
    private Passenger[] passengers; // The array of Passengers assigned to this vehicle
    private int count; // The number of passengers currently assigned to this vehicle
    private Route route; // The route on which this Vehicle will travel

    /*
     * Creates a new Bus object with a default capacity of 2.
     *
     *
     */
    public Bus(Route route) {
        this(route, 2);
    }

    /*
     * a. Creates a new Bus object and initializes the route and capacity
     * instance variables with their respective arguments. b. Make sure to
     * initialize the Passenger array and the count!
     */
    public Bus(Route route, int capacity) {
        this.route = route;
        this.capacity = capacity;
        passengers = new Passenger[0];
        this.count = 0;
    }

    //The class implements the Vehicle interface with the following behavior
// (in addition to the Vehicle interface description):
    /*
     * adds a Passenger object to the passengers array, returns true if
     * successful, false otherwise. successful only if the Passenger's Route and
     * Bus's Route matches (even if the Passenger stays on the WaitList) the
     * array has to be extended if needed
     */
    public boolean addPassenger(Passenger person) {
        System.out.println("in add passenger.");

        //  if(count>=capacity) {
        //     person.waitlist();
        //return false;
        //  }
        boolean sameRoute = this.route.equals(person.getRoute());
        //A Passenger can only be added to the Bus 
        //if the route of the Bus matches that of the Passenger 
        //and the capacity has not been exceeded
        if (sameRoute) {

            if (count < capacity) {
                person.confirm();
            }
            Passenger[] newPassengers = new Passenger[count + 1];
            for (int i = 0; i < count; i++) {
                newPassengers[i] = passengers[i];
            }

            passengers = newPassengers;
            passengers[count] = person;
            count = count + 1;
        } else {
            person.cancel();
        }
        return sameRoute;
    }

    /*
     * Upgrade the Bus to an Airplane of the given capacity. This method has to
     * create an Airplane and re-assign all Passengers to it and returns it. *
     */
    @Override
    public Vehicle upgrade(int newCapacity) {
        System.out.println("In upgrade.");
        this.capacity = newCapacity;
        Vehicle airPlane = new Airplane(route, newCapacity);
        for (int i = 0; i < count; i++) {
            airPlane.addPassenger(passengers[i]);
        }
        return airPlane;
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
        System.out.println("in add passanger.");
        if (!waitingList) {
            if (count >= capacity) {
                person.cancel();
                return false; //no place on vehicle and no waitlisting allowed
            }
        }
        //possiblity of waitingList  
        return addPassenger(person);
    }
}
