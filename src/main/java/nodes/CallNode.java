package nodes;

import java.util.List;

public class CallNode extends BasicNode implements ExprNode {
    VariableNameNode functionName;
    List<? extends Node> arguments;

    public CallNode(VariableNameNode functionName, List<? extends Node> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    @Override
    public List<? extends Node> children() {
        return arguments;
    }

    @Override
    public String toString() {
        return functionName.toString();
    }
}
