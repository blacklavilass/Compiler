package nodes;

public class VarDeclarationNode extends AssignNode {

    public VarDeclarationNode(VariableNameNode varName, ExprNode expr) {
        super(varName, expr);
    }

    public VarDeclarationNode(AssignNode another) {
        super(another.varName, another.expr);
    }

    @Override
    public String toString() {
        return "new var :=";
    }
}
