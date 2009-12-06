/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * A Zombie is a type of Creature. It wanders around randomly, and will attack
 * nearby non-Zombies and Safe zones.
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class Zombie extends Entity {


    /**
     * Default Constructor
     * @param position - the initial position of the Zombie.
     */
    public Zombie(Point position) {
        super(position);
    }

    /**
     * returns the Creature type Zombie.
     * @return - EntityEnum.ZOMBIE
     */
    @Override
    public EntityEnum getType() { return EntityEnum.ZOMBIE; }

    /**
     * gets the events associated with the Zombie.
     * @param creatures
     * @return - the next event.
     */

    @Override
    public Event getNextEvent(ArrayList<Entity> board) {
        Random randy = new Random();
        Event event=new Event(new ActionEntity(ActionEnum.MOVE_RANDOMLY, this), 2);

        for (Entity piece : board) {
            if (piece.getLocation().distance(getLocation()) <= 1) {
                if (piece.getType() == EntityEnum.SAFEZONE) {
                    event=new Event(new ActionEntity(ActionEnum.BLOCK_SAFEZONE, this), 1);
                    break;
                }
            }
            if (piece.getType() == EntityEnum.HUMAN ||
                piece.getType() == EntityEnum.COWARD ||
                piece.getType() == EntityEnum.HERO) {
                if (piece.getLocation().distance(getLocation()) <= 1) {
                    if (randy.nextInt(2) == 1) {
                        event=new Event(new ActionEntity(ActionEnum.ATTACK_HUMAN, this), 1);
                    } else {
                        event=new Event(new ActionEntity(ActionEnum.CONVERT_HUMAN, this), 1);
                    }
                }

                if (piece.getLocation().distance(getLocation()) == 2) {
                    event=new Event(new ActionEntity(ActionEnum.RUSH_HUMAN, this), 1);
                    break;
                }
            }
        }
        return event;
    }
}
