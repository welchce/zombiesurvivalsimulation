/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class SimulationController {
    MainFrame _mainFrame;
    ArrayList<Creature> _creatures;
    SimulationPanel _simulationPanel;
    EventQueue _simulationQueue = new EventQueue();
    int _numHumans = 0;
    int _numZombies = 0;

    public SimulationController(MainFrame mainFrame, SimulationPanel simulationPanel,
                                ArrayList<Creature> creatures) {
        _mainFrame = mainFrame;
        _creatures = creatures;
        _simulationPanel = simulationPanel;
        
        _mainFrame.addFastForwardButtonHandler(new FastForwardButtonHandler());
        _mainFrame.addHumanButtonHandler(new AddHumanButtonHandler());
        _mainFrame.addPlayButtonHandler(new PlayButtonHandler());
        _mainFrame.addStepButtonHandler(new StepButtonHandler());
        _mainFrame.addZombieButtonHandler(new ZombieButtonHandler());
    }

    class FastForwardButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            while(!_simulationQueue.isEmpty()) {
                step();
            }
            step();
        }
    }

    class AddHumanButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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
        }
    }


    class PlayButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new Thread() {
                public void run() {
                    while (true) {
                        step();
                        try {
                            Thread.sleep(20);
                        } catch (Exception e) {
                            
                        }
                    }
                }
            }.start();
        }
    }

    class StepButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            step();
        }
    }

    class ZombieButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Point zombiePos = getRandomUnusedLocation();
            Zombie newZombie = new Zombie(zombiePos);
            _creatures.add(newZombie);
            _simulationPanel.repaint();
            _mainFrame.updateNumZombies(++_numZombies);
            System.out.println("Add new zombie ("+zombiePos.x+", "+zombiePos.y+")");
        }
    }

    private void step() {
        if (!_simulationQueue.isEmpty()) {
            Event stepEvent = _simulationQueue.dequeue();
            ActionEntity stepAction = stepEvent.getItem();
            Creature stepCreature = stepAction.getCreature();
            
            switch (stepAction.getAction()) {
                case MOVE:
                    Point moveLoc = stepAction.getActionLocation();
                    if (moveLoc.x > -1 && moveLoc.x < MainFrame.SCREEN_SIZE.width &&
                        moveLoc.y > -1 && moveLoc.y < MainFrame.SCREEN_SIZE.height)
                            stepCreature.setLocation(moveLoc);
                    break;
                case ATTACK:
                    break;
            }
            _simulationPanel.repaint();

        } else {
            System.out.println("Repopulating queue...");
            for (Creature creature : _creatures) {
                _simulationQueue.enqueue(creature.getNextEvent(_creatures));
            }
            step();
        }
    }

    // Loops forever if all spaces are filled
    private Point getRandomUnusedLocation() {
        boolean creatureInSpot;
        int x, y;
        Random randy = new Random();
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
        } while (creatureInSpot);
        return new Point(x,y);
    }
}
