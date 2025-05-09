package nodes;

import exception.SemanticException;
import semantic.NonOverlappingScope;
import semantic.Scope;
import semantic.TypeConvertibility;

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

    @Override
    public void semanticCheck() {
        condition.semanticCheck();
        if (!condition.getType().equals(Type.BOOLEAN) && !TypeConvertibility.canConvert(condition.getType(), Type.BOOLEAN)) {
            throw new SemanticException("If condition should be boolean");
        }
        body.semanticCheck();

    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder code = new StringBuilder();
        int ident = scope.getFreeWhileIdentifier();
        StringBuilder whileStart = new StringBuilder().append("while").append(ident).append("start");
        StringBuilder whileEnd = new StringBuilder().append("while").append(ident).append("end");

        code.append(whileStart).append(":\n");
        code.append(condition.generateCode());
        code.append("ifeq ").append(whileEnd).append("\n");

        code.append(body.generateCode());
        code.append("goto ").append(whileStart).append("\n");

        code.append(whileEnd).append(":\n");
        return code;
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = new NonOverlappingScope(scope);
        condition.initialize(this.scope);
        body.initialize(this.scope);
    }
}
