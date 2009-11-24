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
public class Zombie extends Creature {

    /**
     * Default Constructor
     * @param position - the initial position of the Zombie.
     */
    public Zombie(Point position) {
        super(position);
    }

    /**
     * returns the Creature type Zombie.
     * @return - CreatureEnum.ZOMBIE
     */
    @Override
    public CreatureEnum getType() { return CreatureEnum.ZOMBIE; }

    /**
     * gets the next event associated with the Zombie.
     * @param creatures
     * @return - the next event.
     */
    @Override
    public Event getNextEvent(ArrayList<Creature> neighbors, ArrayList<SafeZone> safeZones) {
        Creature newZombie = new Zombie(getRandomLocation(neighbors));
        ActionEntity zombieAction = new ActionEntity(this, newZombie, ActionEnum.MOVE);
        return new Event(zombieAction,1);
    }
}
