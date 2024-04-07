import java.util.*;

// 2.uzd
public class GameTreeMinMax {

}

class GameTreeNodeMinMax {
    private Integer number;
    private int playerScore;
    private int computerScore;
    private int bank;
    private Integer evaluationScore;
    private GameTreeNodeMinMax dividingBy2;
    private GameTreeNodeMinMax dividingBy3;

    private GameTreeNodeMinMax parentTreeNode;

    private boolean isMax;

    private int treeLevel;
    public GameTreeNodeMinMax(Integer inputNumber, int playerScore, int computerScore, int bank, GameTreeNodeMinMax parentTreeNode, boolean isMax, int treeLevel) {


        this.number = inputNumber;
        this.playerScore = playerScore;
        this.computerScore = computerScore;
        this.bank = bank;
        this.evaluationScore = null;
        this.dividingBy2 = null;
        this.dividingBy3 = null;
        this.parentTreeNode = parentTreeNode;
        this.isMax = isMax;
        this.treeLevel = treeLevel;

        if (Game.totalLevels < treeLevel) {
            Game.totalLevels = treeLevel;
        }


        boolean skipChild2 = false;
        boolean skipChild3 = false;

        int divisionBy2 = inputNumber / 2;
        int divisionBy3 = inputNumber / 3;

        if (inputNumber % 2 != 0) {
            skipChild2 = true;  //) || number == 3
        }
        if (inputNumber % 3 != 0) {
            skipChild3 = true;
        }
        if (inputNumber == 3) {
            skipChild2 = true;
            skipChild3 = true;
        } else if (inputNumber == 2) {
            if (!isMax) {
                this.playerScore += bank;
            } else {
                this.computerScore += bank;
            }
            skipChild2 = true;
            skipChild3 = true;
        }

        List<Integer> scoreBank = null;

        if (isMax) {
            if (!skipChild2) {
                scoreBank = calculateScores(2, computerScore);

                this.dividingBy2 = new GameTreeNodeMinMax(divisionBy2, playerScore, scoreBank.get(0), scoreBank.get(1), this, false, treeLevel+1);
            }
            if (!skipChild3) {
                scoreBank = calculateScores(3, computerScore);
                this.dividingBy3 = new GameTreeNodeMinMax(divisionBy3, playerScore, scoreBank.get(0), scoreBank.get(1), this, false, treeLevel+1);
            }
        } else {
            if (!skipChild2) {
                scoreBank = calculateScores(2, playerScore);
                this.dividingBy2 = new GameTreeNodeMinMax(divisionBy2, scoreBank.get(0), computerScore, scoreBank.get(1), this, true, treeLevel+1);
            }
            if (!skipChild3) {
                scoreBank = calculateScores(3, playerScore);
                this.dividingBy3 = new GameTreeNodeMinMax(divisionBy3, scoreBank.get(0), computerScore, scoreBank.get(1), this, true, treeLevel+1);
            }
        }
        if (skipChild2 && skipChild3) {
            Game.finalNodes.add(this);
        }





    }

    public int getTreeLevel() {
        return treeLevel;
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

    public GameTreeNodeMinMax getDividingBy2() {
        return dividingBy2;
    }

    public void setDividingBy2(GameTreeNodeMinMax dividingBy2) {
        this.dividingBy2 = dividingBy2;
    }

    public GameTreeNodeMinMax getDividingBy3() {
        return dividingBy3;
    }

    public void setDividingBy3(GameTreeNodeMinMax dividingBy3) {
        this.dividingBy3 = dividingBy3;
    }

    public GameTreeNodeMinMax getParentTreeNode() {
        return this.parentTreeNode;
    }
    public boolean getisMax() {
        return this.isMax;
    }


    private List<Integer> calculateScores (int divisor, int currentScore) {
        int localNum = this.number / divisor;

        if (localNum % 2 == 0 && (localNum % 10 != 0 || localNum % 10 != 5)) {
            currentScore += 1;
        } else if (localNum % 10 == 0 || localNum % 10 == 5) {
            bank += 1;
        } else {
            currentScore -= 1;
        }

        List<Integer> result = new ArrayList<>();

        result.add(currentScore);
        result.add(bank);
        return result;
    }

}