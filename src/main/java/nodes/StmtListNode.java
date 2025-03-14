package nodes;

import java.util.ArrayList;
import java.util.List;

public class StmtListNode extends BasicNode{
    private List<StmtNode> statements;

    public StmtListNode(List<StmtNode> statements) {
        this.statements = statements;
    }

    public StmtListNode(StmtListNode other) {
        this.statements = other.statements;
    }

    @Override
    public String toString() {
        return "statements";
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<Node>(1);
        children.addAll(statements);
        return statements;
    }
}
