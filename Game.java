import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game {
        static int playerScore, computerScore, bank, chosenNumber, divisor, playAgain;
        static int algorithm; // Which algorithm to use. 1 - Minmax, 2 - AlphaBeta
        static int firstMove; // Who is starting the game. 1 - player, 2 - computer
        static int[] randomNumbers = new int[5]; // Array of 5 generated random numbers

        static int totalLevels = 0; // The amount of levels in a GameTree

        static GameTreeNodeMinMax currentSelectedNode; // Current node in GameTreeMinMax creation process
        static boolean isFirstPlayerComputer; // Checks if computer is the first player

        // GUI
        //static JFrame frame;
        static JPanel panel, buttonPanel, labelPanel;
        static JLabel headerLabel, label1, label2, label3, label4, label5, label6;
        static JButton btn1, btn2, btn3, btn4, btn5;
        static ActionListener numberButtonListener, firstMoveListener, algorithmListener, 
        divisorListener, playAgainListener;

    public static void main(String[] args) {
        createGUI();
        startGame();
    }

    public static void createGUI() {
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("K32_PR1");
        panel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new GridLayout(2,3));
        labelPanel = new JPanel(new GridLayout(0,1));

        headerLabel = new JLabel("", JLabel.CENTER);
        label1 = new JLabel("", JLabel.CENTER);
        label2 = new JLabel("", JLabel.CENTER);
        label3 = new JLabel("", JLabel.CENTER);
        label4 = new JLabel("", JLabel.CENTER);
        label5 = new JLabel("", JLabel.CENTER);
        label6 = new JLabel("", JLabel.CENTER);
        btn1 = new JButton("");
        btn2 = new JButton("");
        btn3 = new JButton("");
        btn4 = new JButton("");
        btn5 = new JButton("");
        btn1.setActionCommand("Button 1");
        btn2.setActionCommand("Button 2");
        btn3.setActionCommand("Button 3");
        btn4.setActionCommand("Button 4");
        btn5.setActionCommand("Button 5");
        labelPanel.add(headerLabel);
        labelPanel.add(label1);
        labelPanel.add(label2);
        labelPanel.add(label3);
        labelPanel.add(label4);
        labelPanel.add(label5);
        labelPanel.add(label6);
        panel.add(labelPanel, BorderLayout.CENTER);
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        buttonPanel.add(btn3);
        buttonPanel.add(btn4);
        buttonPanel.add(btn5);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        panel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setVisible(true);

        // Action Listener for choosing first number
        numberButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                
                switch (actionCommand) {
                    case "Button 1":
                        chosenNumber = randomNumbers[0];
                        break;
                    case "Button 2":
                        chosenNumber = randomNumbers[1];
                        break;
                    case "Button 3":
                        chosenNumber = randomNumbers[2];
                        break;
                    case "Button 4":
                        chosenNumber = randomNumbers[3];
                        break;
                    case "Button 5":
                        chosenNumber = randomNumbers[4];
                        break;    
                }
            }
        };

        firstMoveListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                
                switch (actionCommand) {
                    case "Button 1":
                        firstMove = 1;
                        break;
                    case "Button 2":
                        firstMove = 2;
                        break;
                }
            }
        };
        
        algorithmListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                
                switch (actionCommand) {
                    case "Button 1":
                        algorithm = 1;
                        break;
                    case "Button 2":
                        algorithm = 2;
                        break;
                }
            }
        };

        divisorListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                
                switch (actionCommand) {
                    case "Button 1":
                        divisor = 2;
                        break;
                    case "Button 2":
                        divisor = 3;
                        break;
                }
            }
        };

        playAgainListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                
                switch (actionCommand) {
                    case "Button 1":
                        frame.dispose();
                        createGUI();
                        startGame();
                        break;
                    case "Button 2":
                        System.exit(0);
                        break;
                }
            }
        };
    }

    // Removes ActionListeners from an existing button and adds a new one
    public static void changeActionListener(JButton btn, ActionListener newListener) {
        for (ActionListener listener : btn.getActionListeners()) {
            btn.removeActionListener(listener);
        }
        btn.addActionListener(newListener);
    }

    public static void startGame() {
        playerScore = 0;
        computerScore = 0;
        bank = 0;
        chosenNumber = 0;
        firstMove = 0;
        algorithm = 0;

        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");
        label6.setText("");

        for (int i = 0; i < 5; i++) {
            randomNumbers[i] = generateRandomNumber();
        }

        headerLabel.setText("Choose which number to start with");
        do {
            btn1.setText(String.valueOf(randomNumbers[0]));
            changeActionListener(btn1, numberButtonListener);
            btn2.setText(String.valueOf(randomNumbers[1]));
            changeActionListener(btn2, numberButtonListener);
            btn3.setText(String.valueOf(randomNumbers[2]));
            changeActionListener(btn3, numberButtonListener);
            btn4.setText(String.valueOf(randomNumbers[3]));
            changeActionListener(btn4, numberButtonListener);
            btn5.setText(String.valueOf(randomNumbers[4]));
            changeActionListener(btn5, numberButtonListener);
        }
        while (chosenNumber == 0);

        buttonPanel.remove(btn3);
        buttonPanel.remove(btn4);
        buttonPanel.remove(btn5);
 
        headerLabel.setText("Who starts the game?");
        do {
            btn1.setText("Player");
            changeActionListener(btn1, firstMoveListener);
            btn2.setText("Computer");
            changeActionListener(btn2, firstMoveListener);
        }
        while (firstMove == 0);
        
        headerLabel.setText("Which algorithm should computer use?");
        do {
            btn1.setText("Minmax");
            changeActionListener(btn1, algorithmListener);
            btn2.setText("AlphaBeta");
            changeActionListener(btn2, algorithmListener);
        }
        while (algorithm == 0);

        if (firstMove == 1) { //If computer is the first player, levels are named starting from MAX, else - from MIN
            isFirstPlayerComputer = false;
        }
        // Creating the Game Tree for MinMax
        minmaxGameTree = new GameTreeNodeMinMax(chosenNumber, playerScore, computerScore, bank, null, isFirstPlayerComputer, 1);
        // Calculating the weight (Evaluation) for every node of a Game Tree
        MinMaxAlgorithm.calculateWeights();
        // Tekosa virsotne
        currentSelectedNode = minmaxGameTree;

        // Parbaude, vai pirmais gajiens ir cilveka vai datora
        if (firstMove == 1) {
            playerMove();
        }
        else {
            if (algorithm == 1) {
                // function from Minmax that returns best divisor
                computerMove(MinMaxAlgorithm.findBestMove());
            }
            else {
                // function from AlphaBeta that returns best divisor
                computerMove(AlphaBetaAlgorithm.findBestMove());
            }
        }
    }

    // Speletaja gajiens
    public static void playerMove() {
        divisor = 0;

        label2.setText("Player Score: " + playerScore);
        label3.setText("Computer Score: " + computerScore);
        label4.setText("Bank: " + bank);
        headerLabel.setText("Current number: " + chosenNumber);

        do {
            btn1.setText("Divide by 2");
            changeActionListener(btn1, divisorListener);
            btn2.setText("Divide by 3");
            changeActionListener(btn2, divisorListener);
        } while (!(divisor == 2 || divisor == 3) || chosenNumber % divisor != 0);

        chosenNumber /= divisor; // Next number based on the answer

        // Changing current selected node for Minimax tree to its child
        if (divisor == 2) {
            currentSelectedNode = currentSelectedNode.getDividingBy2();
        } else {
            currentSelectedNode = currentSelectedNode.getDividingBy3();
        }

        // Checking if player gets the point
        if (chosenNumber % 2 == 0) {
            playerScore += 1;
        } else {
            playerScore -= 1;
        }
        // Checking if bank gets bigger
        if (chosenNumber % 10 == 0 || chosenNumber % 10 == 5) {
            bank += 1;
        }

        // Checking if the game is finished
        if ((chosenNumber % 2 == 0 || chosenNumber % 3 == 0) && chosenNumber != 2 && chosenNumber != 3) {
            if (algorithm == 1) {
                // function from Minmax that returns best divisor
                computerMove(MinMaxAlgorithm.findBestMove());
            }
            else {
                // function from AlphaBeta that returns best divisor
                computerMove(AlphaBetaAlgorithm.findBestMove());
            }
        }
        // Adding bank value to the score if player got a '2' in the final node
        else if (chosenNumber == 2) {
            playerScore += bank;
            endGame();
        }
        else {
            endGame();
        }
    }

    public static GameTreeNodeMinMax minmaxGameTree = null;

    // Datora gajiens
    public static void computerMove(int divisor) {
        chosenNumber /= divisor; // Next number based on the answer

        if (divisor == 2) {
            currentSelectedNode = currentSelectedNode.getDividingBy2();
        } else {
            currentSelectedNode = currentSelectedNode.getDividingBy3();
        }

        // Checking if computer gets the point
        if (chosenNumber % 2 == 0) {
            computerScore += 1;
        } else {
            computerScore -= 1;
        }
        // Checking if bank gets bigger
        if (chosenNumber % 10 == 0 || chosenNumber % 10 == 5) {
            bank += 1;
        }

        label1.setText("Computer divided " + (chosenNumber * divisor) + " by " + divisor + " and got " +  chosenNumber);

        // Checking if the game is finished
        if ((chosenNumber % 2 == 0 || chosenNumber % 3 == 0) && chosenNumber != 2 && chosenNumber != 3) {
            playerMove();
        }
        // Adding bank value to the score if computer got a '2' in the final node
        else if (chosenNumber == 2) {
            computerScore += bank;
            endGame();
        }
        else {
            endGame();
        }
    }

    // Rezultatu izvade
    public static void endGame() {
        playAgain = 0;

        headerLabel.setText("Last number: " + chosenNumber);
        label2.setText("Player Score: " + playerScore);
        label3.setText("Computer Score: " + computerScore);
        label4.setText("Bank: " + bank);

        if (playerScore > computerScore) {
            label5.setText("Player wins!");
        }
        else if (playerScore < computerScore) {
            label5.setText("Computer wins!");
        }
        else {
            label5.setText("Draw!");
        }

        label6.setText("Play again?");
            btn1.setText("Yes");
            changeActionListener(btn1, playAgainListener);
            btn2.setText("No");
            changeActionListener(btn2, playAgainListener);
    }

    // Nejausu skaitlu generesana speles sakumam
    public static int generateRandomNumber() {
        Random random = new Random();
        int randomNumber;

        do {
            randomNumber = random.nextInt(10001) + 10000;
        }
        while (randomNumber % 2 != 0 || randomNumber % 3 != 0);

        return randomNumber;
    }

    public static int getCurrentNumber() {
        return chosenNumber;
    }
}
