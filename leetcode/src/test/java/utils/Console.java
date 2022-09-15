package utils;

import com.bulenkov.darcula.DarculaLaf;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author xzer
 */
public class Console {

    public static String title(Object o) {
        return o.getClass().getName();
    }

    public static void run(final JFrame frame, int width, int height) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new DarculaLaf());
        SwingUtilities.invokeLater(() -> {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(width, height);
            frame.setVisible(true);
        });
    }

    public static void run(final JPanel panel, int width, int height) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(title(panel));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel);
            frame.setSize(width, height);
            frame.setVisible(true);
        });
    }
}
