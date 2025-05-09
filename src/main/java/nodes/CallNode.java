package nodes;

import semantic.Callable;
import semantic.DefaultFunctions;
import semantic.Scope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CallNode extends BasicNode implements ExprNode {
    String functionName;
    List<ExprNode> arguments;
    Callable function = null;

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
        if (function == null) {
            ArrayList<Type> types = new ArrayList<>();
            for (ExprNode arg : arguments) {
                arg.semanticCheck();
                types.add(arg.getType());
            }
            DefaultFunctions.addIfDefault(functionName, types, scope);
            function = scope.getCallable(functionName, types);
        }
        for (int i = 0; i < arguments.size(); i++) {
            if (arguments.get(i).getType() != function.getParameters().get(i)) {
                arguments.set(i, new CastNode(function.getParameters().get(i), arguments.get(i), scope));
            }
        }
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder code = new StringBuilder();
        if (DefaultFunctions.isDefault(functionName)) {
            code.append(DefaultFunctions.generate(functionName, arguments));
        } else {
            Collections.reverse(arguments);
            arguments.forEach(e -> code.append(e.generateCode()));
            Collections.reverse(arguments);
            code.append("invokestatic ").append(scope.getName()).append("/").append(functionName).append("(");
            function.getParameters().forEach(e -> code.append(e.getAbbreviation()));
            code.append(")").append(function.getReturnType().getAbbreviation()).append("\n");
        }
        return code;
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
        if (function == null) {
            ArrayList<Type> types = new ArrayList<>();
            for (ExprNode arg : arguments) {
                types.add(arg.getType());
            }
            function = scope.getCallable(functionName, types);
        }
        return function.getReturnType();
    }
}
