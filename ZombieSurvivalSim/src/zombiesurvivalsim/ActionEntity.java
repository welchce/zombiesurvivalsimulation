/* 
 * ActionEntity
 * November 15, 2009
 */

package zombiesurvivalsim;

import java.io.Serializable;
import java.awt.Point;

/**
 * An ActionEntity is a set that contains all of the information nessisary
 * to perform an action tied to an Eevent.
 * @author ryancummins
 */
public class ActionEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    private ActionEnum _action;
    private Point _location;
    private Creature _creature;

    /**
     * Default Constructor.
     * @param creature - the creature associated with the action.
     * @param action - the action being performed
     * @param location - the location involved in the action.
     */
    public ActionEntity(Creature creature, ActionEnum action, Point location) {
        _creature = creature;
        _action = action;
        _location = location;
    }

    /**
     * getActionLocation() returns the location of the action.
     * @return - the location of the associated action.
     */
    public Point getActionLocation() {
        return _location;
    }

    /**
     * getAction() returns the action.
     * @return - the action being performed.
     */
    public ActionEnum getAction() {
        return _action;
    }

    /**
     * getCreature() returns the creature performing the action.
     * @return - the creature.
     */
    public Creature getCreature() {
        return _creature;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (_creature != null ? _creature.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof ActionEntity)) {
            return false;
        }
        ActionEntity other = (ActionEntity) object;
        if ((this._creature == null && other._creature != null) || (this._creature != null && !this._creature.equals(other._creature))) {
            return false;
        }
        return true;
    }

    /**
     * returns a string that represents to action being performed.
     * @return - a string representation of the action and associated
     * information.
     */
    @Override
    public String toString() {
        return "" + _creature + "]";
    }

}
