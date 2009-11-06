/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiesurvivalsim;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.net.URL;

/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class MainFrame extends JFrame {
    static final String WINDOW_TITLE = "Zombie Human Survival Simulation";
    JTextField _numZombiesText = new JTextField("0");
    JTextField _numHumansText = new JTextField("0");
    JTextField _perZombiesWinText = new JTextField("0");
    JTextField _perHumansToZombiesText = new JTextField("0");
    JLabel _zombiesKilledLabel = new JLabel("0");
    JLabel _humansKilledLabel = new JLabel("0");
    JLabel _humansSavedLabel = new JLabel("0");
    JButton _playButton;
    JButton _pauseButton;
    JButton _stepButton;
    JButton _fastForwardButton;
    JButton _addHumanButton;
    JButton _addZombieButton;

    SimulationPanel _simulationPanel = new SimulationPanel();

    public MainFrame() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.createGUI();
        this.setTitle(WINDOW_TITLE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void createGUI() {
        this.setLayout(new BorderLayout());
        this.add(new JPanel(), BorderLayout.NORTH);
        this.add(createRightPanel(), BorderLayout.EAST);
        this.add(_simulationPanel, BorderLayout.CENTER);
        this.add(createBottomPanel(), BorderLayout.SOUTH);
        this.add(createLeftPanel(), BorderLayout.WEST);
    }

    private ImageIcon createImageIcon(String path, String description) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            throw new RuntimeException("Cannot find file " + path);
        }
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        _playButton = new JButton(createImageIcon("images/Play.gif", "Play"));
        bottomPanel.add(_playButton,c);
        c.gridx = 1;
         _stepButton = new JButton(createImageIcon("images/Step.gif", "Step"));
        bottomPanel.add(_stepButton,c);
        c.gridx = 2;
          _fastForwardButton = new JButton(createImageIcon("images/FastForward.gif", "Fast Forward"));
        bottomPanel.add(_fastForwardButton,c);
        c.gridx = 3;
        _addHumanButton = new JButton("+ Human");
        bottomPanel.add(_addHumanButton,c);
        c.gridx = 4;
        _addZombieButton = new JButton("+ Zombie");
        bottomPanel.add(_addZombieButton,c);
        return bottomPanel;
    }

    private JPanel createRightPanel() {
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,20,0,20);
        c.gridy = 0;
        topPanel.add(new JLabel("Zombies Killed"),c);
        c.insets = new Insets(0,20,0,20);
        c.gridy = 1;
        topPanel.add(_zombiesKilledLabel,c);
        c.insets = new Insets(10,20,0,20);
        c.gridy = 2;
        topPanel.add(new JLabel("Humans Killed"),c);
        c.insets = new Insets(0,20,0,20);
        c.gridy = 3;
        topPanel.add(_humansKilledLabel,c);
        c.insets = new Insets(10,20,0,20);
        c.gridy = 4;
        topPanel.add(new JLabel("Humans Saved"),c);
        c.insets = new Insets(0,20,0,20);
        c.gridy = 5;
        topPanel.add(_humansSavedLabel,c);
        return topPanel;
    }
    private JPanel createLeftPanel() {
        GridBagConstraints c = new GridBagConstraints();
        JPanel leftPanel = new JPanel(new GridBagLayout());
        c.insets = new Insets(10,20,10,0);
        c.gridy = 0;
        leftPanel.add(new JLabel("# Zombies"),c);
        c.insets = new Insets(0,20,0,10);
        c.gridy = 1;
        _numZombiesText.setPreferredSize(new Dimension(80,20));
        leftPanel.add(_numZombiesText,c);
        c.insets = new Insets(10,20,0,10);
        c.gridy = 2;
        leftPanel.add(new JLabel("# Humans"),c);
        c.insets = new Insets(0,20,0,10);
        c.gridy = 3;
        _numHumansText.setPreferredSize(new Dimension(80,20));
        leftPanel.add(_numHumansText,c);
        c.insets = new Insets(10,20,0,10);
        c.gridy = 4;
        leftPanel.add(new JLabel("Zombies Win"),c);
        c.insets = new Insets(0,20,0,10);
        c.gridy = 5;
        _perZombiesWinText.setPreferredSize(new Dimension(80,20));
        leftPanel.add(_perZombiesWinText,c);
        c.insets = new Insets(10,20,0,10);
        c.gridy = 6;
        leftPanel.add(new JLabel("Humans->Zombies"),c);
        c.insets = new Insets(0,20,0,10);
        c.gridy = 7;
        _perHumansToZombiesText.setPreferredSize(new Dimension(80,20));
        leftPanel.add(_perHumansToZombiesText,c);
        return leftPanel;
    }

}
