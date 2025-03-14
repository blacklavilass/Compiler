package nodes;

import java.util.ArrayList;
import java.util.List;

public class ForIteratorAssignNode extends BasicNode {
    private AssignNode assign;
    private final boolean wasDeclared;

    public ForIteratorAssignNode(AssignNode assign, boolean wasDeclared) {
        this.assign = assign;
        this.wasDeclared = wasDeclared;
    }

    public ForIteratorAssignNode(AssignNode assign) {
        this.assign = assign;
        this.wasDeclared = false;
    }

    @Override
    public String toString() {
        return "for assign" + (wasDeclared ? ": declared here" : "");
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<Node>();
        children.add(assign);
        return children;
    }
}
