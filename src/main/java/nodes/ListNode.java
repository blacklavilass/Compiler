package nodes;

import java.util.List;

public class ListNode extends Node {
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
    public String toString() {
        return name;
    }
}
