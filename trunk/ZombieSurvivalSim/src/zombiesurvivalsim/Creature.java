/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


/**
 * Creature represents a creature in our simulation.  It takes up a space on
 * the board and has knowledge of its next location.
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public abstract class Creature {
    private Point _location;
    private Point _nextLocation;

    /**
     * Default Constructor
     * @param location - the inital location of the creature.
     */
    public Creature(Point location) {
        setLocation(location);
        setNextLocation(location);
    }

    /**
     * this function returns the next event associated with the creature.
     * @param creatures -
     * @return -
     */
    public abstract Event getNextEvent(ArrayList<Creature> creatures);

    /**
     *
     * @return - the type of Creature.
     */
    public abstract CreatureEnum getType();

    /**
     *
     * @param newLocation - the new location of the Creature.
     */
    public void setLocation(Point newLocation) {
        _location = newLocation;
    }

    /**
     *
     * @param newNextLocation
     */
    protected void setNextLocation(Point newNextLocation) {
        _nextLocation = newNextLocation;
    }
    /**
     *
     * @return
     */
    public Point getLocation() {
        return _location;
    }

    /**
     *
     * @return
     */
    public Point getNextLocation() {
        return _nextLocation;
    }

    /**
     *
     * @param creatures
     * @return
     */
    protected Point getRandomLocation(ArrayList<Creature> creatures) {
        Random randy = new Random();
        Point newLoc = new Point(getLocation());

        ArrayList<Point> neighbors = getNeighborLocations(creatures);
        if (neighbors.size() < 4) {
            boolean creatureThere;
            do {
                newLoc = new Point(getLocation());
                switch (randy.nextInt(4)) {
                    case 0:
                        newLoc.x += 1;
                        break;
                    case 1:
                        newLoc.y += 1;
                        break;
                    case 2:
                        newLoc.x -= 1;
                        break;
                    case 3:
                        newLoc.y -= 1;
                        break;
                }
                creatureThere = false;

                System.out.println("Move To Location: " + newLoc);
                for (Point neighborLocation : neighbors) {
                    //System.out.println("Neighbor position: " + neighbor.getLocation());
                    if (neighborLocation.x == newLoc.x && neighborLocation.y == newLoc.y) {
                        creatureThere = true;
                        //System.out.println("There is a creature at new location.");
                        break;
                    }
                }
            } while (creatureThere);
            setNextLocation(newLoc);
        }
        
        return newLoc;
    }

    /**
     *
     * @param creatures
     * @return
     */
    protected ArrayList<Point> getNeighborLocations(ArrayList<Creature> creatures) {
        ArrayList<Point> neighborLocations = new ArrayList();
        for (Creature neighbor : creatures) {
            if (neighbor != this) {
                if (neighbor.getLocation().distance(this.getLocation()) <= 1) {
                    neighborLocations.add(neighbor.getLocation());
                }
                if (!neighbor.getLocation().equals(neighbor.getNextLocation()) &&
                    neighbor.getNextLocation().distance(this.getLocation()) <= 1) {
                    neighborLocations.add(neighbor.getNextLocation());
                }
            }
        }
        return neighborLocations;
    }
}
