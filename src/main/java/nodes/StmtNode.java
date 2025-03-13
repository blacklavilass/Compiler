package nodes;

import java.util.ArrayList;
import java.util.List;

public class StmtNode extends BasicNode{
    private Node node;

    public StmtNode(Node node) {
        this.node = node;
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<>(1);
        children.add(node);
        return children;
    }

    @Override
    public String toString() {
        return "statement";
    }
}
