package nodes;

import java.util.ArrayList;
import java.util.List;

public class AssignNode extends BasicNode {
    VariableNameNode varName;
    ExprNode expr;

    public AssignNode(VariableNameNode varName, ExprNode expr) {
        this.varName = varName;
        this.expr = expr;
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<Node>();
        children.add(varName);
        children.add(expr);
        return children;
    }

    @Override
    public String toString() {
        return ":=";
    }
}
