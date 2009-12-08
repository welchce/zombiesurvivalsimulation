package zombiesurvivalsim;

import javax.swing.SwingUtilities;

/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class Main {

    /**
     * First thing that runs, starts up the GUI
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
