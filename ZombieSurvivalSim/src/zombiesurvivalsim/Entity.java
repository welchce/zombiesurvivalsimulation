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

    protected Point getRandomMove(ArrayList<Entity> board, EventQueue simulationQueue) {
        boolean invalidMove;
        int numMoves = 4;
        HashMap<Point,Entity> neighbors = new HashMap<Point,Entity>();
        if (getLocation().x+1 >= MainFrame.SCREEN_SIZE.width || getLocation().x-1 < 0)
            numMoves -= 1;
        if (getLocation().y+1 >= MainFrame.SCREEN_SIZE.height || getLocation().y-1 < 0)
            numMoves -= 1;
        for (Event event : simulationQueue.getEvents()) {
            Point curLocation = event.getItem().getLocation();
            if (curLocation.distance(getLocation()) == 1) {
                neighbors.put(curLocation, event.getItem().getEntity());
                numMoves -= 1;
            }
        }
         for (Entity piece : board) {
            if (piece.getLocation().distance(getLocation()) == 1 && !neighbors.containsValue(piece)
                && piece != this) {
                neighbors.put(piece.getLocation(), piece);
                numMoves -= 1;
            }
        }
        Point newLocation = new Point(getLocation());
        if (numMoves > 0) {
            do {
                Random randy = new Random();
                newLocation = new Point(getLocation());
                switch (randy.nextInt(4)) {
                    case 0:
                        newLocation.x = newLocation.x+1;
                        break;
                    case 1:
                        newLocation.x = newLocation.x-1;
                        break;
                    case 2:
                        newLocation.y = newLocation.y+1;
                        break;
                    case 3:
                        newLocation.y = newLocation.y-1;
                        break;
                }
                invalidMove = false;

                if (newLocation.x >= MainFrame.SCREEN_SIZE.width || newLocation.x < 0 ||
                    newLocation.y >= MainFrame.SCREEN_SIZE.height || newLocation.y < 0)
                    invalidMove = true;
                else if (neighbors.containsKey(newLocation)) invalidMove = true;
            } while (invalidMove);
        }

        return newLocation;
    }

    public abstract EntityEnum getType();
    public abstract Event getNextEvent(ArrayList<Entity> board, EventQueue simulationQueue);
}
