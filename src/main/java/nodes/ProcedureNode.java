package nodes;

import semantic.Callable;
import semantic.OverlappingScope;
import semantic.Scope;

import java.util.ArrayList;
import java.util.List;

public class ProcedureNode extends BasicNode {
    private String name;
    private ParamListNode params;
    private List<VarNode> vars;
    private StmtListNode body;

    public ProcedureNode(VariableNameNode name, ParamListNode params, List<VarNode> vars, StmtListNode body) {
        this.name = name.name;
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
        if (!vars.isEmpty())
            children.add(new ListNode(vars, name.toString() + " vars"));
        children.add(body);
        return children;
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = new OverlappingScope(scope);
        params.initialize(scope);
        for (VarNode var : vars) {
            var.initialize(scope);
        }
        body.initialize(scope);
        ArrayList<Type> parameters = new ArrayList<>();
        for (VarLineNode varLineNode: params.children()) {
            for (int i = 0; i < varLineNode.variables.size(); i++) {
                parameters.add(varLineNode.type);
            }
        }
        scope.addCallable(new Callable(name, parameters));
    }
}
