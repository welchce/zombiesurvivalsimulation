/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.awt.Dimension;
/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class SimulationPanel extends JPanel {
    Image _humanImage, _zombieImage, _cowardImage, _heroImage, _backgroundImage, _safezoneImage;
    ArrayList<Creature> _creatures;
    ArrayList<SafeZone> _safezones;
    Image _backbuffer;

    public SimulationPanel(ArrayList<Creature> creatures, ArrayList<SafeZone> safezones) {
        _creatures = creatures;
        _safezones = safezones;
        _backbuffer = new BufferedImage(MainFrame.SCREEN_SIZE.width*24, MainFrame.SCREEN_SIZE.height*24, BufferedImage.TYPE_INT_ARGB);
        try {
            _humanImage = ImageIO.read(this.getClass().getResource("images/Human.gif"));
            _zombieImage = ImageIO.read(this.getClass().getResource("images/Zombie.gif"));
            _cowardImage = ImageIO.read(this.getClass().getResource("images/Coward.gif"));
            _heroImage = ImageIO.read(this.getClass().getResource("images/Hero.gif"));
            _backgroundImage = ImageIO.read(this.getClass().getResource("images/Background.png"));
            _safezoneImage = ImageIO.read(this.getClass().getResource("images/Safezone.gif"));
        } catch (java.lang.Exception e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public void paint(Graphics g) {
        Graphics bg = _backbuffer.getGraphics();
        //bg.fillRect(0, 0, MainFrame.SCREEN_SIZE.width*24, MainFrame.SCREEN_SIZE.height*24);
        bg.drawImage(_backgroundImage, 0, 0, MainFrame.SCREEN_SIZE.width*24, MainFrame.SCREEN_SIZE.height*24, null);
        for (SafeZone safeZone : _safezones) {
            bg.drawImage(_safezoneImage, safeZone.getLocation().x*24, safeZone.getLocation().y*24, null);
        }

        for (Creature creature : _creatures) {
            switch (creature.getType()) {
                case ZOMBIE:
                    bg.drawImage(_zombieImage, creature.getLocation().x*24, creature.getLocation().y*24, null);
                    break;
                case HUMAN:
                    bg.drawImage(_humanImage, creature.getLocation().x*24, creature.getLocation().y*24, null);
                    break;
                case COWARD:
                    bg.drawImage(_cowardImage, creature.getLocation().x*24, creature.getLocation().y*24, null);
                    break;
                case HERO:
                    bg.drawImage(_heroImage, creature.getLocation().x*24, creature.getLocation().y*24, null);
                    break;
            }
        }
        //g.drawImage(_backbuffer, 0, 0, this);
        g.drawImage(_backbuffer, 0, 0, getWidth(), getHeight(), this);
    }
}
