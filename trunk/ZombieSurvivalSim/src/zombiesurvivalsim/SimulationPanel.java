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
/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class SimulationPanel extends JPanel {
    Image _kennyImage, _zombieImage;

    public SimulationPanel() {
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
        // Fun with zombies
        g.drawImage(_zombieImage, getWidth()/2, getHeight()/2+24, this);
        g.drawImage(_kennyImage, getWidth()/2, getHeight()/2, this);
        g.drawImage(_zombieImage, getWidth()/2+24, getHeight()/2, this);
        g.drawImage(_zombieImage, getWidth()/2+24, getHeight()/2+24, this);
        g.drawImage(_zombieImage, getWidth()/2-24, getHeight()/2+24, this);
        g.drawImage(_zombieImage, getWidth()/2-24, getHeight()/2, this);
        g.drawImage(_zombieImage, getWidth()/2-24, getHeight()/2-24, this);
        g.drawImage(_zombieImage, getWidth()/2, getHeight()/2-24, this);
        g.drawImage(_zombieImage, getWidth()/2+24, getHeight()/2-24, this);
    }
}
