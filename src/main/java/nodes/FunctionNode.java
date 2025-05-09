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
        this.name = name.name.toLowerCase();
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
        body.semanticCheck();
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder code = new StringBuilder();
        code.append(".method public static ").append(name).append("(")
                .append(params.generateCode())
                .append(")").append(returnType.getAbbreviation()).append("\n");
        code.append(".limit stack 20\n");
        code.append(".limit locals ").append(scope.getFreeVariableIdentifier()).append("\n");
        vars.stream().map(VarNode::generateCode).forEach(code::append);
        code.append(body.generateCode());
        code.append(scope.getVariable("result").generateGetCode());
        code.append(returnType.getCommandWordPrefix()).append("return\n");
        code.append(".end method\n\n");
        return code;
    }

    @Override
    public void initialize(Scope scope) {
        scope = new OverlappingScope(scope);
        this.scope = scope;

        params.initialize(scope);
        for (VarNode var : vars) {
            var.initialize(scope);
        }
        body.initialize(scope);

        ArrayList<Type> parameters = new ArrayList<>();
        for (VarLineNode node : params.children()) {
            for (int i = 0; i < node.variables.size(); i++) {
                parameters.add(node.type);
            }
        }
        scope.addVariable("result", returnType);
        scope.addCallable(new Callable(name, parameters, returnType));
    }
}

