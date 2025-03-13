package nodes;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Node {
    public abstract List<? extends Node> children();

    public List<String> tree() {
        // крайне неэффективно, но относительно просто
        List<String> res = new ArrayList<>();
        res.add(toString());
        List<? extends Node> children = children();
        for (int i = 0; i < children.size() - 1; i++) {
            String ch0 = "├", ch = "│";
            List<String> tree = children.get(i).tree();
            for (int j = 0; j < tree.size(); j++) {
                res.add((j == 0 ? ch0 : ch) + ' ' + tree.get(j));
            }
        }
        String ch0 = "└", ch = " ";
        if (children.isEmpty()) {
            return res;
        }
        List<String> tree = children.get(children.size() - 1).tree();
        for (int j = 0; j < tree.size(); j++) {
            res.add((j == 0 ? ch0 : ch) + ' ' + tree.get(j));
        }
        /*
                int i = 0;
                for (AstNode child : children()) {
            String ch0 = "├", ch = "│";
            // изменили строку, если что поломалось, то вина туть
            if (++i == children().size()) {
                ch0 = "└";
                ch = " ";
            }
            Collection<String> tree = child.tree();
            if (tree == null) {
                continue;
            }
            int j = 0;
            for (String s : tree) {
                res.add((j++ == 0 ? ch0 : ch) + ' ' + s);
            }
        }*/
        return res;
    }

    public void printTree(PrintStream printStream) {
        Collection<String> tree = tree();
        if (tree == null) {
            return;
        }
        for (String s : tree) {
            printStream.println(s);
        }
    }
}
