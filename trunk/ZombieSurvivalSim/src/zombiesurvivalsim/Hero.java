/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import java.awt.Point;
import java.util.ArrayList;

/**
 * A Hero is a type of Creature. It attempts to get ot the nearest safe zone
 * but will attack zombies that it encounters in the process.
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class Hero extends Creature {

    /**
     * Default Constructor
     * @param position - the initial position of the Hero.
     */
    public Hero(Point position) {
        super(position);
    }

    /**
     * returns the Hero Creature type.
     * @return - CreatureEnum.Hero
     */
    @Override
    public CreatureEnum getType() { return CreatureEnum.HERO; }

    /**
     * gets the next event associated with the Hero.
     * @param creatures
     * @return - the next event.
     */
    @Override
    public Event getNextEvent(ArrayList<Creature> neighbors, ArrayList<SafeZone> safeZones) {
        Creature newHero = new Hero(moveTowardsSafezone(neighbors, safeZones));
        ActionEntity heroAction = new ActionEntity(this, newHero, ActionEnum.MOVE);
        return new Event(heroAction,1);
    }
}
