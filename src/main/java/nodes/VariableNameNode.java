package nodes;

import java.util.ArrayList;
import java.util.List;

public class VariableNameNode extends BasicNode implements ExprNode {
    String name;

    public VariableNameNode(String varName) {
        name = varName;
    }

    @Override
    public List<? extends Node> children() {
        return new ArrayList<>(0) ;
    }

    @Override
    public String toString() {
        return name;
    }
}
