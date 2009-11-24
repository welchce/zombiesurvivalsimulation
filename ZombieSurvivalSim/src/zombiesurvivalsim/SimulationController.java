/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// 3% of the board safe zones

package zombiesurvivalsim;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Point;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class SimulationController {
    MainFrame _mainFrame;
    ArrayList<Creature> _creatures;
    ArrayList<SafeZone> _safeZones;
    SimulationPanel _simulationPanel;
    EventQueue _simulationQueue = new EventQueue();
    int _numHumans = 0;
    int _numZombies = 0;
    boolean _alreadyPlaying = false;

    public SimulationController(MainFrame mainFrame, SimulationPanel simulationPanel,
                                ArrayList<Creature> creatures, ArrayList<SafeZone> safeZones) {
        _mainFrame = mainFrame;
        _creatures = creatures;
        _safeZones = safeZones;
        _simulationPanel = simulationPanel;

        _mainFrame.addFastForwardButtonHandler(new FastForwardButtonHandler());
        _mainFrame.addHumanButtonHandler(new AddHumanButtonHandler());
        _mainFrame.addPlayPauseButtonHandler(new PlayPauseButtonHandler());
        _mainFrame.addStepButtonHandler(new StepButtonHandler());
        _mainFrame.addZombieButtonHandler(new AddZombieButtonHandler());

        this.initSafeZones();
    }

    class FastForwardButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            do {
                step();
            } while(!_simulationQueue.isEmpty());
        }
    }

    class AddHumanButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                Point humanPos = getRandomUnusedLocation();
                Random randy = new Random();
                switch (randy.nextInt(3)) {
                    case 0:
                        Human newHuman = new Human(humanPos);
                        _creatures.add(newHuman);
                        break;
                    case 1:
                        Hero newHero = new Hero(humanPos);
                        _creatures.add(newHero);
                        break;
                    case 2:
                        Coward newCoward = new Coward(humanPos);
                        _creatures.add(newCoward);
                        break;
                }
                _simulationPanel.repaint();
                _mainFrame.updateNumHumans(++_numHumans);

                System.out.println("Add new human ("+humanPos.x+", "+humanPos.y+")");
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, "The map is full, you cannot add any more creatures",
                                              "Map is full", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class PlayPauseButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!_alreadyPlaying) {
                _alreadyPlaying = true;
                new Thread() {
                    public void run() {
                        while (_alreadyPlaying) {
                            step();
                            try { Thread.sleep(10);
                            } catch (Exception e) { }
                        }
                    }
                }.start();
            } else {
                _alreadyPlaying = false;
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
            try {
                Point zombiePos = getRandomUnusedLocation();
                Zombie newZombie = new Zombie(zombiePos);
                _creatures.add(newZombie);
                _simulationPanel.repaint();
                _mainFrame.updateNumZombies(++_numZombies);
                System.out.println("Add new zombie ("+zombiePos.x+", "+zombiePos.y+")");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "The map is full, you cannot add any more creatures",
                                              "Map is full", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void step() {
        if (_simulationQueue.isEmpty()) repopulateQueue();
        ActionEntity curItem = _simulationQueue.dequeue().getItem();
        Creature oldCreature = curItem.getOldCreature();
        Creature newCreature = curItem.getNewCreature();
        _creatures.set(_creatures.indexOf(oldCreature), newCreature);
        _simulationPanel.repaint();
    }

    private void initSafeZones() {
        Random randy = new Random();
        boolean safezoneInSpot;
        for (int i=0; i<5; i++) {
            int x;
            int y;
            do {
                x = randy.nextInt(MainFrame.SCREEN_SIZE.width);
                y = randy.nextInt(MainFrame.SCREEN_SIZE.height);

                safezoneInSpot = false;
                for (SafeZone safezone : _safeZones) {
                    if (safezone.getLocation().x == x && safezone.getLocation().y == y) {
                        safezoneInSpot = true;
                        break;
                    }
                }
            } while (safezoneInSpot);
            _safeZones.add(new SafeZone(new Point(x,y)));
        }
    }

    private void repopulateQueue() {
        for(Creature creature : _creatures) {
            ArrayList<Creature> neighbors = getNeighbors(creature);
            _simulationQueue.enqueue(creature.getNextEvent(neighbors, _safeZones));
        }
    }

    private ArrayList<Creature> getNeighbors(Creature creature) {
        ArrayList<Creature> neighbors = new ArrayList<Creature>();
        for (Creature neighbor : _creatures) {
            if (neighbor.getLocation().distance(creature.getLocation()) <= 1)
                neighbors.add(neighbor);
        }

        for (Event event : _simulationQueue.getEvents()) {
            Creature futureNeighbor = event.getItem().getNewCreature();
            Creature oldNeighbor = event.getItem().getOldCreature();
            if (futureNeighbor.getLocation().distance(creature.getLocation()) <= 1 && !oldNeighbor.getLocation().equals(futureNeighbor.getLocation()))
                neighbors.add(futureNeighbor);
        }
        return neighbors;
    }
    
    private Point getRandomUnusedLocation() throws Exception {
        boolean creatureInSpot;
        int x, y;
        Random randy = new Random();
        if (_creatures.size() >= (MainFrame.SCREEN_SIZE.width*MainFrame.SCREEN_SIZE.height - _safeZones.size()))
            throw new Exception();
        do {
            creatureInSpot = false;
            x = randy.nextInt(MainFrame.SCREEN_SIZE.width);
            y = randy.nextInt(MainFrame.SCREEN_SIZE.height);
            for (Creature creature : _creatures) {
                if (creature.getLocation().x == x && creature.getLocation().y == y) {
                    creatureInSpot = true;
                    break;
                }
            }
           for (SafeZone safezone : _safeZones) {
                if (safezone.getLocation().x == x && safezone.getLocation().y == y) {
                    creatureInSpot = true;
                    break;
                }
           }
        } while (creatureInSpot);
        return new Point(x,y);
    }
}
