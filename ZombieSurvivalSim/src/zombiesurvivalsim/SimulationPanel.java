package zombiesurvivalsim;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Point;

import java.util.ArrayList;

/**
 * Graphically shows the simulation
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class SimulationPanel extends JPanel {

    private ArrayList<Entity> _board;
    private Image _backbuffer;
    private Point _selectionLoc;
    private Color _selectionColor;
    private boolean _selecting = true;

    /**
     * Default Constructor
     * Sets up the simulation panel
     * @param board the current board to draw
     */
    public SimulationPanel(ArrayList<Entity> board) {
        _board = board;
        _backbuffer = new BufferedImage(MainFrame.SCREEN_SIZE.width * 24, MainFrame.SCREEN_SIZE.height * 24, BufferedImage.TYPE_INT_ARGB);
        _selectionLoc = new Point(0, 0);
        _selectionColor = Color.WHITE;
    }

    /**
     * draws the simulation
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        Graphics bg = _backbuffer.getGraphics();
        bg.setColor(Color.GRAY);
        bg.fillRect(0, 0, MainFrame.SCREEN_SIZE.width * 24, MainFrame.SCREEN_SIZE.height * 24);

        // I don't use a for next loop b/c it fixes a concurrent thread violation.
        for (int i = 0; i < _board.size(); i++) {
            Entity entity = _board.get(i);
            bg.drawImage(entity.getImage(), entity.getLocation().x * 24, entity.getLocation().y * 24, this);
        }



        if (_selecting) {
            bg.setColor(_selectionColor);
            bg.drawRect(_selectionLoc.x * 24, _selectionLoc.y * 24, 24, 24);
        }
        g.drawImage(_backbuffer, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Sets the selected grid location to color
     * @param location
     * @param color
     */
    public void setSelection(Point location, Color color) {
        _selectionLoc = location;
        _selectionColor = color;
    }

    /**
     * Hides the selection, useful when the simulation is running
     */
    public void hideSelection() {
        _selecting = false;
    }

    /**
     * Shows the selection, useful when the simulation isn't running
     */
    public void showSelection() {
        _selecting = true;
    }
}
