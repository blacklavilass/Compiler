package nodes;

import java.util.ArrayList;
import java.util.List;

public class ProcedureNode extends BasicNode {
    private VariableNameNode name;
    private ParamListNode params;
    private List<VarNode> vars;
    private StmtListNode body;

    public ProcedureNode(VariableNameNode name, ParamListNode params, List<VarNode> vars, StmtListNode body) {
        this.name = name;
        this.params = params;
        this.vars = vars;
        this.body = body;
    }

    @Override
    public String toString() {
        return "procedure " + name.toString();
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<>();
        children.add(params);
        children.add(new ListNode(vars, name.toString() + "vars"));
        children.add(body);
        return children;
    }
}
