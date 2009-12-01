/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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

    /*
     *Construtor for the item that assigns the passed values into the item array
     * and the priority.
     */
    public Event(ActionEntity item, int priority) throws IndexOutOfBoundsException {
        if (priority > MAX_PRIORITY || priority < MIN_PRIORITY)
            throw new IndexOutOfBoundsException();
        _priority = priority;
        _item = item;
    }
    /**
     *
     * @return the item array.
     */
    public ActionEntity getItem() { return _item; }
    /**
     *
     * @return the priority of the Event.
     */
    public int getPriority() { return _priority; }
}
