/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * A Coward is a type of Creature that attempts to get to the nearest Safe zone
 * and either runs from any zombie that it encounters or cowers in fear in its
 * space.
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class Coward extends Human {

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
    public EntityEnum getType() { return EntityEnum.COWARD; }

    /**
     * gets hte next event associated with the creature.
     * @param creatures
     * @return
     */
    @Override
    public Event getNextEvent(ArrayList<Entity> board, EventQueue simulationQueue) {
        Point newLocation = getRandomMove(board, simulationQueue);
        ActionEntity action = new ActionEntity(ActionEnum.MOVE, this, newLocation);
        return new Event(action, 1);
    }
}
