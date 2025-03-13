package nodes;

import java.util.List;

public class VarNode extends Node {
    List<VarLineNode> varLines;

    public VarNode(List<VarLineNode> varLines) {
        this.varLines = varLines;
    }

    @Override
    public List<? extends Node> children() {
        return varLines;
    }

    @Override
    public String toString() {
        return "varLines";
    }
}
