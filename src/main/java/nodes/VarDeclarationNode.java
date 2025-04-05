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
    public void initialize(Scope scope) {
        super.initialize(scope);
        scope.addVariable(varName, expr.getType());
    }
}
