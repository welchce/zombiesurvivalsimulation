
package zombiesurvivalsim;
import java.util.ArrayList;

/**
 * @author Raymond Cox <rj.cox101 at gmail.com>
 * @author Ryan Cummins
 *
 * The Priority Queue class is an array organized into a heap.  It has
 * items in the array that have a character array and a priority value.
 */
public class EventQueue {
    ArrayList<Event> _items = new ArrayList();

    /**
     * The enqueue function takes a given Event item and places it in
     * the last position in the array and then bubbles the item up the Priority
     * Queue until it has reached the proper position.
     */
    public void enqueue(Event item) {
        _items.add(item);
        bubbleUp(item);
    }

    /**
     * The bubbleDown function is used exclusively by the EventQueue class
     * and takes a Event and it compares the item to both of its
     * children and if the given item has a priority value higher than one of
     * its children it will swap the item with the child and repeat the process
     * until it does not have a a priority that exceeds one of its children.
     */
    private void bubbleDown(Event item) {
        int child1 = _items.indexOf(item)*2+1;
        int child2 = _items.indexOf(item)*2+2;
        if (child1 < _items.size() && child1 >= 0 && child2 < _items.size() && child2 >= 0) {
            if (_items.get(child1).getPriority() < _items.get(child2).getPriority()) {
                if (_items.get(child1).getPriority() < item.getPriority()) {
                    swap(_items.get(child1), item);
                    bubbleDown(item);
                }
            } else if (_items.get(child2).getPriority() < item.getPriority()) {
                    swap(_items.get(child2), item);
                    bubbleDown(item);
                }
        } else if (child1 < _items.size() && child1 >= 0) {
             if (_items.get(child1).getPriority() < item.getPriority()) {
                    swap(_items.get(child1), item);
                    bubbleDown(item);
                }
        }
    }

/**
 * The bubbleUp function is analogous to the bubbleDown function except it
 * works in reverse.
 */
    private void bubbleUp(Event item) {

        if (_items.indexOf(item) != 0) {
            // index is even
            if (_items.indexOf(item)%2 == 0) {
                Event parent = _items.get((_items.indexOf(item)-2)/2);
                if (parent.getPriority() > item.getPriority()) {
                    swap(parent, item);
                    bubbleUp(item);
                }
            // index is odd
            } else {
                Event parent = _items.get((_items.indexOf(item)-1)/2);
                if (parent.getPriority() > item.getPriority()) {
                    swap(parent, item);
                    bubbleUp(item);
                }
            }
        }
    }

    /**
     * The getSize function returns the size of the Priority Queue.
     */
    public int getSize() { return _items.size(); }

    /**
     * The isEmpty function returns true if the Priority Queue is empty,
     * otherwise it will return false.
     */
    public boolean isEmpty() { return _items.isEmpty(); }

    /**
     * The swap function takes 2 Event items and swaps them within the
     * EventQueue array.
     */
    private void swap(Event src, Event dest) {
        int temp = _items.indexOf(src);
        _items.set(_items.indexOf(dest), src);
        _items.set(temp, dest);
    }

    /**
     * The dequeue function removes and returns the item on the bottom of the
     * queue and then updates the queue.
     */
    public Event dequeue() {
        swap(_items.get(0), _items.get(_items.size()-1));
        Event item = _items.remove(_items.size()-1);
        if (!isEmpty()) bubbleDown(_items.get(0));
        return item;
    }
}
