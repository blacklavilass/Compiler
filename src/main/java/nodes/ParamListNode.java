package nodes;

import semantic.Scope;

import java.util.ArrayList;
import java.util.List;

public class ParamListNode extends BasicNode{
    private List<VarLineNode> children;

    public ParamListNode(List<VarLineNode> children) {
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
    public List<VarLineNode> children() {
        return children;
    }

    @Override
    public void semanticCheck() {
        //not needed
    }

    @Override
    public void initialize(Scope scope) {
        for (VarLineNode child : children) {
            child.initialize(scope);
        }
    }
}
