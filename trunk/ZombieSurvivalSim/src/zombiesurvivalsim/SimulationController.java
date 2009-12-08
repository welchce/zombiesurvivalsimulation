/*
 * The Simulation Controller is the main class of the Zombie Survival
 * Simulation.  It controls how the GUI interacts with the other classes and
 * makes the program work correctly.  Most of the functions are obvious in the
 * tasks they perform, because the code is self documenting.
 */
package zombiesurvivalsim;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Color;

/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class SimulationController implements MouseMotionListener, MouseListener {
    MainFrame _mainFrame;
    Logger _logger;
    ArrayList<Entity> _board;
    SimulationPanel _simulationPanel;
    EventQueue _simulationQueue = new EventQueue();
    int _numHumans = 0;
    int _numZombies = 0;
    int _numHumansSaved = 0;
    int _numHumansKilled = 0;
    int _numZombiesKilled = 0;
    int _numHumansConverted = 0;
    boolean _alreadyPlaying = false;
    boolean _addHumanSelected = true;
    private boolean retardButtonsDisabled = false;

    public SimulationController(MainFrame mainFrame, SimulationPanel simulationPanel,
            ArrayList<Entity> board) {
        _mainFrame = mainFrame;
        _board = board;
        _simulationPanel = simulationPanel;

        _logger = new Logger();

/**
 * This section of code adds the buttons to the GUI.
 */
        _mainFrame.addFastForwardButtonHandler(new FastForwardButtonHandler());
        _mainFrame.addHumanButtonHandler(new AddHumanButtonHandler());
        _mainFrame.addPlayPauseButtonHandler(new PlayPauseButtonHandler());
        _mainFrame.addStepButtonHandler(new StepButtonHandler());
        _mainFrame.addZombieButtonHandler(new AddZombieButtonHandler());
        _mainFrame.addResetButtonHandler(new ResetButtonHandler());

        this.generateSafeZones();
    }

    /**
     * The next section of code handles mouse events such as adding creatures
     * to the board and lighting up the point on the grid that the mouse is
     * currently on.
     */
    public void mouseExited(MouseEvent arg0) {
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseClicked(MouseEvent arg0) {
    }

    public void mouseReleased(MouseEvent arg0) {
        Point addPos = getGridPoint(arg0.getPoint());
        if (validLocation(addPos)) {
            _simulationPanel.setSelection(getGridPoint(arg0.getPoint()), Color.WHITE);
        } else {
            _simulationPanel.setSelection(getGridPoint(arg0.getPoint()), Color.RED);
        }
        _simulationPanel.repaint();
    }

    public void mouseMoved(MouseEvent arg0) {
        Point addPos = getGridPoint(arg0.getPoint());
        if (validLocation(addPos)) {
            _simulationPanel.setSelection(getGridPoint(arg0.getPoint()), Color.WHITE);
        } else {
            _simulationPanel.setSelection(getGridPoint(arg0.getPoint()), Color.RED);
        }
        _simulationPanel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        if (!_alreadyPlaying) {
            addCreature(getGridPoint(arg0.getPoint()));
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        if (!_alreadyPlaying) {
            addCreature(getGridPoint(arg0.getPoint()));
        }
    }

    private Point getGridPoint(Point mousePoint) {
        int tileWidth = (int) Math.floor(_simulationPanel.getWidth() / MainFrame.SCREEN_SIZE.width);
        int tileHeight = (int) Math.floor(_simulationPanel.getHeight() / MainFrame.SCREEN_SIZE.height);
        int x = (int) Math.floor(mousePoint.getX() / tileWidth);
        int y = (int) Math.floor(mousePoint.getY() / tileHeight);
        return new Point(x, y);
    }

    private void addCreature(Point addPos) {
        _simulationPanel.setSelection(addPos, Color.CYAN);
        if (validLocation(addPos) && !retardButtonsDisabled) {
            if (_addHumanSelected) {
                Random randy = new Random();
                switch (randy.nextInt(3)) {
                    case 0:
                        Human newHuman = new Human(addPos);
                        _board.add(newHuman);
                        break;
                    case 1:
                        Hero newHero = new Hero(addPos);
                        _board.add(newHero);
                        break;
                    case 2:
                        Coward newCoward = new Coward(addPos);
                        _board.add(newCoward);
                        break;
                }
                _mainFrame.updateNumHumans(++_numHumans);
            } else {
                _board.add(new Zombie(addPos));
                _mainFrame.updateNumZombies(++_numZombies);
            }

            while (!_simulationQueue.isEmpty()) {
                _simulationQueue.dequeue();
            }
            repopulateQueue();
        }
        _simulationPanel.repaint();
    }


// The validLocation function returns whether a point on the grid is a valid
// location.
    private boolean validLocation(Point pos) {
        if (pos.x >= MainFrame.SCREEN_SIZE.width ||
                pos.y >= MainFrame.SCREEN_SIZE.height ||
                pos.x < 0 || pos.y < 0) {
            return false;
        }

        for (Entity entity : _board) {
            if (entity.getLocation().equals(pos)) {
                return false;
            }
        }
        return true;
    }

    // FastForwardButtonHandler controls the fast forward button.
    class FastForwardButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            do {
                step();
                _mainFrame.disableRetardButtons();
                retardButtonsDisabled = true;
            } while (!_simulationQueue.isEmpty());
        }
    }

    // PlayPauseButtonHandler controls the play/pause button.
    class PlayPauseButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            _mainFrame.disableRetardButtons();
            retardButtonsDisabled = true;
            if (!_alreadyPlaying) {
                _alreadyPlaying = true;
                _simulationPanel.hideSelection();
                new Thread() {

                    @Override
                    public void run() {
                        while (_alreadyPlaying) {
                            do {
                                step();
                            } while (!_simulationQueue.isEmpty());
                            try {
                                Thread.sleep(80);
                            } catch (Exception e) {
                            }
                        }
                    }
                }.start();
            } else {
                _alreadyPlaying = false;
                _simulationPanel.showSelection();
            }
            _mainFrame.togglePlayPause(_alreadyPlaying);
        }
    }

    // StepButtonHandler controls the step button.
    class StepButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            _mainFrame.disableRetardButtons();
            retardButtonsDisabled = true;
            step();
        }
    }

    class AddZombieButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            _addHumanSelected = false;
        }
    }

    class AddHumanButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            _addHumanSelected = true;
        }
    }

    // The reset button resets the simulation to its original state.
    class ResetButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            _board.removeAll(_board);
            while (!_simulationQueue.isEmpty())
                _simulationQueue.dequeue();
            generateSafeZones();
            _mainFrame.updateNumHumans(0);
            _mainFrame.updateNumZombies(0);
            _mainFrame.updateHumansConvertedLabel(0);
            _mainFrame.updateHumansKilledLabel(0);
            _mainFrame.updateZombiesKilledLabel(0);
            _mainFrame.updateHumansSavedLabel(0);
            _simulationPanel.repaint();
            _mainFrame.enableRetardButtons();
            retardButtonsDisabled = false;
        }
    }

    // Controls the step function.
    private void step() {
        if (_simulationQueue.isEmpty()) {
            repopulateQueue();
        }
        if (!_simulationQueue.isEmpty()) {
            Event stepEvent = _simulationQueue.dequeue();
            Entity stepEntity = stepEvent.getItem().getEntity();
            try
            {
                _logger.write(stepEvent);
            }
            catch (java.io.IOException ex)
            {
                //ignore fails to write.
            }
            if (_board.contains(stepEntity)) {
                ActionEnum stepAction = stepEvent.getItem().getAction();
                switch (stepAction) {
                    case BLOCK_SAFEZONE:
                        blockSafeZone(stepEntity);
                        break;
                    case HUMAN_SAVED:
                        moveSaved(stepEntity);
                        break;
                    case ATTACK_HUMAN:
                        attackNearbyHuman(stepEntity);
                        break;
                    case MOVE_RANDOMLY:
                        moveRandomly(stepEntity);
                        break;
                    case MOVE_TO_SAFE:
                        moveTowardsEntity(stepEntity, getClosestEntity(stepEntity, EntityEnum.SAFEZONE));
                        break;
                    case CONVERT_HUMAN:
                        convertNearbyHuman(stepEntity);
                        break;
                    case ATTACK_ZOMBIE:
                        attackNearbyZombie(stepEntity);
                        break;
                    case RUSH_ZOMBIE:
                        moveTowardsEntity(stepEntity, getClosestEntity(stepEntity, EntityEnum.ZOMBIE));
                        break;
                    case RUSH_HUMAN:
                        moveTowardsEntity(stepEntity, getClosestHuman(stepEntity));
                        break;
                    case INVITE_NEIGHBORS:
                        inviteNeighbors(stepEntity);
                        break;
                }
            }
        }
        _simulationPanel.repaint();
    }

    //
    private void blockSafeZone(Entity source) {
        Entity targetZone = null;
        for (Entity safezone : _board) {
            if (safezone.getType() == EntityEnum.SAFEZONE &&
                source.getLocation().distance(safezone.getLocation()) == 1) {
                targetZone = safezone;
                break;
            }
        }
        if (targetZone != null) {
            int zombiesAroundZone=0;
            for (Entity zombie : _board) {
                if (zombie.getType() == EntityEnum.ZOMBIE &&
                    targetZone.getLocation().distance(zombie.getLocation()) == 1) {
                    if (++zombiesAroundZone >= 4) {
                        _board.remove(targetZone);
                        break;
                    }
                }
            }
        }
    }

    private void inviteNeighbors(Entity source) {
        for (Entity neighbor : _board) {
            if (neighbor.getLocation().distance(source.getLocation()) == 2) {
                Entity targetZombie = getClosestEntity(source, EntityEnum.ZOMBIE);
                moveTowardsEntity(neighbor, targetZombie);
            }
        }
    }

    private void moveSaved(Entity source) {
        for (Entity safezone : _board) {
            if (safezone.getType() == EntityEnum.SAFEZONE) {
                if (source.getLocation().distance(safezone.getLocation()) == 1) {
                    _board.remove(source);
                    _mainFrame.updateHumansSavedLabel(++_numHumansSaved);
                    _mainFrame.updateNumHumans(--_numHumans);
                    return;
                }
            }
        }
    }

    private void convertNearbyHuman(Entity source) {
        Random randy = new Random();
        if (randy.nextInt(100) < _mainFrame.getZombieConvertPer()) {
            Entity nearbyHuman = getClosestHuman(source);
            if (nearbyHuman != null) {
                _board.remove(nearbyHuman);
                _board.add(new Zombie(nearbyHuman.getLocation()));
                _mainFrame.updateHumansConvertedLabel(++_numHumansConverted);
                _mainFrame.updateNumHumans(--_numHumans);
                _mainFrame.updateNumZombies(++_numZombies);
            }
        }
    }

    private void attackNearbyZombie(Entity source) {
        Random randy = new Random();
        if (randy.nextInt(100) > _mainFrame.getZombiesWinPer()) {
            Entity nearbyZombie = getClosestEntity(source, EntityEnum.ZOMBIE);
            if (nearbyZombie.getLocation().distance(source.getLocation()) == 1) {
                _board.remove(nearbyZombie);
                _mainFrame.updateZombiesKilledLabel(++_numZombiesKilled);
                _mainFrame.updateNumHumans(--_numZombies);
            }
        }
    }

    private void attackNearbyHuman(Entity source) {
        Random randy = new Random();
        if (randy.nextInt(100) < _mainFrame.getZombiesWinPer()) {
            Entity nearbyHuman = getClosestHuman(source);
            if (nearbyHuman != null) {
                _board.remove(nearbyHuman);
                _mainFrame.updateHumansKilledLabel(++_numHumansKilled);
                _mainFrame.updateNumHumans(--_numHumans);
            }
        }
    }

    private Entity getClosestHuman(Entity source) {
        for (Entity nearbyHuman : _board) {
            if (nearbyHuman.getLocation().distance(source.getLocation()) == 1 &&
                    (nearbyHuman.getType() == EntityEnum.HUMAN ||
                    nearbyHuman.getType() == EntityEnum.COWARD ||
                    nearbyHuman.getType() == EntityEnum.HERO)) {
                return nearbyHuman;
            }
        }
        return null;
    }

    private void moveRandomly(Entity source) {
        Random randy = new Random();
        Point newLocation;

        if (getPossibleMoves(source) > 0) {
            do {
                newLocation = new Point(source.getLocation());
                switch (randy.nextInt(4)) {
                    case 0:
                        newLocation.x += 1;
                        break;
                    case 1:
                        newLocation.x -= 1;
                        break;
                    case 2:
                        newLocation.y += 1;
                        break;
                    case 3:
                        newLocation.y -= 1;
                        break;
                }
            } while (!validLocation(newLocation));
            source.setLocation(newLocation);
        }
    }

    private void moveTowardsEntity(Entity source, Entity destination) {
        Point newLocation;
        if (getPossibleMoves(source) > 0 && destination != null) {
            newLocation = new Point(source.getLocation());
            if (destination.getLocation().x > newLocation.x) {
                newLocation.x++;
            } else if (destination.getLocation().x < newLocation.x) {
                newLocation.x--;
            } else if (destination.getLocation().y > newLocation.y) {
                newLocation.y++;
            } else if (destination.getLocation().y < newLocation.y) {
                newLocation.y--;
            }
            if (validLocation(newLocation)) {
                source.setLocation(newLocation);
            } else {
                moveRandomly(source);
            }
        }
    }

    private Entity getClosestEntity(Entity source, EntityEnum searchType) {
        Entity closeEntity = null;
        for (Entity piece : _board) {
            if (piece.getType() == searchType) {
                if (closeEntity == null) {
                    closeEntity = piece;
                }
                if (piece.getLocation().distance(source.getLocation()) <
                        closeEntity.getLocation().distance(source.getLocation())) {
                    closeEntity = piece;
                }
            }
        }
        return closeEntity;
    }

    private int getPossibleMoves(Entity creature) {
        int possibleMoves = 4;
        for (Entity piece : _board) {
            if (creature.getLocation().distance(piece.getLocation()) == 1) {
                possibleMoves -= 1;
            }
        }
        if (creature.getLocation().x + 1 >= MainFrame.SCREEN_SIZE.width) {
            possibleMoves -= 1;
        }
        if (creature.getLocation().y + 1 >= MainFrame.SCREEN_SIZE.height) {
            possibleMoves -= 1;
        }
        if (creature.getLocation().x - 1 < 0) {
            possibleMoves -= 1;
        }
        if (creature.getLocation().y - 1 < 0) {
            possibleMoves -= 1;
        }
        return possibleMoves;
    }

    private void generateSafeZones() {
        Random randy = new Random();
        for (int i = 0; i < 5; i++) {
            int x, y;
            boolean badLoc;
            Point randLoc;
            do {
                badLoc = false;
                x = randy.nextInt(MainFrame.SCREEN_SIZE.width-2)+1;
                y = randy.nextInt(MainFrame.SCREEN_SIZE.height-2)+1;
                randLoc = new Point(x, y);

                for (Entity piece : _board) {
                    if (piece.getLocation().distance(randLoc) <= 1) {
                        badLoc = true;
                        break;
                    }
                }
            } while (badLoc);
            _board.add(new SafeZone(randLoc));
        }
    }

    private void repopulateQueue() {
        for (Entity piece : _board) {
            Event nextEvent = piece.getNextEvent(_board);
            if (nextEvent != null) {
                _simulationQueue.enqueue(nextEvent);
            }
        }
    }
}
