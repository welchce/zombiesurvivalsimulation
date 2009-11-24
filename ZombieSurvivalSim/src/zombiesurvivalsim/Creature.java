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
    private Point _location, _newLocation;

    /**
     * Default Constructor
     * @param location - the inital location of the creature.
     */
    public Creature(Point location) {
        setLocation(location);
        setNewLocation(location);
    }

    /**
     * this function returns the next event associated with the creature.
     * @param creatures -
     * @return -
     */
    public abstract Event getNextEvent(ArrayList<Creature> neighbors, ArrayList<SafeZone> safeZones);

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

    public void setNewLocation(Point newLocation) {
        _newLocation = newLocation;
    }

    public Point getNewLocation() {
        return _newLocation;
    }

    /**
     *
     * @return
     */
    public Point getLocation() {
        return _location;
    }

    protected Point moveTowardsSafezone(ArrayList<Creature> neighbors, ArrayList<SafeZone> safeZones) {
        return null;
    }

    /**
     *
     * @param creatures
     * @return
     */
    protected Point getRandomLocation(ArrayList<Creature> neighbors) {
        Random randy = new Random();
        Point newLoc = new Point(getLocation());
        int possibleMoves = 4-(neighbors.size());

        if (getLocation().y+1 >= MainFrame.SCREEN_SIZE.height)
            possibleMoves--;
        if (getLocation().y-1 < 0)
            possibleMoves--;
        if (getLocation().x+1 >= MainFrame.SCREEN_SIZE.width)
            possibleMoves--;
        if (getLocation().x-1 < 0)
            possibleMoves--;
        System.out.println("Possible moves: " + possibleMoves);
        if (possibleMoves > 0) {
            boolean creatureThere;
            boolean offMap;
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
                offMap = false;

                for (Creature neighbor : neighbors) {
                    if (neighbor.getLocation().equals(newLoc) || neighbor.getNewLocation().equals(newLoc)) {
                        creatureThere = true;
                        break;
                    }
                }

                if (newLoc.x >= MainFrame.SCREEN_SIZE.width || newLoc.x < 0) offMap = true;
                if (newLoc.y >= MainFrame.SCREEN_SIZE.height || newLoc.y < 0) offMap = true;

            } while (creatureThere || offMap);
        }
        setNewLocation(newLoc);
        return newLoc;
    }
}
