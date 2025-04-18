package semantic;

import exception.SemanticException;
import nodes.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GlobalScope implements Scope {
    private Set<Variable> variables;
    private Set<Callable> callables;

    public GlobalScope() {
        variables = new TreeSet<>();
        callables = new TreeSet<>();
    }

    @Override
    public void addCallable(Callable callable) {
        if (callables.contains(callable)) throw new SemanticException("This callable has already been declared");
        callables.add(callable);
    }

    @Override
    public Callable getCallable(String name, List<Type> types) {
        name = name.toLowerCase();
        List<Callable> seive = new ArrayList<>();
        for (Callable callable : callables) {
            if (!callable.getName().equals(name)) continue;
            if (convertable(types, callable.getParameters())) seive.add(callable);
        }
        int max = 0;
        int count;
        List<Callable> result = new ArrayList<>();
        for (Callable callable : seive) {
            count = 0;
            for (int i = 0; i < callable.getParameters().size(); i++) {
                if (types.get(i).equals(callable.getParameters().get(i))) count++;
            }
            if (count > max) {
                max = count;
                result.clear();
            }
            if (count == max) result.add(callable);
        }
        if (result.isEmpty()) throw new SemanticException("None callables with name " + name + " can be called");
        if (result.size() > 1) throw new SemanticException("Multiple callables with name " + name + " can be called");
        return result.getLast();
    }

    private boolean convertable(List<Type> types, List<Type> parameters) {
        if (parameters.size() != types.size()) return false;
        for (int i = 0; i < types.size(); i++) {
            if (!(types.get(i).equals(parameters.get(i)) || TypeConvertibility.canConvert(types.get(i), parameters.get(i))))
                return false;
        }
        return true;
    }

    @Override
    public Variable getVariable(String name) {
        for (Variable i : variables) {
            if (i.getName().equals(name.toLowerCase())) return i;
        }
        throw new SemanticException("Variable \"" + name + "\" is not found");
    }

    @Override
    public void addVariable(String name, Type type) {
        Variable i = new Variable(name.toLowerCase(), type);
        if (variables.contains(i)) throw new SemanticException("Variable is already declared");
        variables.add(i);
    }

    @Override
    public boolean contains(Variable var) {
        return variables.contains(var);
    }
}
