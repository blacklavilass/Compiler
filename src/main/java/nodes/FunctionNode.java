package nodes;

import semantic.Callable;
import semantic.OverlappingScope;
import semantic.Scope;

import java.util.ArrayList;
import java.util.List;

public class FunctionNode extends BasicNode {
    private String name;
    private ParamListNode params;
    private Type returnType;
    private List<VarNode> vars;
    private StmtListNode body;

    public FunctionNode(VariableNameNode name, ParamListNode params, TypeNode returnType, List<VarNode> vars, StmtListNode body) {
        this.name = name.name;
        this.params = params;
        this.returnType = returnType.name;
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

    @Override
    public void semanticCheck() {
        //TODO: add Result expectation
        body.semanticCheck();
    }

    @Override
    public void initialize(Scope scope) {
        //TODO: add support of convertable types in parameters type
        scope = new OverlappingScope(scope);
        this.scope = scope;

        params.initialize(scope);
        for (VarNode var : vars) {
            var.initialize(scope);
        }
        body.initialize(scope);

        ArrayList<Type> parameters = new ArrayList<>();
        for (VarLineNode node: params.children()) {
            for (int i = 0; i < node.variables.size(); i++) {
                parameters.add(node.type);
                scope.addVariable(node.variables.get(i), node.type);
            }
        }
        scope.addCallable(new Callable(name, parameters, returnType));
    }
}

