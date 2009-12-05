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
public class SafeZone extends Entity {
    public SafeZone(Point location) {
        super(location);
    }
    @Override
    public EntityEnum getType() { return EntityEnum.SAFEZONE; }
    @Override
    public Event getNextEvent(ArrayList<Entity> board, EventQueue simulationQueue) {
        return null;
    }
}
