import java.util.ArrayList;
import java.util.List;

public class EvaluationFunction {
    public static void main(String[] args) {
        evaluateState(GameTree.createGameTree(10080));
        System.out.println(leafNodes);
    }
    private static List<GameTreeNode> leafNodes; // massivs ar stupceliem, kurus novertesim
    public static void evaluateState(GameTreeNode gameTreeNode) {
        leafNodes = new ArrayList<>(); // massiva deklaresana

        findLeafs(gameTreeNode); // izsaucam funkciju, lai atrast visus koka stupcelus

        for (GameTreeNode i : leafNodes) {
            i.setEvaluationScore(i.getComputerScore() - i.getPlayerScore()); // novertejuma funkcija
        }
    }
    public static void findLeafs(GameTreeNode gameTreeNode) { // atrodam visus koka stupcelus
        if (gameTreeNode == null) {
            return;
        }
        if (gameTreeNode.getDividingBy2() == null && gameTreeNode.getDividingBy3() == null) {
            gameTreeNode.setEvaluationScore(Integer.MIN_VALUE);
            leafNodes.add(gameTreeNode);
        } else {
            findLeafs(gameTreeNode.getDividingBy2());
            findLeafs(gameTreeNode.getDividingBy3());
        }
    }

}