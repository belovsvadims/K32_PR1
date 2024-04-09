import java.util.ArrayList;
import java.util.List;

public class EvaluationFunction {
    private static final int MAX_DEPTH = GameTree.MAX_DEPTH; // koka dzilums
    private static final int START_DEPTH = GameTree.START_DEPTH;
    private static List<GameTreeNode> leafNodes; // massivs ar stupceliem, kurus novertesim
    public static GameTreeNode evaluateState(GameTreeNode gameTreeNode) { // atgriež kokas virsoti (pašo pirmo) lai butu viegli stradat algoritmos
        leafNodes = new ArrayList<>(); // massiva deklaresana

        findLeafs(gameTreeNode, START_DEPTH); // izsaucam funkciju, lai atrast visus koka stupcelus

        for (GameTreeNode i : leafNodes) { // noverte strupcelus/speles..stavoklus (atnem no ComputerScore PlayerScore)
            System.out.println(i.getEvaluationScore());
            if (i.getNumber() == 2 || i.getNumber() == 3) {
                if (i.getEvaluationScore() == 0) {
                    i.setEvaluationScore(i.getComputerScore() - i.getPlayerScore() + i.getBank());
                } else {
                    i.setEvaluationScore(i.getComputerScore() - i.getPlayerScore() - i.getBank());
                }
            } else {
                if (i.getEvaluationScore() == 0) {
                    i.setEvaluationScore(i.getPlayerScore() - i.getComputerScore());
                } else {
                    i.setEvaluationScore(i.getComputerScore() - i.getPlayerScore());
                }
            }
        }
        return gameTreeNode;
    }
    public static void findLeafs(GameTreeNode gameTreeNode, int depth) { // atrodam visus koka stupcelus
        if (gameTreeNode == null) {
            return;
        }
        if (gameTreeNode.getDividingBy2() == null && gameTreeNode.getDividingBy3() == null) {
            if (depth == MAX_DEPTH) {
                gameTreeNode.setEvaluationScore(0);
            } else {
                gameTreeNode.setEvaluationScore(Integer.MIN_VALUE);
            }
            leafNodes.add(gameTreeNode);
        } else {
            findLeafs(gameTreeNode.getDividingBy2(), depth + 1);
            findLeafs(gameTreeNode.getDividingBy3(), depth + 1);
        }
    }
}