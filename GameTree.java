import java.util.ArrayList;
import java.util.List;

// 2.uzd
public class GameTree {

    //TODO

}

class GameTreeNode {
    // kaut kads tads ir TreeNode izskats
    private int number;
    private int player;
    private int bank;
    private int[] playerScores;
    private GameTreeNode parent;
    private List<GameTreeNode> children;

    public GameTreeNode(int number, int player, int bank, int[] playerScores, GameTreeNode parent) {
        this.number = number;
        this.player = player;
        this.bank = bank;
        this.playerScores = playerScores;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public int getPlayer() {
        return player;
    }

    public int getBank() {
        return bank;
    }

    public int[] getPlayerScores() {
        return playerScores;
    }

    public GameTreeNode getParent() {
        return parent;
    }

    public List<GameTreeNode> getChildren() {
        return children;
    }

    public void addChild(GameTreeNode child) {
        children.add(child);
    }
}