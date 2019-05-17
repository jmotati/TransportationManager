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
public class VehicleManagement {

    private Vehicle[] vehicles; // The array of Vehicles that have been assigned a Route
    private int count; // The number of vehicles in the 'vehicles' array (and therefore assigned to a Route)
    private int bus; // The number of buses that are available (not assigned to a Route)
    private int plane; // The number of airplanes that available (not assigned to a Route)

    /*
     * You should initialize the vehicles array to be empty (not null), and the
     * count to be 0.
     */
    public VehicleManagement(int bus, int plane) {
        vehicles = new Vehicle[0];
        this.count = 0;
        this.bus = bus;
        this.plane = plane;
    }

    /*
     * creates a new vehicle object of type Bus with the given capacity and adds
     * it to the vehicles array. But, there can be only one Vehicle assigned to
     * a given Route. Therefore if the given Route has already been created, the
     * function returns false and doesn't modify anything. It returns true if
     * the Route is added. Don't forget to update the number of available
     * busses!
     */
    public boolean createRoute(Route route, int capacity) {
        if (this.bus > 0) {
            if (lookupVehicle(route) == -1) {

                //extend array
                Vehicle[] newVehicles = new Vehicle[count + 1];
                for (int i = 0; i < vehicles.length; i++) {
                    newVehicles[i] = vehicles[i];
                }
                vehicles = newVehicles;
                vehicles[count] = new Bus(route, capacity);
                count = count + 1;
                this.bus--;
                return true;
            } else {
                return false;
            }

        } else {
            //no bus
            return false;
        }

    }

    /*
     * Check if the Passenger wants to book on an existing Route; If not, cancel
     * the Passenger booking and return false. Add the Passenger to the correct
     * Vehicle (through the addPassenger function of the Vehicle interface).
     * Update the number of planes and busses, if necessary.
     */
    public boolean addPassengerToVehicle(Passenger person) {
        int route = lookupVehicle(person.getRoute());
        //does not exist
        if (route == -1) {
            person.cancel();
            return false;
        } else {
            boolean added;
            //If there is no plane available
            if (vehicles[route] instanceof Bus) {
                /*
                 * If there is no plane available, a Passenger added to a Bus
                 * shouldn't have the chance to be put on the waiting list.
                 */
                if (this.plane <= 0) {
                    added = vehicles[route].addPassenger(person, false);
                } else {
                    added = vehicles[route].addPassenger(person, true);
                }
            } else {
                //no chance of waitlist in plane
                added = vehicles[route].addPassenger(person, false);
            }

            /*
             * If the addPassenger function (of the Vehicle interface) returns
             * false, then the Vehicle is full and nothing can be done. The
             * function returns false.
             */
            if (!added) {
                return false;
            }

            //Otherwise, it is still possible that the Passenger has been put on the waiting list
            //if(person.getBookingStatus().equals(Passenger.WAITLIST)) {
            //Maybe the Vehicle can be upgraded to maximize the company's profit
            /*
             * a Vehicle should be updated only if the number of Passengers
             * booked is greater or equal to twice the original capacity (>= )
             * and there are available planes
             */
            if (this.plane > 0 && vehicles[route] instanceof Bus) {
                if (vehicles[route].getCount() >= vehicles[route].getCapacity() * 2) {
                    //the Vehicle should be upgraded with a Vehicle three times bigger than the current one
                    Vehicle v = vehicles[route].upgrade(vehicles[route].getCapacity() * 3);
                    //(don't forget to replace the old Vehicle by the new one, and update the
                    //number of available vehicle).

                    vehicles[route] = v;
                    this.plane--;
                    this.bus++;

                    //confirm booking of all waitlisted passenger
                    for (int i = 0; i < vehicles[route].getCount(); i++) {
                        vehicles[route].getPassengers()[i].confirm();
                    }
                }
            }

            return true;
        }

    }

    public Vehicle[] getVehicles() {
        return this.vehicles;
    }

    public int getCount() {
        return this.count;
    }

    /*
     * Returns index of the Vehicle assigned to the given Route
     */
    public int lookupVehicle(Route route) {
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i].getRoute().equals(route)) {
                return i;
            }
        }
        return -1;
    }

    public int getAvailableBus() {
        return this.bus;
    }

    public int getAvailablePlane() {
        return this.plane;
    }
}
