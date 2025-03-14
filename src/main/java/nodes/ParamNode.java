package nodes;

import java.util.ArrayList;
import java.util.List;

public class ParamNode extends BasicNode{
    private TypeNode type;
    private VariableNameNode name;

    public ParamNode(TypeNode type, VariableNameNode name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<Node>();
        children.add(type);
        return children;
    }
}
