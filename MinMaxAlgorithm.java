// 4.uzd
public class MinMaxAlgorithm {
    public static int findBestMove() {
        int bestValue = Integer.MIN_VALUE;

        // Children of the node
        GameTreeNodeMinMax child2 = Game.currentSelectedNodeMinMax.getDividingBy2();
        GameTreeNodeMinMax child3 = Game.currentSelectedNodeMinMax.getDividingBy3();

        // Checking which value to choose based on the evaluation score(s) of children
        if (child2 == null) {
            bestValue = 3;
        } else if (child3 == null) {
            bestValue = 2;
        } else {
            if (Game.currentSelectedNodeMinMax.getEvaluationScore() == child2.getEvaluationScore()) {
                bestValue = 2;
            } else if (Game.currentSelectedNodeMinMax.getEvaluationScore() == child3.getEvaluationScore()) {
                bestValue = 3;
            }
        }



        return bestValue;
    }

    public static void calculateWeights() {

        // Calculating the evaluation score based on levels
        for (int i = Game.totalLevels; i > 0; i--) {
            calculateEvaluationFunc(Game.minmaxGameTree, i);
        }
    }

    private static void calculateEvaluationFunc(GameTreeNodeMinMax gameTreeNodeMinMax, int treeLevel) {

        // Check if node exists
        if (gameTreeNodeMinMax == null) {
            return;
        }

        // Recurssion for finding the evaluation score for child nodes
        if (gameTreeNodeMinMax.getTreeLevel() != treeLevel) {
            calculateEvaluationFunc(gameTreeNodeMinMax.getDividingBy2(), treeLevel);
            calculateEvaluationFunc(gameTreeNodeMinMax.getDividingBy3(), treeLevel);
            return;
        }
        // If node has children
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
        } else { // If node doesnt have children (ir strupcels)
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
