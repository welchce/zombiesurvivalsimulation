package zombiesurvivalsim;

import java.awt.Point;
import java.awt.Image;
import java.util.HashMap;
import javax.imageio.ImageIO;

import java.util.ArrayList;

/**
 * The Entity class includes all the types of humans, zombies, and safe zones,
 * and how they interact.
 * @author Raymond Cox <rj.cox101 at gmail.com>
 * @author Ryan Cummins
 */
public abstract class Entity {

    private Point _location;
    private static final HashMap<EntityEnum, Image> images = new HashMap<EntityEnum, Image>();
    private static boolean imagesLoaded = false;

    /**
     * Default constructer loads images if they are not loaded and sets the entity position
     * @param location the position of the entity on the board
     */
    public Entity(Point location) {
        setLocation(location);
        if (!imagesLoaded) {
            try {
                images.put(EntityEnum.HUMAN, readImage("images/Human.gif"));
                images.put(EntityEnum.ZOMBIE, readImage("images/Zombie.gif"));
                images.put(EntityEnum.COWARD, readImage("images/Coward.gif"));
                images.put(EntityEnum.HERO, readImage("images/Hero.gif"));
                images.put(EntityEnum.SAFEZONE, readImage("images/Safezone.gif"));
                imagesLoaded = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Image readImage(String path) throws java.io.IOException {
        return ImageIO.read(this.getClass().getResource(path));
    }

    /**
     * @param newLocation new entity location
     */
    public void setLocation(Point newLocation) {
        _location = newLocation;
    }

    /**
     * @return the location of the entity
     */
    public Point getLocation() {
        return _location;
    }

    /**
     * @return entity image
     */
    public Image getImage() {
        return images.get(getType());
    }

    /**
     * @return the type of the entity (coward, human, hero, zombie)
     */
    public abstract EntityEnum getType();

    /**
     * @param the current simulation board
     * @return the next entity event
     */
    public abstract Event getNextEvent(ArrayList<Entity> board);

    /**
     * @return string representation of the entity
     */
    @Override
    public String toString() {
        String entityText = "";

        entityText = "\nEntity Type: " + getType() + "  Location: " + getLocation();

        return entityText;
    }
}
