package nodes;

import semantic.Scope;

import java.util.List;

public class ListNode extends BasicNode {
    List<? extends Node> list;
    String name;

    public ListNode(List<? extends Node> list, String name) {
        this.list = list;
        this.name = name;
    }

    @Override
    public List<? extends Node> children() {
        return list;
    }

    @Override
    public void semanticCheck() {
        //it will never be called; exists just for ast-tree output and has no functional purpose
    }

    @Override
    public StringBuilder generateCode() {
        return new StringBuilder();
    }

    @Override
    public void initialize(Scope scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return name;
    }
}
