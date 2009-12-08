package zombiesurvivalsim;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * A Human is a type of entity.  It will attempt to get the nearest safe zone
 * and will either attack zombies it encounters or flee from the zombie.
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class Human extends Entity {

    /**
     * Default Constructor
     * @param position the initial position of the Human.
     */
    public Human(Point position) {
        super(position);
    }

    /**
     * @return Human entity type
     */
    @Override
    public EntityEnum getType() {
        return EntityEnum.HUMAN;
    }

    /**
     * @return the next event associated with Human.
     */
    @Override
    public Event getNextEvent(ArrayList<Entity> board) {
        Random randy = new Random();
        Event event = new Event(new ActionEntity(ActionEnum.MOVE_TO_SAFE, this), 3);

        for (Entity piece : board) {
            if (piece.getLocation().distance(getLocation()) == 1) {
                if (piece.getType() == EntityEnum.SAFEZONE) {
                    event = new Event(new ActionEntity(ActionEnum.HUMAN_SAVED, this), 2);
                    break;
                }

                if (piece.getType() == EntityEnum.ZOMBIE) {
                    if (randy.nextInt(2) == 1) {
                        event = new Event(new ActionEntity(ActionEnum.INVITE_NEIGHBORS, this), 2);
                    } else if (randy.nextInt(2) == 1) {
                        event = new Event(new ActionEntity(ActionEnum.ATTACK_ZOMBIE, this), 2);
                    }
                    break;
                }
            }
        }
        return event;
    }
}
