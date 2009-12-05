/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiesurvivalsim;

import java.awt.Point;
import java.awt.Image;
import java.util.HashMap;
import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public abstract class Entity {

    private Point _location;
    private static final HashMap<EntityEnum, Image> images = new HashMap<EntityEnum, Image>();
    private static boolean imagesLoaded = false;

    public Entity(Point location) {
        setLocation(location);
        if (!imagesLoaded) {
            try {
                images.put(EntityEnum.HUMAN, readImage("images/Human.gif"));
                images.put(EntityEnum.ZOMBIE, readImage("images/Zombie.gif"));
                images.put(EntityEnum.COWARD, readImage("images/Coward.gif"));
                images.put(EntityEnum.HERO, readImage("images/Hero.gif"));
                images.put(EntityEnum.SAFEZONE, readImage("images/SafeZone.gif"));
                imagesLoaded = true;
            } catch(java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Image readImage(String path) throws java.io.IOException {
        return ImageIO.read(this.getClass().getResource(path));
    }

    public void setLocation(Point newLocation) {
        _location = newLocation;
    }

    public Point getLocation() {
        return _location;
    }

    public Image getImage() {
        return images.get(getType());
    }

    public abstract EntityEnum getType();
    public abstract Event getNextEvent(ArrayList<Entity> board);
}
