/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// 3% of the board safe zones

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
    ArrayList<Entity> _board;
    SimulationPanel _simulationPanel;
    EventQueue _simulationQueue = new EventQueue();
    int _numHumans = 0;
    int _numZombies = 0;
    int _numHumansSaved = 0;
    int _numHumansKilled = 0;
    boolean _alreadyPlaying = false;
    boolean _addHumanSelected = true;

    public SimulationController(MainFrame mainFrame, SimulationPanel simulationPanel,
                                ArrayList<Entity> board) {
        _mainFrame = mainFrame;
        _board = board;
        _simulationPanel = simulationPanel;

        _mainFrame.addFastForwardButtonHandler(new FastForwardButtonHandler());
        _mainFrame.addHumanButtonHandler(new AddHumanButtonHandler());
        _mainFrame.addPlayPauseButtonHandler(new PlayPauseButtonHandler());
        _mainFrame.addStepButtonHandler(new StepButtonHandler());
        _mainFrame.addZombieButtonHandler(new AddZombieButtonHandler());

        this.initSafeZones();
    }
    public void mouseExited(MouseEvent arg0) { }
    public void mouseEntered(MouseEvent arg0) { }
    public void mouseClicked(MouseEvent arg0) { }
    public void mouseReleased(MouseEvent arg0) {
        Point addPos = getGridPoint(arg0.getPoint());
        if (validAddPosition(addPos))
            _simulationPanel.setSelection(getGridPoint(arg0.getPoint()), Color.WHITE);
        else
            _simulationPanel.setSelection(getGridPoint(arg0.getPoint()), Color.RED);
        _simulationPanel.repaint();
    }
    public void mouseMoved(MouseEvent arg0) {
        Point addPos = getGridPoint(arg0.getPoint());
        if (validAddPosition(addPos))
            _simulationPanel.setSelection(getGridPoint(arg0.getPoint()), Color.WHITE);
        else
            _simulationPanel.setSelection(getGridPoint(arg0.getPoint()), Color.RED);
        _simulationPanel.repaint();
    }
    @Override
    public void mouseDragged(MouseEvent arg0) {
        if (!_alreadyPlaying) addCreature(getGridPoint(arg0.getPoint()));
    }
    @Override
    public void mousePressed(MouseEvent arg0) {
        if (!_alreadyPlaying) addCreature(getGridPoint(arg0.getPoint()));
    }
    private Point getGridPoint(Point mousePoint) {
        int tileWidth = (int)Math.floor(_simulationPanel.getWidth()/MainFrame.SCREEN_SIZE.width);
        int tileHeight = (int)Math.floor(_simulationPanel.getHeight()/MainFrame.SCREEN_SIZE.height);
        int x=(int)Math.floor(mousePoint.getX()/tileWidth);
        int y=(int)Math.floor(mousePoint.getY()/tileHeight);
        return new Point(x,y);
    }
    private void addCreature(Point addPos) {
        _simulationPanel.setSelection(addPos, Color.CYAN);
        if (validAddPosition(addPos)) {
            while(!_simulationQueue.isEmpty()) {
                _simulationQueue.dequeue();
            }
            repopulateQueue();
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
        }
        _simulationPanel.repaint();
    }

    private boolean validAddPosition(Point pos) {
      if (pos.x >= MainFrame.SCREEN_SIZE.width ||
            pos.y >= MainFrame.SCREEN_SIZE.height ||
            pos.x < 0 || pos.y < 0) return false;

        for (Entity entity : _board) {
            if (entity.getLocation().equals(pos)) {
                return false;
            }
        }
        return true;
    }
    
    class FastForwardButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            do {
                step();
            } while(!_simulationQueue.isEmpty());
        }
    }
    
    class PlayPauseButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!_alreadyPlaying) {
                _alreadyPlaying = true;
                _simulationPanel.hideSelection();
                new Thread() {
                    @Override
                    public void run() {
                        while (_alreadyPlaying) {
                            do {
                                step();
                            } while(!_simulationQueue.isEmpty());
                            try { Thread.sleep(80);
                            } catch (Exception e) { }
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

    class StepButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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

    private void step() {
        if (_simulationQueue.isEmpty()) repopulateQueue();
        Event stepEvent = _simulationQueue.dequeue();
        Entity stepEntity = stepEvent.getItem().getEntity();
        if (stepEntity != null) {
            Point newLocation = stepEvent.getItem().getLocation();
            stepEntity.setLocation(newLocation);
            _simulationPanel.repaint();
        }
    }

    private void initSafeZones() {
        Random randy = new Random();
        for (int i=0; i<5; i++) {
            int x, y;
            boolean badLoc;
            Point randLoc;
            do {
                badLoc = false;
                x = randy.nextInt(MainFrame.SCREEN_SIZE.width);
                y = randy.nextInt(MainFrame.SCREEN_SIZE.height);
                randLoc = new Point(x,y);

                for (Entity piece : _board) {
                    if (piece.getLocation().equals(randLoc)) {
                        badLoc = true;
                        break;
                    }
                }
            } while(badLoc);
            _board.add(new SafeZone(randLoc));
        }
    }

    private void repopulateQueue() {
        for (Entity piece : _board) {
            Event nextEvent = piece.getNextEvent(_board, _simulationQueue);
            if (nextEvent != null) {
                _simulationQueue.enqueue(nextEvent);
            }
        }
    }
}
