package nodes;

import java.util.ArrayList;
import java.util.List;

public class BinaryExprNode extends BasicNode implements ExprNode {
    ExprNode expr1;
    ExprNode expr2;
    BinaryOperator operator;

    public BinaryExprNode(BinaryOperator operator, ExprNode expr1, ExprNode expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.operator = operator;
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<>();
        children.add(expr1);
        children.add(expr2);
        return children;
    }

    @Override
    public String toString() {
        return operator.toString();
    }
}
