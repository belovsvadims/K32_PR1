// 4.uzd
public class MinMaxAlgorithm {
    public static int findBestMove() {
        //int currentNumber = Game.getCurrentNumber();
        int bestValue = Integer.MIN_VALUE;

        GameTreeNodeMinMax child2 = Game.currentSelectedNode.getDividingBy2();
        GameTreeNodeMinMax child3 = Game.currentSelectedNode.getDividingBy3();

        if (child2 == null) {
            bestValue = 3;
        } else if (child3 == null) {
            bestValue = 2;
        } else {
            if (Game.currentSelectedNode.getEvaluationScore() == child2.getEvaluationScore()) {
                bestValue = 2;
            } else if (Game.currentSelectedNode.getEvaluationScore() == child3.getEvaluationScore()) {
                bestValue = 3;
            }
        }



        return bestValue;
    }

    public static void calculateWeights() {

        for (int i = Game.totalLevels; i > 0; i--) {
            calculateEvaluationFunc(Game.minmaxGameTree, i);
        }
    }

    private static void calculateEvaluationFunc(GameTreeNodeMinMax gameTreeNodeMinMax, int treeLevel) {

        if (gameTreeNodeMinMax == null) {
            return;
        }

        if (gameTreeNodeMinMax.getTreeLevel() != treeLevel) {
            calculateEvaluationFunc(gameTreeNodeMinMax.getDividingBy2(), treeLevel);
            calculateEvaluationFunc(gameTreeNodeMinMax.getDividingBy3(), treeLevel);
            return;
        }
        if (gameTreeNodeMinMax.getDividingBy2() != null || gameTreeNodeMinMax.getDividingBy3() != null) {
            if (gameTreeNodeMinMax.getDividingBy2() == null) {
                gameTreeNodeMinMax.setEvaluationScore(gameTreeNodeMinMax.getDividingBy3().getEvaluationScore());
            } else if (gameTreeNodeMinMax.getDividingBy3() == null) {
                gameTreeNodeMinMax.setEvaluationScore(gameTreeNodeMinMax.getDividingBy2().getEvaluationScore());
            } else {
                if (gameTreeNodeMinMax.getisMax()) {
                    if (gameTreeNodeMinMax.getDividingBy2().getEvaluationScore() > gameTreeNodeMinMax.getDividingBy3().getEvaluationScore()) {
                        gameTreeNodeMinMax.setEvaluationScore(gameTreeNodeMinMax.getDividingBy2().getEvaluationScore());
                    } else {
                        gameTreeNodeMinMax.setEvaluationScore(gameTreeNodeMinMax.getDividingBy3().getEvaluationScore());
                    }
                } else {
                    if (gameTreeNodeMinMax.getDividingBy2().getEvaluationScore() > gameTreeNodeMinMax.getDividingBy3().getEvaluationScore()) {
                        gameTreeNodeMinMax.setEvaluationScore(gameTreeNodeMinMax.getDividingBy3().getEvaluationScore());
                    } else {
                        gameTreeNodeMinMax.setEvaluationScore(gameTreeNodeMinMax.getDividingBy2().getEvaluationScore());
                    }
                }
            }
        } else {

                if (gameTreeNodeMinMax.getComputerScore() > gameTreeNodeMinMax.getPlayerScore()) {
                    gameTreeNodeMinMax.setEvaluationScore(1);
                } else if (gameTreeNodeMinMax.getComputerScore() < gameTreeNodeMinMax.getPlayerScore()) {
                    gameTreeNodeMinMax.setEvaluationScore(-1);
                } else {
                    gameTreeNodeMinMax.setEvaluationScore(0);
                }

        }
    }
}
