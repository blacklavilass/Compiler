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
    public StringBuilder generateCode() {
        StringBuilder code = new StringBuilder();
        for (VarLineNode child : children) {
            code.append(String.valueOf(child.type.getAbbreviation()).repeat(child.variables.size()));
        }
        return code;
    }

    @Override
    public void initialize(Scope scope) {
        for (VarLineNode child : children) {
            child.initialize(scope);
        }
    }
}
