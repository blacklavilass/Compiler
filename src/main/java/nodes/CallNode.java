package nodes;

import semantic.Scope;

import java.util.List;

public class CallNode extends BasicNode implements ExprNode {
    String functionName;
    List<ExprNode> arguments;

    public CallNode(VariableNameNode functionName, List<ExprNode> arguments) {
        this.functionName = functionName.name;
        this.arguments = arguments;
    }

    @Override
    public List<? extends Node> children() {
        return arguments;
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return functionName.toString();
    }
}
