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
public class SafeZone {
    Point _location;
    public SafeZone(Point location) {
        _location = location;
    }

    public Point getLocation() {
        return _location;
    }
}
