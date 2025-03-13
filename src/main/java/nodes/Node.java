package nodes;

import java.util.List;

public interface Node {
    List<? extends Node> children();

    List<String> tree();
}
