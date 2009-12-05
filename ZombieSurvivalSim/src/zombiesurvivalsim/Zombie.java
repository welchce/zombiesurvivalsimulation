/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import java.awt.Point;
import java.util.ArrayList;

/**
 * A Zombie is a type of Creature. It wanders around randomly, and will attack
 * nearby non-Zombies and Safe zones.
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class Zombie extends Entity {


    /**
     * Default Constructor
     * @param position - the initial position of the Zombie.
     */
    public Zombie(Point position) {
        super(position);
    }

    /**
     * returns the Creature type Zombie.
     * @return - EntityEnum.ZOMBIE
     */
    @Override
    public EntityEnum getType() { return EntityEnum.ZOMBIE; }

    /**
     * gets the events associated with the Zombie.
     * @param creatures
     * @return - the next event.
     */

    @Override
    public Event getNextEvent(ArrayList<Entity> board) {
        return new Event(new ActionEntity(ActionEnum.HUNT_HUMANS, this), 2);
    }
}
