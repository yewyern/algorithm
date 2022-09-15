package sudoku;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.concurrent.locks.LockSupport;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import utils.Console;

/**
 * @author zhou.xu
 * @date 2020/9/27 14:11
 */
public class SudokuFrame extends JFrame {

    private final SudokuBean[][] ground = new SudokuBean[9][9];
    private final JPanel groundP = new JPanel();
    private final SudokuSolver solver = new SudokuSolver();
    private Thread solverThread = null;

    public void init() {
        JButton start = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solver.setPause(solverThread);
            }
        });
        start.setText("start");

        Container cp = getContentPane();
        Box hb = Box.createHorizontalBox();
        // 横向的箱子，布局的一种形式，放到箱子中的每个组件以横向排列的方式组成
        hb.add(Box.createRigidArea(new Dimension(20, 10)));
        // 刚性区域，支撑开一个固定的距离，不会因界面拉伸而变化
        hb.add(groundP);
        hb.add(Box.createRigidArea(new Dimension(20, 10)));

        Box vb = Box.createVerticalBox();
        // 纵向的箱子，布局的一种形式，放到箱子中的每个组件以纵向排列的方式组成
        vb.add(Box.createRigidArea(new Dimension(20, 10)));
        vb.add(start);
        vb.add(Box.createRigidArea(new Dimension(20, 10)));
        vb.add(hb);
        vb.add(Box.createRigidArea(new Dimension(20, 10)));
        // 面板容器默认为BorderLayout布局方式，该布局方式将界面分成5个部分，North,South,Center,East,West
        cp.add(vb, BorderLayout.CENTER);
        // 游戏面板初始化
        groundP.setLayout(new GridLayout(9, 9));
        for (int i = 0; i < ground.length; i++) {
            for (int j = 0; j < ground[i].length; j++) {
                ground[i][j] = new SudokuBean();
                groundP.add(ground[i][j]);
            }
        }
        solver.setGround(ground);
        solverThread = new Thread(solver);
        solverThread.start();
    }

    public void start(String... args) {
        solver.init();
        char[][] board = new char[args.length][];
        for (int i = 0; i < board.length; i++) {
            board[i] = args[i].toCharArray();
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    ground[i][j].setState(SudokuBean.INIT);
                    ground[i][j].setValue(board[i][j] - '0');
                    solver.flip(i, j, board[i][j] - '1');
                } else {
                    solver.addSpace(i, j, 0, 0, 0);
                }
            }
        }
        LockSupport.unpark(solverThread);
        solver.setPause(solverThread);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        SudokuFrame sudoku = new SudokuFrame();
        sudoku.init();
        sudoku.start("..9748...", "7........", ".2.1.9...", "..7...24.", ".64.1.59.", ".98...3..",
            "...8.3.2.",
            "........6", "...2759..");
        Console.run(sudoku, 500, 500);
    }
}
