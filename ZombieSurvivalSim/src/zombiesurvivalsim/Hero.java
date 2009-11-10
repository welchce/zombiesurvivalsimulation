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
public class Hero extends Creature {
    public Hero(Point position) {
        super(position);
    }
    @Override
    public CreatureEnum getType() { return CreatureEnum.HERO; }
    @Override
    public Event getNextEvent() {
        return null;
    }
}
