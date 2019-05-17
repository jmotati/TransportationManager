import java.util.Objects;

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

public class Route {

    private String from; // The starting point of the Route
    private String to; // The destination of the Route

    public Route(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Route other = (Route) obj;
        if (!Objects.equals(this.from, other.from)) {
            return false;
        }
        return Objects.equals(this.to, other.to);
    }

    @Override
    public String toString() {
        return String.format("Route: From %s to %s\n", this.from, this.to);
    }
}
