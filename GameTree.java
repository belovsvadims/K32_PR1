public class GameTree {
    private static final int MAX_DEPTH = 4; // koka dzilums
    private static final int START_DEPTH = 0;
    public static GameTreeNode createGameTree(int number, int playerScore, int computerScore, int bank) {
        return createGameTreeNode(number, playerScore, computerScore, bank, START_DEPTH);
    }
    private static GameTreeNode createGameTreeNode(int number, int playerScore, int computerScore,
                                                   int bank, int depth) {
        if (depth >= MAX_DEPTH) { //parbauda vai ir sasniegts koka dzilims
            return null;
        }

        GameTreeNode node = new GameTreeNode(number, playerScore, computerScore, bank);
        if (number == 1) {
            // Pedejais skaitlis var but vai nu 2, vai nu 3
            return null;
        } else {
            // Veidojam bērnu stadijas
            generateChildNodes(node, depth + 1);
        }
        return node;
    }
    private static void generateChildNodes(GameTreeNode parentNode, int depth) {
        int number = parentNode.getNumber();
        int playerScore = parentNode.getPlayerScore();
        int computerScore = parentNode.getComputerScore();
        int bank = parentNode.getBank();

        if (number % 2 == 0) { //pārbaudam vai dalas ar 2
            int newNumber = number / 2;
            int newPlayerScore = playerScore;
            int newComputerScore = computerScore;
            int newBank = bank;
            if (depth % 2 == 0) {
                newPlayerScore = playerScore + 1;
            } else {
                newComputerScore = computerScore + 1;
            }
            newBank = (newNumber % 10 == 0 || newNumber % 10 == 5) ? bank + 1 : bank; // parbaudam vai beidzas ar 5 vai 0
            GameTreeNode childNode = createGameTreeNode(newNumber, newPlayerScore, newComputerScore, newBank, depth);
            parentNode.setDividingBy2(childNode);
        }

        if (number % 3 == 0) { //parbaudam vai dalās ar 3
            int newNumber = number / 3;
            int newPlayerScore = playerScore;
            int newComputerScore = computerScore;
            int newBank = bank;
            if (depth % 2 == 0) {
                newPlayerScore = (number % 2 == 0) ? playerScore + 1 : playerScore - 1; // vai dalās ar 2 bez atlikuma un piešķiram punktu
            } else {
                newComputerScore = (number % 2 == 0) ? computerScore + 1 : computerScore - 1; // vai dalās ar 2 bez atlikuma un piešķiram punktu
            }
            newBank = (newNumber % 10 == 0 || newNumber % 10 == 5) ? bank + 1 : bank;// parbaudam vai beidzas ar 5 vai 0
            GameTreeNode childNode = createGameTreeNode(newNumber, newPlayerScore, newComputerScore, newBank, depth);
            parentNode.setDividingBy3(childNode);
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

    @Override
    public String toString() {
        return "GameTreeNode{" +
                "number=" + number +
                ", playerScore=" + playerScore +
                ", computerScore=" + computerScore +
                ", bank=" + bank +
                ", evaluationScore=" + evaluationScore +
                '}';
    }
}