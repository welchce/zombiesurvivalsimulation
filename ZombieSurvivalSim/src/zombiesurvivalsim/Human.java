/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class Human extends Creature {
    public Human(Point position) {
        super(position);
    }
    @Override
    public CreatureEnum getType() { return CreatureEnum.HUMAN; }
    @Override
    public Event getNextEvent(ArrayList<Creature> creatures) {
        Point newLoc = getRandomLocation(creatures);
        ActionEntity humanAction = new ActionEntity(this, ActionEnum.MOVE, newLoc);
        return new Event(humanAction,1);
    }
}
