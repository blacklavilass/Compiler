package semantic;

import nodes.ExprNode;
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

    public static boolean isDefault(String name) {
        return functions.contains(name.toLowerCase());
    }

    public static StringBuilder generate(String functionName, List<ExprNode> arguments) {
        StringBuilder code = new StringBuilder();
        if (functionName.equalsIgnoreCase("writeln")) {
            code.append("getstatic java/lang/System/out Ljava/io/PrintStream;\n");
            code.append("""
                    new java/lang/StringBuilder
                    dup
                    invokespecial java/lang/StringBuilder/<init>()V
                    """);
            for (ExprNode arg : arguments) {
                code.append(arg.generateCode());
                code.append("invokevirtual java/lang/StringBuilder/append(").append(arg.getType().getAbbreviation()).append(")Ljava/lang/StringBuilder;\n");
            }
            code.append("invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;\n");
            code.append("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n");
        }
        return code;
    }
}
