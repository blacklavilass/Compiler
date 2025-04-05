package nodes;

import semantic.Scope;

import java.util.ArrayList;
import java.util.List;

public class TypeNode extends BasicNode {
    Type name;
    public TypeNode(String typeName) {
        this.name = Type.fromString(typeName);
    }

    @Override
    public List<? extends Node> children() {
        return new ArrayList<>(0);
    }

    @Override
    public void semanticCheck() {
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
