package nodes;

import semantic.Scope;

import java.util.List;

public interface Node {
    List<? extends Node> children();
    void semanticCheck();

    List<String> tree();
    <T extends Scope> void initialize(final T scope);

    StringBuilder generateCode();
}
