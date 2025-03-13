package nodes;

import java.util.ArrayList;
import java.util.List;

public class VarDeclarationNode extends BasicNode {
    private AssignNode assign;

    public VarDeclarationNode(AssignNode assign) {
        this.assign = assign;
    }

    @Override
    public String toString() {
        return "var statement";
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<Node>();
        children.add(assign);
        return children;
    }
}
