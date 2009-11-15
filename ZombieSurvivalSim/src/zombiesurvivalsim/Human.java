/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import java.awt.Point;
import java.util.ArrayList;

/**
 * A human is a type of Creature.  It will attempt to get the nearest safe zone
 * and will either attack zombies it encounters or flee from the zombie.
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class Human extends Creature {

    /**
     * Default Constructor
     * @param position - the initial position of the Human
     */
    public Human(Point position) {
        super(position);
    }

    /**
     * returns the Human Creature type
     * @return - CreatureEnum.HUMAN
     */
    @Override
    public CreatureEnum getType() { return CreatureEnum.HUMAN; }

    /**
     * gets the next event associated with the Human.
     * @param creatures
     * @return - the next event.
     */
    @Override
    public Event getNextEvent(ArrayList<Creature> creatures) {
        Point newLoc = getRandomLocation(creatures);
        ActionEntity humanAction = new ActionEntity(this, ActionEnum.MOVE, newLoc);
        return new Event(humanAction,1);
    }
}
