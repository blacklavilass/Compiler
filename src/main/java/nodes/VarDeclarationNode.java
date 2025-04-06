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
        node.semanticCheck();
        expr.semanticCheck();
        node.variable.setType(expr.getType());
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = scope;
        scope.addVariable(node.name, Type.UNDEFINED);
    }
}
