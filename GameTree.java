// 2.uzd
public class GameTree {

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