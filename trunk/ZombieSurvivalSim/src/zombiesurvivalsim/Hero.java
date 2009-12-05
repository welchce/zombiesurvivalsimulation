/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * A Hero is a type of Creature. It attempts to get ot the nearest safe zone
 * but will attack zombies that it encounters in the process.
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class Hero extends Human {

    /**
     * Default Constructor
     * @param position - the initial position of the Hero.
     */
    public Hero(Point position) {
        super(position);
    }

    /**
     * returns the Hero Creature type.
     * @return - EntityEnum.Hero
     */
    @Override
    public EntityEnum getType() { return EntityEnum.HERO; }

    /**
     * gets the next event associated with the Hero.
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
