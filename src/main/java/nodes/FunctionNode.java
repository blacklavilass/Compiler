package nodes;

import java.util.ArrayList;
import java.util.List;

public class FunctionNode extends BasicNode {
    private VariableNameNode name;
    private ParamListNode params;
    private TypeNode returnType;
    private List<VarNode> vars;
    private StmtListNode body;

    public FunctionNode(VariableNameNode name, ParamListNode params, TypeNode returnType, List<VarNode> vars, StmtListNode body) {
        this.name = name;
        this.params = params;
        this.returnType = returnType;
        this.vars = vars;
        this.body = body;
    }

    @Override
    public String toString() {
        return "function " + name.toString() + " of " + returnType.toString();
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<>();
        children.add(params);
        if (!vars.isEmpty())
            children.add(new ListNode(vars, name.toString() + " vars"));
        children.add(body);
        return children;
    }
}

