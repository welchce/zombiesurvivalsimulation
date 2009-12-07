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
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import java.net.URL;

/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class MainFrame extends JFrame {

    static final String WINDOW_TITLE = "Zombie Human Survival Simulation";
    public static final Dimension SCREEN_SIZE = new Dimension(20, 21);
    private JLabel _numZombiesLabel = new JLabel("0");
    private JLabel _numHumansLabel = new JLabel("0");
    private JTextField _perZombiesWinText = new JTextField("50");
    private JTextField _perHumansToZombiesText = new JTextField("50");
    private JLabel _zombiesKilledLabel = new JLabel("0");
    private JLabel _humansKilledLabel = new JLabel("0");
    private JLabel _humansSavedLabel = new JLabel("0");
    private JLabel _humansConvertedLabel = new JLabel("0");
    private JButton _playPauseButton;
    private JButton _stepButton;
    private JButton _fastForwardButton;
    private JButton _resetButton;
    private JRadioButton _addHumanButton;
    private JRadioButton _addZombieButton;
    private ImageIcon _playImageIcon,  _pauseImageIcon;
    private SimulationController _simulationController;
    private ArrayList<Entity> _board = new ArrayList<Entity>();
    private SimulationPanel _simulationPanel = new SimulationPanel(_board);


    public MainFrame() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        _playImageIcon = createImageIcon("images/Play.gif", "Play");
        _pauseImageIcon = createImageIcon("images/Pause.gif", "Pause");
        this.createGUI();
        this.setTitle(WINDOW_TITLE);
        this.pack();
        this.setLocationRelativeTo(null);
        _simulationController = new SimulationController(this, _simulationPanel,_board);
        _simulationPanel.addMouseMotionListener(_simulationController);
        _simulationPanel.addMouseListener(_simulationController);
        _simulationPanel.requestFocus();
    }

    public void addPlayPauseButtonHandler(ActionListener listener) {
        _playPauseButton.addActionListener(listener);
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

    public void addResetButtonHandler(ActionListener listener) {
        _resetButton.addActionListener(listener);
    }

    public int getZombieConvertPer() {
        int zombieHumanPer = Integer.valueOf(_perHumansToZombiesText.getText());
        if (zombieHumanPer > 100) zombieHumanPer = 100;
        if (zombieHumanPer < 0) zombieHumanPer = 0;
        return zombieHumanPer;
    }

    public int getZombiesWinPer() {
        int zombieWinPer = Integer.valueOf(_perZombiesWinText.getText());
        if (zombieWinPer > 100) zombieWinPer = 100;
        if (zombieWinPer < 0) zombieWinPer = 0;
        return zombieWinPer;
    }

    public void updateNumHumans(int num) {
        _numHumansLabel.setText(String.valueOf(num));
    }

    public void updateNumZombies(int num) {
        _numZombiesLabel.setText(String.valueOf(num));
    }

    public void updateHumansSavedLabel(int num) {
        _humansSavedLabel.setText(String.valueOf(num));
    }

    public void updateHumansConvertedLabel(int num) {
        _humansConvertedLabel.setText(String.valueOf(num));
    }

    public void updateZombiesKilledLabel(int num) {
        _zombiesKilledLabel.setText(String.valueOf(num));
    }

    public void updateHumansKilledLabel(int num) {
        _humansKilledLabel.setText(String.valueOf(num));
    }

    public void togglePlayPause(boolean play) {
        if (play) {
            _playPauseButton.setIcon(_pauseImageIcon);
            _stepButton.setEnabled(false);
            _fastForwardButton.setEnabled(false);
            _resetButton.setEnabled(false);

        } else {
            _playPauseButton.setIcon(_playImageIcon);
            _stepButton.setEnabled(true);
            _fastForwardButton.setEnabled(true);
            _resetButton.setEnabled(true);
        }
    }

    public void disableRetardButtons() {
        _addHumanButton.setEnabled(false);
        _addZombieButton.setEnabled(false);
    }

    public void enableRetardButtons() {
        _addHumanButton.setEnabled(true);
        _addZombieButton.setEnabled(true);
        _resetButton.setEnabled(true);
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
        _playPauseButton = new JButton(_playImageIcon);
        bottomPanel.add(_playPauseButton, c);
        c.gridx = 1;
        _stepButton = new JButton(createImageIcon("images/Step.gif", "Step"));
        bottomPanel.add(_stepButton, c);
        c.gridx = 2;
        _fastForwardButton = new JButton(createImageIcon("images/FastForward.gif", "Fast Forward"));
        bottomPanel.add(_fastForwardButton, c);
        c.gridx = 3;
        _addHumanButton = new JRadioButton("Human", true);
        bottomPanel.add(_addHumanButton, c);
        c.gridx = 4;
        _addZombieButton = new JRadioButton("Zombie", false);
        bottomPanel.add(_addZombieButton, c);
        
        ButtonGroup creatureButtons = new ButtonGroup();
        creatureButtons.add(_addZombieButton);
        creatureButtons.add(_addHumanButton);
        return bottomPanel;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 0, 20);
        c.gridy = 0;
        rightPanel.add(new JLabel("Zombies Killed"), c);
        c.insets = new Insets(0, 0, 0, 20);
        c.gridy = 1;
        rightPanel.add(_zombiesKilledLabel, c);
        c.insets = new Insets(10, 0, 0, 20);
        c.gridy = 2;
        rightPanel.add(new JLabel("Humans Killed"), c);
        c.insets = new Insets(0, 0, 0, 20);
        c.gridy = 3;
        rightPanel.add(_humansKilledLabel, c);
        c.insets = new Insets(10, 0, 0, 20);
        c.gridy = 4;
        rightPanel.add(new JLabel("Humans Saved"), c);
        c.insets = new Insets(0, 0, 0, 20);
        c.gridy = 5;
        rightPanel.add(_humansSavedLabel, c);
        c.insets = new Insets(10, 0, 0, 20);
        c.gridy = 6;
        rightPanel.add(new JLabel("Humans Converted"), c);
        c.insets = new Insets(0, 0, 0, 20);
        c.gridy = 7;
        rightPanel.add(_humansConvertedLabel, c);
        c.gridy = 8;
        _resetButton = new JButton("Reset");
        rightPanel.add(_resetButton, c);
        return rightPanel;
    }

    private JPanel createLeftPanel() {
        GridBagConstraints c = new GridBagConstraints();
        JPanel leftPanel = new JPanel(new GridBagLayout());
        c.insets = new Insets(10, 20, 0, 0);
        c.gridy = 0;
        leftPanel.add(new JLabel("# Zombies"), c);
        c.insets = new Insets(0, 20, 0, 0);
        c.gridy = 1;
        leftPanel.add(_numZombiesLabel, c);
        c.insets = new Insets(10, 20, 0, 0);
        c.gridy = 2;
        leftPanel.add(new JLabel("# Humans"), c);
        c.insets = new Insets(0, 20, 0, 0);
        c.gridy = 3;
        leftPanel.add(_numHumansLabel, c);
        c.insets = new Insets(10, 20, 0, 0);
        c.gridy = 4;
        leftPanel.add(new JLabel("Zombies Win"), c);
        c.insets = new Insets(0, 20, 0, 0);
        c.gridy = 5;
        // This is ugly, someone consider fixing?
        _perZombiesWinText.setPreferredSize(new Dimension(40, 20));
        JPanel zombieWinPer = new JPanel(new BorderLayout());
        zombieWinPer.add(_perZombiesWinText, BorderLayout.WEST);
        zombieWinPer.add(new JLabel("%", JLabel.LEFT), BorderLayout.CENTER);
        leftPanel.add(zombieWinPer, c);
        c.insets = new Insets(10, 20, 0, 0);
        c.gridy = 6;
        leftPanel.add(new JLabel("Humans->Zombies"), c);
        c.insets = new Insets(0, 20, 0, 0);
        c.gridy = 7;
        // This is ugly, someone consider fixing?
        _perHumansToZombiesText.setPreferredSize(new Dimension(40, 20));
        JPanel humanToZombiePer = new JPanel(new BorderLayout());
        humanToZombiePer.add(_perHumansToZombiesText, BorderLayout.WEST);
        humanToZombiePer.add(new JLabel("%", JLabel.LEFT), BorderLayout.CENTER);
        leftPanel.add(humanToZombiePer, c);
        return leftPanel;
    }
}
