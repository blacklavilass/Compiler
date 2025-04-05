package semantic;

import nodes.Type;

import java.util.List;

public class Callable implements Comparable<Callable> {
    //TODO: если упадём, сюда смотреть в первую очередь))))
    @Override
    public int compareTo(Callable o) {
        int a = name.compareTo(o.name);
        int b = (parameters.size() - o.parameters.size());
        if (a != 0) return a;
        if (b != 0) return b;
        int c;
        for (int i = 0; i < parameters.size(); i++) {
            c = parameters.get(i).compareTo(o.parameters.get(i));
            if (c != 0) return c;
        }
        return 0;
    }

    private String name;
    private Type type;
    private List<Type> parameters;

    public Callable(String name, List<Type> parameters) {
        this.name = name;
        this.parameters = parameters;
        this.type = Type.UNDEFINED;
    }

    public Callable(String name, List<Type> parameters, Type returnType) {
        this.name = name;
        this.parameters = parameters;
        this.type = returnType;
    }

    public Type getReturnType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Callable) {
            Callable other = (Callable) obj;
            return this.name.equals(other.name) && this.parameters.equals(other.parameters);
        }
        return false;
    }
}
