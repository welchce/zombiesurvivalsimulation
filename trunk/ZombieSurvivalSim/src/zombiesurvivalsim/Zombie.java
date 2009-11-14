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
public class Zombie extends Creature {
    public Zombie(Point position) {
        super(position);
    }
    @Override
    public CreatureEnum getType() { return CreatureEnum.ZOMBIE; }
    @Override
    public Event getNextEvent(ArrayList<Creature> creatures) {
        Point newLoc = getRandomLocation(creatures);
        ActionEntity humanAction = new ActionEntity(this, ActionEnum.MOVE, newLoc);
        return new Event(humanAction,2);
    }
}
