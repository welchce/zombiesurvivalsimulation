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
public class Coward extends Entity {

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
     * gets the event associated with the Coward.
     * @return - the events.
     */
    @Override
    public Event getNextEvent(ArrayList<Entity> board) {
        Random randy = new Random();
        Event event=new Event(new ActionEntity(ActionEnum.MOVE_TO_SAFE, this), 3);

        for (Entity piece : board) {
            if (piece.getLocation().distance(getLocation()) == 1) {
                if (piece.getType() == EntityEnum.SAFEZONE) {
                    event=new Event(new ActionEntity(ActionEnum.HUMAN_SAVED, this), 2);
                    break;
                }
                if (piece.getType() == EntityEnum.ZOMBIE && randy.nextInt(2) == 1) {
                    event=new Event(new ActionEntity(ActionEnum.STAND_STILL, this), 2);
                    break;
                }
            }
        }
        return event;
    }
}
