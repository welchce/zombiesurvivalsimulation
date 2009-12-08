package zombiesurvivalsim;

/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 * @author Ryan Cummins
 */
public class Event {
    //The maximum allowed priority in the Queue.
    static final int MAX_PRIORITY = 200000;
    //The minimum allowed priority in the Queue.
    static final int MIN_PRIORITY = 0;
    //The priority of the item.
    int _priority;
    //the char[] array for the item.
    ActionEntity _item;

    /**
     * Construtor for the item that assigns the passed values into the item array
     * and the priority.
     * @param item is the action and entity combination
     * @param priority the importance of the event to be executed
     */
    public Event(ActionEntity item, int priority) throws IndexOutOfBoundsException {
        if (priority > MAX_PRIORITY || priority < MIN_PRIORITY) {
            throw new IndexOutOfBoundsException();
        }
        _priority = priority;
        _item = item;
    }

    /**
     *
     * @return the item array.
     */
    public ActionEntity getItem() {
        return _item;
    }

    /**
     *
     * @return the priority of the event.
     */
    public int getPriority() {
        return _priority;
    }

    /**
     *
     * @return the string representation of the event.
     */
    @Override
    public String toString() {
        String eventText = "";

        eventText = "\n Event Priority: " + _priority + _item.toString();

        return eventText;
    }
}
