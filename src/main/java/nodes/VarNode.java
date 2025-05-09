package nodes;

import semantic.Scope;

import java.util.List;

public class VarNode extends BasicNode {
    List<VarLineNode> varLines;

    public VarNode(List<VarLineNode> varLines) {
        this.varLines = varLines;
    }

    @Override
    public List<? extends Node> children() {
        return varLines;
    }

    @Override
    public void semanticCheck() {
        for (VarLineNode varLine : varLines) {
            varLine.semanticCheck();
        }
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder code = new StringBuilder();
        for (VarLineNode varLine : varLines) {
            code.append(varLine.generateCode());
        }
        return code;
    }

    @Override
    public String toString() {
        return "varLines";
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = scope;
        for (VarLineNode varLine : varLines) {
            varLine.initialize(scope);
        }
    }
}
