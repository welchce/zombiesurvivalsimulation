/* 
 * ActionEntity
 * November 15, 2009
 */

package zombiesurvivalsim;

/**
 * An ActionEntity is a set that contains all of the information necessary
 * to perform an action tied to an Eevent.
 * @author ryancummins
 */
public class ActionEntity {

    private ActionEnum _action;
    private Entity _entity;

    /**
     * Default Constructor.
     * @param creature - the creature associated with the action.
     * @param action - the action being performed
     * @param location - the location involved in the action.
     */
    public ActionEntity(ActionEnum action, Entity entity) {
        _action = action;
        _entity = entity;
    }

    /**
     * getAction() returns the action.
     * @return - the action being performed.
     */
    public ActionEnum getAction() {
        return _action;
    }

    public Entity getEntity() {
        return _entity;
    }

    public String toString()
    {
        String actionText = "";

        actionText = "\nAction: " + _action + _entity.toString();

        return actionText;
    }
}
