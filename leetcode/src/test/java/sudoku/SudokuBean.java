package sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * @author zhou.xu
 * @since 2020/9/27 14:18
 */
public class SudokuBean extends JPanel {

    public static final int BLANK = 0;
    public static final int INIT = 1;
    public static final int TEMP = 2;
    public static final int TEMP_TRY = 3;
    public static final int ERROR = 4;
    public static final Color[] colors = {Color.BLACK, Color.BLACK, Color.BLUE, Color.YELLOW, Color.RED};
    public static final int fontSize = 28;
    private final JLabel label = new JLabel();

    public SudokuBean() {
        setSize(50, 50);
        setBackground(Color.WHITE);
        add(label);
    }

    /**
     * 状态: 0-空白，1-初始值，2-尝试值，3-错误值
     */
    private int state;

    /**
     * 当前值
     */
    private int value;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();

        setBorder(new LineBorder(Color.LIGHT_GRAY));
        if (state != BLANK) {
            g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
            String s = value + "";
            int width = g.getFontMetrics().stringWidth(s);
            g.setColor(colors[state]);
            g.drawString(s, (w - width) / 2, (h + fontSize / 2) / 2);
        }
    }
}
