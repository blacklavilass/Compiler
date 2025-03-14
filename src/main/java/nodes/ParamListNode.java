package nodes;

import java.util.ArrayList;
import java.util.List;

public class ParamListNode extends BasicNode{
    private List<? extends Node> children;

    public ParamListNode(List<? extends Node> children) {
        this.children = children;
    }

    public static ParamListNode empty() {
        return new ParamListNode(new ArrayList<>());
    }

    @Override
    public String toString() {
        return "parameters";
    }

    @Override
    public List<? extends Node> children() {
        return children;
    }
}
