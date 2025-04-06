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
}
