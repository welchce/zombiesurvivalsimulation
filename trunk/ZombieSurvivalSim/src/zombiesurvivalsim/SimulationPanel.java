/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;

import java.util.ArrayList;
/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class SimulationPanel extends JPanel {
    Image _kennyImage, _zombieImage;
    ArrayList<Creature> _creatures;

    public SimulationPanel(ArrayList<Creature> creatures) {
        _creatures = creatures;
        try {
            _kennyImage = ImageIO.read(this.getClass().getResource("images/Human.gif"));
            _zombieImage = ImageIO.read(this.getClass().getResource("images/Zombie.gif"));
        } catch (java.lang.Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (Creature creature : _creatures) {
            switch (creature.getType()) {
                case ZOMBIE:
                    g.drawImage(_zombieImage, creature.getPosition().x*24, creature.getPosition().y*24, null);
                    break;
                case HUMAN:
                    g.drawImage(_kennyImage, creature.getPosition().x*24, creature.getPosition().y*24, null);
                    break;
            }
        }
    }
}
