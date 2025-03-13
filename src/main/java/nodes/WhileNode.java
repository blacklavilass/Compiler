package nodes;

import java.util.ArrayList;
import java.util.List;

public class WhileNode extends BasicNode{
    private ExprNode condition;
    private StmtListNode body;

    public WhileNode(ExprNode condition, StmtListNode body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public String toString() {
        return "while";
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<Node>();
        children.add(condition);
        children.add(body);
        return children;
    }
}
