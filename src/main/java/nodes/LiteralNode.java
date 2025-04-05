package nodes;

import java.util.ArrayList;
import java.util.List;

public class LiteralNode implements ExprNode {
    public final String value;

    public LiteralNode(String value) {
        this.value = value;
    }

    @Override
    public List<? extends Node> children() {
        return List.of();
    }

    @Override
    public void semanticCheck() {

    }

    @Override
    public List<String> tree() {
        return List.of();
    }
}
