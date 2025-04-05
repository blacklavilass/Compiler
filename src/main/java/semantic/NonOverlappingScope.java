package semantic;

import exception.SemanticException;
import nodes.Type;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class NonOverlappingScope implements Scope{
    private Scope parent;
    private Set<Variable> variables;

    public NonOverlappingScope(Scope parent) {
        this.parent = parent;
        variables = new TreeSet<>();
    }

    @Override
    public Variable getVariable(String name) {
        for (Variable i : variables) {
            if (i.getName().equals(name)) return i;
        }
        return parent.getVariable(name);
    }

    @Override
    public void addVariable(String name, Type type) {
        Variable i = new Variable(name, type);
        if (contains(i)) throw new SemanticException("Variable is already declared");
        variables.add(i);
    }

    @Override
    public boolean contains(Variable var) {
        return variables.contains(var) && parent.contains(var);
    }

    @Override
    public void addCallable(Callable callable) {
        parent.addCallable(callable);
    }

    @Override
    public Callable getCallable(String name, List<Type> parameters) {
        return parent.getCallable(name, parameters);
    }
}
