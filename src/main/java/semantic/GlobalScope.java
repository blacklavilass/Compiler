package semantic;

import exception.SemanticException;
import nodes.Type;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GlobalScope implements Scope{
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
    public Callable getCallable(String name, List<Type> parameters) {
        Callable temp = new Callable(name, parameters);
        for (Callable callable : callables) {
            if (callable.equals(temp)) return callable;
        }
        throw new SemanticException("Callable with this name or list of parameters has not been declared: " + name + " " + parameters.toString());
    }

    @Override
    public Variable getVariable(String name) {
        for (Variable i : variables) {
            if (i.getName().equals(name)) return i;
        }
        throw new SemanticException("Variable \"" + name + "\" is not found");
    }

    @Override
    public void addVariable(String name, Type type) {
        Variable i = new Variable(name, type);
        if (variables.contains(i)) throw new SemanticException("Variable is already declared");
        variables.add(i);
    }

    @Override
    public boolean contains(Variable var) {
        return variables.contains(var);
    }
}
