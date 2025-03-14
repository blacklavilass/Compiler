package nodes;

import java.util.ArrayList;
import java.util.List;

public class IfNode extends BasicNode {
    protected ExprNode condition;
    protected StmtListNode thenStmt;

    public IfNode(ExprNode condition, StmtListNode thenStmt) {
        this.condition = condition;
        this.thenStmt = thenStmt;
    }

    @Override
    public String toString() {
        return "if";
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<Node>();
        children.add(condition);
        children.add(thenStmt);
        return children;
    }
}
