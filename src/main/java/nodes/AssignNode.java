package nodes;

import semantic.Scope;

import java.util.ArrayList;
import java.util.List;

public class AssignNode extends BasicNode {
    String varName;
    VariableNameNode node;
    ExprNode expr;

    public AssignNode(VariableNameNode varName, ExprNode expr) {
        this.varName = varName.name;
        node = varName;
        this.expr = expr;
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<Node>();
        children.add(node);
        children.add(expr);
        return children;
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = scope;
        expr.initialize(scope);
    }

    @Override
    public String toString() {
        return ":=";
    }
}
