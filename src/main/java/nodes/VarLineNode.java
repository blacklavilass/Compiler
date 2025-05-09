package nodes;

import semantic.Scope;
import semantic.Variable;

import java.util.ArrayList;
import java.util.List;

public class VarLineNode extends BasicNode {
    List<String> variables;
    Type type;

    public VarLineNode(List<VariableNameNode> variables, TypeNode type) {
        this.variables = new ArrayList<>();
        for (VariableNameNode var : variables) {
            this.variables.add(var.name);
        }
        this.type = type.name;
    }

    @Override
    public List<? extends Node> children() {
        return new ArrayList<>(0);
    }

    @Override
    public void semanticCheck() {
        //not needed
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder code = new StringBuilder();
        for (String var : variables) {
            code.append("ldc ").append(type.getDefaultValue()).append("\n");
            code.append(scope.getVariable(var).generatePutCode()).append("\n");
        }
        return code;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = scope;
        for (String variable : variables) {
            scope.addVariable(variable, type);
        }
    }
}
