package nodes;

import java.util.ArrayList;
import java.util.List;

public class ForNode extends BasicNode{
    private AssignNode assign;
    private int ceil;
    private StmtListNode body;
    private boolean isUp;

    public ForNode(AssignNode assign, boolean isUp, Integer ceil, StmtListNode body) {
        this.assign = assign;
        this.isUp = isUp;
        this.ceil = ceil;
        this.body = body;
    }

    @Override
    public String toString() {
        return isUp ? String.format("for to %d", ceil) : String.format("for downto %d", ceil);
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<Node>();
        children.add(assign);
        children.add(body);
        return children;
    }
}
