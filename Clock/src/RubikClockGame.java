import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RubikClockGame extends JFrame {
    private Clock[][] clocks;
    private JButton[][] buttons;
    private int steps;
    private JLabel stepsLabel;



    //separate model and v
    // documentation add my view task and test cases clesar like description 
    public RubikClockGame() {
        setTitle("Rubik Clock Game");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        steps = 0;

        JPanel clockPanel = new JPanel(new GridLayout(3, 3));
        clocks = new Clock[3][3];
        buttons = new JButton[2][2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                clocks[i][j] = new Clock();
                JButton clockButton = new JButton(Integer.toString(clocks[i][j].getHour()));
                clockButton.addActionListener(new ClockButtonListener(i, j));
                clockPanel.add(clockButton);
            }
        }

        add(clockPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                buttons[i][j] = new JButton("Button");
                buttons[i][j].addActionListener(new ButtonListener(i, j));
                buttonPanel.add(buttons[i][j]);
            }
        }

        add(buttonPanel, BorderLayout.CENTER);
        JButton res_game = new JButton("Restart game!");
        res_game.addActionListener(new RestartGameListener());
        add(res_game, BorderLayout.SOUTH);
        stepsLabel = new JLabel("Steps: " + steps);
        add(stepsLabel, BorderLayout.EAST);

        setSize(350, 350);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class RestartGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            restartGame();
            steps = 0;
            updateClocksDisplay();
        }
    }

    class ClockButtonListener implements ActionListener {
        private int row, col;

        ClockButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            clocks[row][col].increaseHour();
            updateClocksDisplay();
            steps++;
            checkGameEnd();
        }
    }

    class ButtonListener implements ActionListener {
        private int row, col;

        ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (row == 0) {
                clocks[0][col].increaseHour();
                clocks[0][col + 1].increaseHour();
                clocks[1][col].increaseHour();
                clocks[1][col + 1].increaseHour();
            } else {
                clocks[1][col].increaseHour();
                clocks[1][col + 1].increaseHour();
                clocks[2][col].increaseHour();
                clocks[2][col + 1].increaseHour();
            }
            steps++;
            updateClocksDisplay();

            checkGameEnd();
        }
    }

    private void updateClocksDisplay() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton clockButton = (JButton) ((JPanel) getContentPane().getComponent(0)).getComponent(i * 3 + j);
                clockButton.setText(Integer.toString(clocks[i][j].getHour()));
            }
        }
        stepsLabel.setText("Steps: " + steps);
    }

    private void checkGameEnd() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clocks[i][j].getHour() != 12) {
                    return;
                }
            }
        }

        JOptionPane.showMessageDialog(this, "Congratulations! You solved the Rubik Clock in " + steps + " steps.");

        restartGame();
    }

    private void restartGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                clocks[i][j] = new Clock();
            }
        }

        steps = 0;
        updateClocksDisplay();
    }
}