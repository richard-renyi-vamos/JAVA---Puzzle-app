import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PuzzleGame extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private int[] nums = new int[9];

    public PuzzleGame() {
        setTitle("Sliding Puzzle 🧩");
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        generatePuzzle();

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(nums[i] == 0 ? "" : String.valueOf(nums[i]));
            buttons[i].setFont(new Font("Arial", Font.BOLD, 24));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setVisible(true);
    }

    private void generatePuzzle() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) list.add(i); // 0 is empty
        Collections.shuffle(list);

        for (int i = 0; i < 9; i++) nums[i] = list.get(i);
    }

    public void actionPerformed(ActionEvent e) {
        int emptyIndex = -1, clickedIndex = -1;

        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) emptyIndex = i;
            if (e.getSource() == buttons[i]) clickedIndex = i;
        }

        if (isAdjacent(emptyIndex, clickedIndex)) {
            buttons[emptyIndex].setText(buttons[clickedIndex].getText());
            buttons[clickedIndex].setText("");
            checkWin();
        }
    }

    private boolean isAdjacent(int i, int j) {
        int row1 = i / 3, col1 = i % 3;
        int row2 = j / 3, col2 = j % 3;
        return (Math.abs(row1 - row2) + Math.abs(col1 - col2)) == 1;
    }

    private void checkWin() {
        for (int i = 0; i < 8; i++) {
            if (!buttons[i].getText().equals(String.valueOf(i + 1))) return;
        }
        if (buttons[8].getText().equals("")) {
            JOptionPane.showMessageDialog(this, "🎉 Congratulations! You solved the puzzle! 🧠");
        }
    }

    public static void main(String[] args) {
        new PuzzleGame();
    }
}
