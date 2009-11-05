/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;
import javax.swing.JFrame;
import java.awt.Dimension;

/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class MainFrame extends JFrame {
    static final String WINDOW_TITLE = "Zombie Human Survival Simulation";

    public MainFrame() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.createGUI();
        this.setTitle(WINDOW_TITLE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void createGUI() {
        
    }

}
