/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import java.awt.Point;
import java.util.ArrayList;

/**
 * A Coward is a type of Creature that attempts to get to the nearest Safe zone
 * and either runs from any zombie that it encounters or cowers in fear in its
 * space.
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class Coward extends Creature {

    /**
     * Default Constructor
     * @param position - the initial position of the Coward.
     */
    public Coward(Point position) {
        super(position);
    }

    /**
     * returns the type of Coward.
     * @return - the Coward creature enum.
     */
    @Override
    public CreatureEnum getType() { return CreatureEnum.COWARD; }

    /**
     * gets hte next event associated with the creature.
     * @param creatures
     * @return
     */
    @Override
    public Event getNextEvent(ArrayList<Creature> neighbors, ArrayList<SafeZone> safeZones) {
        Creature newCoward = new Coward(moveTowardsSafezone(neighbors, safeZones));
        ActionEntity cowardAction = new ActionEntity(this, newCoward, ActionEnum.MOVE);
        return new Event(cowardAction,1);
    }
}
