package nodes;

import semantic.NonOverlappingScope;
import semantic.Scope;

import java.util.ArrayList;
import java.util.List;

public class StmtListNode extends BasicNode{
    private List<StmtNode> statements;

    public StmtListNode(List<StmtNode> statements) {
        this.statements = statements;
    }

    public StmtListNode(StmtListNode other) {
        this.statements = other.statements;
    }

    @Override
    public String toString() {
        return "statements";
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<Node>(1);
        children.addAll(statements);
        return statements;
    }

    @Override
    public void semanticCheck() {
        for (StmtNode statement : statements) {
            statement.semanticCheck();
        }
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder code = new StringBuilder();
        for (StmtNode statement : statements) {
            code.append(statement.generateCode());
        }
        return code;
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = new NonOverlappingScope(scope);
        for (StmtNode stmt : statements) {
            stmt.initialize(this.scope);
        }
    }
}
