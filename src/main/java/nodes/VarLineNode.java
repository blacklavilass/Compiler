package nodes;

import java.util.List;

public class VarLineNode extends Node {
    List<VariableNameNode> variables;
    private TypeNode type;

    public VarLineNode(List<VariableNameNode> variables, TypeNode type) {
        this.variables = variables;
        this.type = type;
    }

    @Override
    public List<? extends Node> children() {
        return variables;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
