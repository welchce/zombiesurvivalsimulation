package zombiesurvivalsim;

import java.awt.Point;
import java.util.ArrayList;

/**
 * A Safezone is a type of Entity. It is randomly spawned in the beginning.
 * @author Raymond Cox <rj.cox101 at gmail.com>
 * @author Kurt Stauffer
 */
public class SafeZone extends Entity {

    /**
     * Default Constructor
     * @param the initial position of the Zombie.
     */
    public SafeZone(Point location) {
        super(location);
    }

     /**
     * @return SafeZone entity type
     */
    @Override
    public EntityEnum getType() {
        return EntityEnum.SAFEZONE;
    }
    /**
     * @return the next event associated with safezone
     * (null because a safezone does not move).
     */
    @Override
    public Event getNextEvent(ArrayList<Entity> board) {
        return null;
    }
}
