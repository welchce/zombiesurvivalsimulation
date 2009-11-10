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
        }
    }

    class AddHumanButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Point humanPos = getRandomUnusedPoint();
            _creatures.add(new Human(humanPos));
            _simulationPanel.repaint();
            System.out.println("Add new human ("+humanPos.x+", "+humanPos.y+")");
        }
    }


    class PlayButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    class StepButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    class ZombieButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Point zombiePos = getRandomUnusedPoint();
            _creatures.add(new Zombie(zombiePos));
            _simulationPanel.repaint();
            System.out.println("Add new zombie ("+zombiePos.x+", "+zombiePos.y+")");
        }
    }

    private Point getRandomUnusedPoint() {
        boolean creatureInSpot;
        int x, y;
        Random randy = new Random();
        do {
            creatureInSpot = false;
            x = randy.nextInt(20);
            y = randy.nextInt(20);
            for (Creature creature : _creatures) {
                if (creature.getPosition().x == x && creature.getPosition().y == y) {
                    creatureInSpot = true;
                    break;
                }
            }
        } while (creatureInSpot);
        return new Point(x,y);
    }
}
