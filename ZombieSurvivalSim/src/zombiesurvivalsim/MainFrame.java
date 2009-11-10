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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

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
    JButton _stepButton;
    JButton _fastForwardButton;
    JButton _addHumanButton;
    JButton _addZombieButton;

    SimulationController _simulationController;
    ArrayList<Creature> _creatures = new ArrayList();
    SimulationPanel _simulationPanel = new SimulationPanel(_creatures);
    
    public MainFrame() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.createGUI();
        this.setTitle(WINDOW_TITLE);
        this.pack();
        this.setLocationRelativeTo(null);
        _simulationController = new SimulationController(this, _simulationPanel, _creatures);
    }

    public void addPlayButtonHandler(ActionListener listener) {
        _playButton.addActionListener(listener);
    }
    public void addStepButtonHandler(ActionListener listener) {
        _stepButton.addActionListener(listener);
    }
    public void addFastForwardButtonHandler(ActionListener listener) {
        _fastForwardButton.addActionListener(listener);
    }
    public void addHumanButtonHandler(ActionListener listener) {
        _addHumanButton.addActionListener(listener);
    }
    public void addZombieButtonHandler(ActionListener listener) {
        _addZombieButton.addActionListener(listener);
    }

    private void createGUI() {
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(10);
        borderLayout.setHgap(20);
        this.setLayout(borderLayout);

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
        _addHumanButton = new JButton("+", createImageIcon("images/Human.gif", "Add Human"));
        bottomPanel.add(_addHumanButton,c);
        c.gridx = 4;
        _addZombieButton = new JButton("+", createImageIcon("images/Zombie.gif", "Add Zombie"));
        bottomPanel.add(_addZombieButton,c);
        return bottomPanel;
    }

    private JPanel createRightPanel() {
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,0,0,20);
        c.gridy = 0;
        topPanel.add(new JLabel("Zombies Killed"),c);
        c.insets = new Insets(0,0,0,20);
        c.gridy = 1;
        topPanel.add(_zombiesKilledLabel,c);
        c.insets = new Insets(10,0,0,20);
        c.gridy = 2;
        topPanel.add(new JLabel("Humans Killed"),c);
        c.insets = new Insets(0,0,0,20);
        c.gridy = 3;
        topPanel.add(_humansKilledLabel,c);
        c.insets = new Insets(10,0,0,20);
        c.gridy = 4;
        topPanel.add(new JLabel("Humans Saved"),c);
        c.insets = new Insets(0,0,0,20);
        c.gridy = 5;
        topPanel.add(_humansSavedLabel,c);
        return topPanel;
    }
    
    private JPanel createLeftPanel() {
        GridBagConstraints c = new GridBagConstraints();
        JPanel leftPanel = new JPanel(new GridBagLayout());
        c.insets = new Insets(10,20,0,0);
        c.gridy = 0;
        leftPanel.add(new JLabel("# Zombies"),c);
        c.insets = new Insets(0,20,0,0);
        c.gridy = 1;
        _numZombiesText.setPreferredSize(new Dimension(50,20));
        leftPanel.add(_numZombiesText,c);
        c.insets = new Insets(10,20,0,0);
        c.gridy = 2;
        leftPanel.add(new JLabel("# Humans"),c);
        c.insets = new Insets(0,20,0,0);
        c.gridy = 3;
        _numHumansText.setPreferredSize(new Dimension(50,20));
        leftPanel.add(_numHumansText,c);
        c.insets = new Insets(10,20,0,0);
        c.gridy = 4;
        leftPanel.add(new JLabel("Zombies Win"),c);
        c.insets = new Insets(0,20,0,0);
        c.gridy = 5;
        // This is ugly, someone consider fixing?
        _perZombiesWinText.setPreferredSize(new Dimension(40,20));
        JPanel zombieWinPer = new JPanel(new BorderLayout());
        zombieWinPer.add(_perZombiesWinText, BorderLayout.WEST);
        zombieWinPer.add(new JLabel("%", JLabel.LEFT),BorderLayout.CENTER);
        leftPanel.add(zombieWinPer,c);
        c.insets = new Insets(10,20,0,0);
        c.gridy = 6;
        leftPanel.add(new JLabel("Humans->Zombies"),c);
        c.insets = new Insets(0,20,0,0);
        c.gridy = 7;
        // This is ugly, someone consider fixing?
        _perHumansToZombiesText.setPreferredSize(new Dimension(40,20));
        JPanel humanToZombiePer = new JPanel(new BorderLayout());
        humanToZombiePer.add(_perHumansToZombiesText, BorderLayout.WEST);
        humanToZombiePer.add(new JLabel("%", JLabel.LEFT),BorderLayout.CENTER);
        leftPanel.add(humanToZombiePer,c);
        return leftPanel;
    }

}
