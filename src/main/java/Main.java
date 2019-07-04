import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Main {
    public static  void main (String []args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame  = new FinderFrame();
                frame.setTitle("Find in Files");
                frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                frame.setVisible(true);

            }

        });

    }

}


