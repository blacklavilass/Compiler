package nodes;

import exception.SemanticException;
import semantic.NonOverlappingScope;
import semantic.Scope;
import semantic.TypeConvertibility;
import semantic.Variable;

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
    public StringBuilder generateCode() {
        StringBuilder code = new StringBuilder();
        int ident = scope.getFreeForIdentifier();
        StringBuilder forStart = new StringBuilder().append("for").append(ident).append("start");
        StringBuilder forEnd = new StringBuilder().append("for").append(ident).append("end");
        Variable v = assign.node.variable;
        code.append(assign.generateCode());
        code.append(forStart).append(":\n");
        code.append(BinaryOperator.EQUAL.getOperatorCode(Type.INTEGER, Type.INTEGER, scope));
        code.append("ifeq ").append(forEnd).append("\n");

        code.append(body.generateCode());

        code.append(v.generateGetCode());
        code.append("iconst 1\n");
        if (isUp) {
            code.append("iadd\n");
        } else {
            code.append("isub\n");
        }
        code.append(v.generatePutCode());
        code.append("goto ").append(forStart).append("\n");

        code.append(forEnd).append(":\n");
        return code;
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
