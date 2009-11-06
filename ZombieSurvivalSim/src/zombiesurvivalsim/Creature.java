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
public abstract class Creature {
    private Point _position;
    
    public abstract Event getNextEvent();
    public Point getPosition() {
        return _position;
    }

}