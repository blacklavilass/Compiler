package semantic;

import exception.SemanticException;
import nodes.Type;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class OverlappingScope implements Scope {
    private Scope parent;
    private Set<Variable> variables;
    private int maxVariableIdentifier = 0;
    private int maxWhileIdentifier = 0;
    private int maxOperatorIdentifier = 0;
    private int maxElseIdentifier = 0;

    public OverlappingScope(Scope parent) {
        this.parent = parent;
        variables = new TreeSet<>();
    }

    @Override
    public Variable getVariable(String name) {
        for (Variable i : variables) {
            if (i.getName().equals(name.toLowerCase())) return i;
        }
        return parent.getVariable(name);
    }

    @Override
    public int getFreeVariableIdentifier() {
        return maxVariableIdentifier++;
    }

    @Override
    public int getFreeWhileIdentifier() {
        return maxWhileIdentifier++;
    }

    @Override
    public int getFreeOperatorIdentifier() {
        return maxOperatorIdentifier++;
    }

    @Override
    public int getElseIdentifier() {
        return maxElseIdentifier++;
    }

    @Override
    public void addVariable(String name, Type type) {
        Variable i = new Variable(name.toLowerCase(), type, getFreeVariableIdentifier());
        if (variables.contains(i)) throw new SemanticException("Variable is already declared: " + name);
        variables.add(i);
    }

    @Override
    public boolean contains(Variable var) {
        return variables.contains(var);
    }

    @Override
    public void addCallable(Callable callable) {
        parent.addCallable(callable);
    }

    @Override
    public Callable getCallable(String name, List<Type> parameters) {
        return parent.getCallable(name, parameters);
    }

    @Override
    public String getName() {
        return parent.getName();
    }

    @Override
    public int getVariableCount() {
        return maxVariableIdentifier;
    }
}
