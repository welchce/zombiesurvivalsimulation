/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import java.awt.Point;

/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class Coward extends Creature {
    public Coward(Point position) {
        super(position);
    }
    @Override
    public CreatureEnum getType() { return CreatureEnum.COWARD; }
    @Override
    public Event getNextEvent() {
        return null;
    }
}
