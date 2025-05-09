package nodes;

import semantic.NonOverlappingScope;
import semantic.Scope;

import java.util.ArrayList;
import java.util.List;

public class IfElseNode extends IfNode {
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

    @Override
    public void semanticCheck() {
        super.semanticCheck();
        elseStmt.semanticCheck();
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder code = new StringBuilder();
        code.append(condition.generateCode());
        StringBuilder elseMarker = new StringBuilder().append("ELSE").append(scope.getElseIdentifier());
        code.append("ifeq ").append(elseMarker).append("\n");
        code.append(thenStmt.generateCode());
        code.append(elseMarker).append(":\n");
        code.append(elseStmt.generateCode());
        return code;
    }

    @Override
    public void initialize(Scope scope) {
        super.initialize(scope);
        elseStmt.initialize(this.scope);
    }
}
