package nodes;

import semantic.Scope;

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
        node.initialize(scope);
        expr.initialize(scope);
        scope.addVariable(node.name, Type.UNDEFINED);
    }
}
