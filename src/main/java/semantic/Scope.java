package semantic;

import nodes.Type;

import java.util.List;

public interface Scope {
    void addVariable(String name, Type type);
    Variable getVariable(String name);
    boolean contains(Variable var);
    void addCallable(Callable callable);
    Callable getCallable(String name, List<Type> parameters);
}
