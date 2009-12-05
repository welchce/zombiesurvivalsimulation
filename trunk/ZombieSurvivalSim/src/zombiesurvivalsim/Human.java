/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * A human is a type of Creature.  It will attempt to get the nearest safe zone
 * and will either attack zombies it encounters or flee from the zombie.
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class Human extends Entity {

    /**
     * Default Constructor
     * @param position - the initial position of the Human
     */
    public Human(Point position) {
        super(position);
    }

    /**
     * returns the Human Creature type
     * @return - EntityEnum.HUMAN
     */
    @Override
    public EntityEnum getType() { return EntityEnum.HUMAN; }

    /**
     * gets the next event associated with the Human.
     * @param creatures
     * @return - the next event.
     */
    @Override
    public Event getNextEvent(ArrayList<Entity> board, EventQueue simulationQueue) {
        Point newLocation = getRandomMove(board, simulationQueue);
        ActionEntity action = new ActionEntity(ActionEnum.MOVE, this, newLocation);
        return new Event(action, 1);
    }
}
