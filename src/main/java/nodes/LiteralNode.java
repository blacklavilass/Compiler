package nodes;

import java.util.ArrayList;
import java.util.List;

public class LiteralNode extends BasicNode implements ExprNode {
    private String value;

    public LiteralNode(String value) {
        this.value = value;
    }

    @Override
    public List<? extends Node> children() {
        return new ArrayList<>(0);
    }

    @Override
    public String toString() {
        return value;
    }
}
