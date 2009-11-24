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
    private Creature _oldCreature;
    private Creature _newCreature;

    /**
     * Default Constructor.
     * @param creature - the creature associated with the action.
     * @param action - the action being performed
     * @param location - the location involved in the action.
     */
    public ActionEntity(Creature oldCreature, Creature newCreature, ActionEnum action) {
        _oldCreature = oldCreature;
        _newCreature = newCreature;
        _action = action;
    }

    /**
     * getAction() returns the action.
     * @return - the action being performed.
     */
    public ActionEnum getAction() {
        return _action;
    }

    /**
     * getOldCreature() returns the creature performing the action.
     * @return - the creature.
     */
    public Creature getOldCreature() {
        return _oldCreature;
    }

    /**
     * getNewCreature() returns the new creature generated from the action.
     * @return - the creature.
     */
    public Creature getNewCreature() {
        return _newCreature;
    }
}
