package zombiesurvivalsim;

/**
 * An ActionEntity is a set that contains all of the information necessary
 * to perform an action tied to an Eevent.
 * @author Ryan Cummins
 */
public class ActionEntity {

    private ActionEnum _action;
    private Entity _entity;

    /**
     * Default Constructor.
     * @param creature associated with the action.
     * @param action being performed
     * @param location involved in the action.
     */
    public ActionEntity(ActionEnum action, Entity entity) {
        _action = action;
        _entity = entity;
    }

    /**
     * @return action being performed.
     */
    public ActionEnum getAction() {
        return _action;
    }

    /**
     * @return entity associated with the action.
     */
    public Entity getEntity() {
        return _entity;
    }

    /**
     * @return string representation of the action entity.
     */
    @Override
    public String toString() {
        String actionText = "";

        actionText = "\nAction: " + _action + _entity.toString();

        return actionText;
    }
}
