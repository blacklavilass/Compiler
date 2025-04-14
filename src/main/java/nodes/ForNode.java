package nodes;

import exception.SemanticException;
import semantic.NonOverlappingScope;
import semantic.Scope;
import semantic.TypeConvertibility;

import java.util.ArrayList;
import java.util.List;

public class ForNode extends BasicNode{
    private AssignNode assign;
    private ExprNode ceil;
    private StmtListNode body;
    private boolean isUp;

    public ForNode(AssignNode assign, boolean isUp, ExprNode ceil, StmtListNode body) {
        this.assign = assign;
        this.isUp = isUp;
        this.ceil = ceil;
        this.body = body;
    }

    @Override
    public String toString() {
        return isUp ? "for to" : "for downto";
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<Node>();
        children.add(ceil);
        children.add(assign);
        children.add(body);
        return children;
    }

    @Override
    public void semanticCheck() {
        ceil.semanticCheck();
        assign.semanticCheck();
        body.semanticCheck();

        if (!TypeConvertibility.canConvert(ceil.getType(), Type.INTEGER)) throw new SemanticException("Not an integer after " + (isUp ? "to" : "downto") + " keyword. Instead got: " + ceil.getType());
    }

    @Override
    public void initialize(Scope scope) {
        scope = new NonOverlappingScope(scope);
        this.scope = scope;
        assign.initialize(scope);
        ceil.initialize(scope);
        body.initialize(scope);
    }
}
