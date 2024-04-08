import java.util.ArrayList;
import java.util.List;

public class EvaluationFunction {
    private static final int MAX_DEPTH = 4; // koka dzilums
    private static final int START_DEPTH = 1;
    public static void main(String[] args) {
        System.out.println(evaluateState(GameTree.createGameTree(9240, 1, 0, 0)));
                // !parskatam! speles sakuma stavoklis (jo playerScore, computerScore un bank ir 0)

        System.out.println("-------------");

        System.out.println(leafNodes);      //!parskatam! izvada massivu ar strupceliem ar vertejumiem

        System.out.println("-------------");

        postOrder(evaluateState(GameTree.createGameTree(9240, 1, 0, 0)));
                // //!parskatam! izvada koku ka sarakstu
    }
    static public void postOrder(GameTreeNode root) {   // pect tam izdzesim
        if (root == null) return;                       // pect tam izdzesim
        System.out.println(root);                       // pect tam izdzesim
        postOrder(root.getDividingBy2());               // pect tam izdzesim
        postOrder(root.getDividingBy3());               // pect tam izdzesim
    }
    private static List<GameTreeNode> leafNodes; // massivs ar stupceliem, kurus novertesim
    public static GameTreeNode evaluateState(GameTreeNode gameTreeNode) { // atgriež kokas virsoti (pašo pirmo) lai butu viegli stradat algoritmos
        leafNodes = new ArrayList<>(); // massiva deklaresana

        findLeafs(gameTreeNode, START_DEPTH); // izsaucam funkciju, lai atrast visus koka stupcelus

        for (GameTreeNode i : leafNodes) { // noverte strupcelus/speles..stavoklus (atnem no ComputerScore PlayerScore)
            if (i.getNumber() == 2 || i.getNumber() == 3) {
//                if (i.getEvaluationScore() == 0) {
//                    i.setEvaluationScore(i.getComputerScore() - i.getPlayerScore() + i.getBank());
//                } else {
//                    i.setEvaluationScore(i.getComputerScore() - i.getPlayerScore() - i.getBank());
//                }
            } else {
                if (i.getEvaluationScore() == 0) {
                    i.setEvaluationScore(i.getComputerScore() - i.getPlayerScore());
                } else {
                    i.setEvaluationScore(i.getPlayerScore() - i.getComputerScore());
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