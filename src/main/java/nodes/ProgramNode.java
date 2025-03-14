package nodes;

import java.util.ArrayList;
import java.util.List;

public class ProgramNode extends BasicNode {
    VariableNameNode name;
    List<ProcedureNode> procs;
    List<VarNode> vars;
    StmtListNode stmts;

    public ProgramNode(VariableNameNode name, List<ProcedureNode> procs, List<VarNode> vars, StmtListNode stmts) {
        this.name = name;
        this.procs = procs;
        this.vars = vars;
        this.stmts = stmts;
    }
    @Override
    public List<? extends Node> children() {
        List<Node> children = new ArrayList<>();
        children.add(new ListNode(procs, "procedures"));
        children.add(new ListNode(vars, "vars"));
        children.add(stmts);
        return children;
    }

    @Override
    public String toString() {
        return name == null ? "Program" : name.toString();
    }
}
