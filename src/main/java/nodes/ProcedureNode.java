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
        this.name = name.name.toLowerCase();
        this.params = params;
        this.vars = vars;
        this.body = body;
    }

    @Override
    public String toString() {
        return "procedure " + name;
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<>();
        children.add(params);
        if (!vars.isEmpty())
            children.add(new ListNode(vars, name + " vars"));
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
                .append(")").append(Type.UNDEFINED.getAbbreviation()).append("\n");
        //TODO нормально посчитать стек (или убрать туду☺☺☺☺)
        code.append(".limit stack 20\n");
        code.append(".limit locals ").append(scope.getFreeVariableIdentifier()).append("\n");
        vars.stream().map(VarNode::generateCode).forEach(code::append);
        code.append(body.generateCode());
        code.append("return\n");
        code.append(".end method\n\n");
        return code;
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
        for (VarLineNode varLineNode: params.children()) {
            for (int i = 0; i < varLineNode.variables.size(); i++) {
                parameters.add(varLineNode.type);
            }
        }
        scope.addCallable(new Callable(name, parameters));
    }
}
