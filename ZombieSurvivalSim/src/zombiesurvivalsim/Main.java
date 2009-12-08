package zombiesurvivalsim;

import javax.swing.SwingUtilities;

/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });


    }
}
