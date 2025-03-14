package nodes;

import java.util.ArrayList;
import java.util.List;

public class TypeNode extends BasicNode {
    String name;
    public TypeNode(String typeName) {
        this.name = typeName;
    }

    @Override
    public List<? extends Node> children() {
        return new ArrayList<>(0);
    }

    @Override
    public String toString() {
        return name;
    }
}
