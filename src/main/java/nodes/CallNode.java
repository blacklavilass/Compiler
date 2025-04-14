package nodes;

import semantic.Callable;
import semantic.DefaultFunctions;
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
    public void semanticCheck() {
        ArrayList<Type> types = new ArrayList<>();
        for (ExprNode arg : arguments) {
            arg.semanticCheck();
            types.add(arg.getType());
        }
        DefaultFunctions.addIfDefault(functionName, types, scope);
        Callable function = scope.getCallable(functionName, types);
        for (int i = 0; i < arguments.size(); i++) {
            if (arguments.get(i).getType() != function.getParameters().get(i)) {
                arguments.set(i, new CastNode(function.getParameters().get(i), arguments.get(i), scope));
            }
        }
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
        return functionName;
    }

    @Override
    public Type getType() {
        ArrayList<Type> types = new ArrayList<>();
        for (ExprNode arg : arguments) {
            types.add(arg.getType());
        }
        return scope.getCallable(functionName, types).getReturnType();
    }
}
