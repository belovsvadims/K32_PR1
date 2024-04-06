import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 2.uzd
public class GameTree {
    private static final Random RANDOM = new Random();

    public static GameTreeNode createGameTree() {
        // Izsaucam funkciju sakuma skaitļu ģenerēšanai
        List<Integer> initialNumbers = generateInitialNumbers();

        // Skaitļa atlase speletajam
        int selectedNumber = initialNumbers.get(RANDOM.nextInt(initialNumbers.size()));

        // Sākuma sakne kokam
        return createGameTreeNode(selectedNumber, 0, 0, 0);
    }
    //iegustam sakuma skaitļus
    private static List<Integer> generateInitialNumbers() {
        List<Integer> initialNumbers = new ArrayList<>(); // Sakuma skaitļu masīvs
        int count = 0;
        for (int i = 10000; i <= 20000; i++) { // ģenerēšanas robežas
            if (i % 2 == 0 && i % 3 == 0) {  //dališanas parbaude
                initialNumbers.add(i);
                count++;
            }
            if (count == 5) {  //5 skaitļu limits
                break;
            }
        }
        return initialNumbers;
    }

    private static GameTreeNode createGameTreeNode(int number, int playerScore, int computerScore, int bank) {
        GameTreeNode node = new GameTreeNode(number, playerScore, computerScore, bank);
        if (number == 2 || number == 3) {
            // Nosakam punktus
            evaluateTerminalState(node);
        } else {
            // Veidojam bērnu stadijas
            generateChildNodes(node);
        }
        return node;
    }

    private static void generateChildNodes(GameTreeNode parentNode) {
        int number = parentNode.getNumber();
        int playerScore = parentNode.getPlayerScore();
        int computerScore = parentNode.getComputerScore();
        int bank = parentNode.getBank();

        if (number % 2 == 0) { //pārbaudam vai dalas ar 2
            int newNumber = number / 2;
            int newPlayerScore = playerScore + 1;
            int newBank = (newNumber % 10 == 0 || newNumber % 10 == 5) ? bank + 1 : bank; // parbaudam vai beidzas ar 5 vai 0
            GameTreeNode childNode = createGameTreeNode(newNumber, newPlayerScore, computerScore, newBank);
            parentNode.setDividingBy2(childNode);
        }

        if (number % 3 == 0) { //parbaudam vai dalās ar 3
            int newNumber = number / 3;
            int newPlayerScore = (number % 2 == 0) ? playerScore + 1 : playerScore - 1; // vai dalās ar 2 bez atlikuma un piešķiram punktu
            int newBank = (newNumber % 10 == 0 || newNumber % 10 == 5) ? bank + 1 : bank;// parbaudam vai beidzas ar 5 vai 0
            GameTreeNode childNode = createGameTreeNode(newNumber, newPlayerScore, computerScore, newBank);
            parentNode.setDividingBy3(childNode);
        }
    }

    private static void evaluateTerminalState(GameTreeNode node) {
        int playerScore = node.getPlayerScore();
        int computerScore = node.getComputerScore();
        int bank = node.getBank();

        if (node.getNumber() == 2) {
            // Ja spēlētājs vinnē pievienojam bankas saturu speletajam
            node.setEvaluationScore(playerScore + bank);
        } else if (node.getNumber() == 3) {
            // Ja speletajs 2 vinnē pievienojam bankas saturu speletajam2 (dators)
            node.setEvaluationScore(computerScore + bank);
        }
    }
}

class GameTreeNode {
    private int number;
    private int playerScore;
    private int computerScore;
    private int bank;
    private int evaluationScore;
    private GameTreeNode dividingBy2;
    private GameTreeNode dividingBy3;

    public GameTreeNode(int number, int playerScore, int computerScore, int bank) {
        this.number = number;
        this.playerScore = playerScore;
        this.computerScore = computerScore;
        this.bank = bank;
        this.evaluationScore = Integer.MIN_VALUE;
        this.dividingBy2 = null;
        this.dividingBy3 = null;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public void setComputerScore(int computerScore) {
        this.computerScore = computerScore;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public int getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(int evaluationScore) {
        this.evaluationScore = evaluationScore;
    }

    public GameTreeNode getDividingBy2() {
        return dividingBy2;
    }

    public void setDividingBy2(GameTreeNode dividingBy2) {
        this.dividingBy2 = dividingBy2;
    }

    public GameTreeNode getDividingBy3() {
        return dividingBy3;
    }

    public void setDividingBy3(GameTreeNode dividingBy3) {
        this.dividingBy3 = dividingBy3;
    }
}