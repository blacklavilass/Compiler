package nodes;

import exception.SemanticException;
import semantic.Scope;
import semantic.TypeConvertibility;

public class VarDeclarationNode extends AssignNode {
    public VarDeclarationNode(AssignNode another) {
        super(another.node, another.expr);
    }

    @Override
    public String toString() {
        return "new var :=";
    }

    @Override
    public void semanticCheck() {
        expr.semanticCheck();
        if (expr.getType().equals(node.getType())) return;

        if (TypeConvertibility.canConvert(expr.getType(), node.getType())) {
            expr = new CastNode(node.getType(), expr, scope);
        } else {
            throw new SemanticException("Type mismatch: " + expr.getType() + " can't be converted " + node.getType());
        }
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = scope;
        scope.addVariable(node.name, Type.UNDEFINED);
    }
}
