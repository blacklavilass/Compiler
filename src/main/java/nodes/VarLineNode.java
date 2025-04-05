package nodes;

import semantic.Scope;

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
        for (String variable : variables) {
            addVariable(variable, type);
        }
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
