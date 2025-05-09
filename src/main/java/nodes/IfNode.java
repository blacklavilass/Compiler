package nodes;

import exception.SemanticException;
import semantic.NonOverlappingScope;
import semantic.Scope;
import semantic.TypeConvertibility;

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

    @Override
    public void semanticCheck() {
        condition.semanticCheck();
        if (!condition.getType().equals(Type.BOOLEAN) && !TypeConvertibility.canConvert(condition.getType(), Type.BOOLEAN)) {
            throw new SemanticException("If condition should be boolean");
        }
        thenStmt.semanticCheck();
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder code = new StringBuilder();
        code.append(condition.generateCode());
        StringBuilder elseMarker = new StringBuilder().append("ELSE").append(scope.getElseIdentifier());
        code.append("ifeq ").append(elseMarker).append("\n");
        code.append(thenStmt.generateCode());
        code.append(elseMarker).append(":\n");
        return code;
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = scope;
        condition.initialize(scope);
        thenStmt.initialize(scope);
    }
}
