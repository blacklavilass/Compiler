package nodes;

import java.util.ArrayList;
import java.util.List;

public class IfElseNode extends  IfNode {
    private StmtListNode elseStmt;
    public IfElseNode(ExprNode condition, StmtListNode thenStmt, StmtListNode elseStmt) {
        super(condition, thenStmt);
        this.elseStmt = elseStmt;
    }

    @Override
    public String toString() {
        return "if-else";
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<Node>();
        children.add(this.condition);
        children.add(this.thenStmt);
        children.add(elseStmt);
        return children;
    }
}
