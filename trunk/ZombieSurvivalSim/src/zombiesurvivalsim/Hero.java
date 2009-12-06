/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * A Hero is a type of Creature. It attempts to get ot the nearest safe zone
 * but will attack zombies that it encounters in the process.
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class Hero extends Entity {

    /**
     * Default Constructor
     * @param position - the initial position of the Hero.
     */
    public Hero(Point position) {
        super(position);
    }

    /**
     * returns the Hero Creature type.
     * @return - EntityEnum.Hero
     */
    @Override
    public EntityEnum getType() { return EntityEnum.HERO; }

    /**
     * gets the event associated with the Hero.
     * @return - the events.
     */
    @Override
    public Event getNextEvent(ArrayList<Entity> board) {
        Random randy = new Random();
        Event event=new Event(new ActionEntity(ActionEnum.MOVE_RANDOMLY, this), 3);

        for (Entity piece : board) {
            if (piece.getLocation().distance(getLocation()) == 1) {
                if (piece.getType() == EntityEnum.SAFEZONE) {
                    event=new Event(new ActionEntity(ActionEnum.HUMAN_SAVED, this), 2);
                    break;
                }
                if (piece.getType() == EntityEnum.ZOMBIE) {
                    if (randy.nextInt(2) == 1) {
                        event=new Event(new ActionEntity(ActionEnum.INVITE_NEIGHBORS, this), 2);
                    } else {
                        event=new Event(new ActionEntity(ActionEnum.ATTACK_ZOMBIE, this), 2);
                    }
                    break;
                }
            }

            if (piece.getLocation().distance(getLocation()) == 2) {
                if (piece.getType() == EntityEnum.ZOMBIE && randy.nextInt(2) == 1) {
                    event=new Event(new ActionEntity(ActionEnum.RUSH_ZOMBIE, this), 2);
                    break;
                }
            }
        }
        return event;
    }
}
