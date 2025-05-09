package nodes;

import semantic.GlobalScope;
import semantic.Scope;

import java.util.ArrayList;
import java.util.List;

public class ProgramNode extends BasicNode {
    VariableNameNode name;
    List<ProcedureNode> procs;
    List<FunctionNode> funcs;
    List<VarNode> vars;
    StmtListNode stmts;

    public ProgramNode(VariableNameNode name, List<ProcedureNode> procs, List<FunctionNode> funcs, List<VarNode> vars, StmtListNode stmts) {
        this.name = name;
        this.procs = procs;
        this.funcs = funcs;
        this.vars = vars;
        this.stmts = stmts;
    }

    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<>();
        children.add(new ListNode(procs, "procedures"));
        children.add(new ListNode(funcs, "functions"));
        children.add(new ListNode(vars, "vars"));
        children.add(stmts);
        return children;
    }

    @Override
    public void semanticCheck() {
        for (ProcedureNode proc : procs) {
            proc.semanticCheck();
        }
        for (FunctionNode func : funcs) {
            func.semanticCheck();
        }
        stmts.semanticCheck();
    }

    @Override
    public String toString() {
        return name == null ? "Program" : name.toString();
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = scope;
        for (VarNode var : vars) {
            var.initialize(scope);
        }
        for (ProcedureNode proc : procs) {
            proc.initialize(scope);
        }
        for (FunctionNode func : funcs) {
            func.initialize(scope);
        }
        stmts.initialize(scope);
    }

    public void initialize(GlobalScope globalScope) {
        if (globalScope != null) {
            globalScope.setName(name.name);
        }
        Scope scope = globalScope;
        initialize(scope);
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder code = new StringBuilder();
        code.append(".class public ").append(scope.getName()).append("\n");
        code.append(".super java/lang/Object\n");

        code.append("""
                .method public <init>()V
                   aload_0
                   invokenonvirtual java/lang/Object/<init>()V
                   return
                .end method
                """);

        procs.stream()
                .map(ProcedureNode::generateCode)
                .forEach(code::append);
        funcs.stream()
                .map(FunctionNode::generateCode)
                .forEach(code::append);

        for (VarNode var : vars) {
            for (VarLineNode varLine : var.varLines) {
                Type type = varLine.type;
                for (String s : varLine.variables) {
                    code.append(".field static ")
                            .append(s)
                            .append(" ")
                            .append(type.toString())
                            .append(" ")
                            .append(type.getAbbreviation())
                            .append(" = ")
                            .append(type.getDefaultValue());
                    code.append("\n");
                }
            }
        }

        code.append(".method public static main([Ljava/lang/String;)V");
        //TODO нормально посчитать стек (или убрать туду☺☺☺☺)
        code.append(".limit stack 20\n");
        code.append(".limit locals ").append(scope.getFreeVariableIdentifier()).append("\n");
        code.append(stmts.generateCode());
        code.append("return\n");
        code.append(".end method");
        return code;
    }


}
