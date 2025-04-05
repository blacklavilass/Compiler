package nodes;

import semantic.Scope;
import semantic.Variable;

import java.util.ArrayList;
import java.util.List;

public class VariableNameNode extends BasicNode implements ExprNode {
    String name;
    Variable variable;

    public VariableNameNode(String varName) {
        name = varName;
    }

    @Override
    public List<? extends Node> children() {
        return new ArrayList<>(0) ;
    }

    @Override
    public void semanticCheck() {
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = scope;
        variable = scope.getVariable(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Type getType() {
        return variable.getType();
    }
}
