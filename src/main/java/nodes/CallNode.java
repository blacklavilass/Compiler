package nodes;

import semantic.Scope;

import java.util.ArrayList;
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
        for (ExprNode arg : arguments) {
            arg.initialize(scope);
        }
    }

    @Override
    public String toString() {
        return functionName.toString();
    }

    @Override
    public Type getType() {
        ArrayList<Type> types = new ArrayList<Type>();
        for (ExprNode arg : arguments) {
            types.add(arg.getType());
        }
        return scope.getCallable(functionName, types).getReturnType();
    }
}
