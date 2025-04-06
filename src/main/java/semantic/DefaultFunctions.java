package semantic;

import nodes.Type;

import java.util.List;

public class DefaultFunctions {
    private static List<String> functions = List.of("writeln");

    public static void addIfDefault(String name, List<Type> types, Scope scope) {
        if (functions.contains(name.toLowerCase())) {
            try {
                scope.addCallable(new Callable(name.toLowerCase(), types));
            } catch (Exception ignored) {
            }
        }
    }
}
