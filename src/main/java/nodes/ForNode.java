package nodes;

import java.util.ArrayList;
import java.util.List;

public class ForNode extends BasicNode{
    private ForIteratorAssignNode assign;
    private int ceil;
    private StmtListNode body;

    public ForNode(ForIteratorAssignNode assign, Integer ceil, StmtListNode body) {
        this.assign = assign;
        this.ceil = ceil;
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format("for to %d", ceil);
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<Node>();
        children.add(assign);
        children.add(body);
        return children;
    }
}
