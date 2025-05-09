package semantic;

import nodes.Type;

import java.util.List;

public interface Scope {
    void addVariable(String name, Type type);
    Variable getVariable(String name);

    int getFreeVariableIdentifier();
    int getFreeWhileIdentifier();
    int getFreeOperatorIdentifier();
    int getElseIdentifier();

    boolean contains(Variable var);
    void addCallable(Callable callable);
    Callable getCallable(String name, List<Type> types);
    String getName();
    int getVariableCount();
}
