package semantic;

import nodes.Type;

public class Variable implements Comparable<Variable> {
    @Override
    public int compareTo(Variable o) {
        return name.compareTo(o.name);
    }

    private Type type;
    private String name;
    private String identifier;
    private boolean global;

    public Variable(String name, Type type, int identifier) {
        this.type = type;
        this.name = name;
        this.identifier = String.valueOf(identifier);
        this.global = false;
    }

    public Variable(String name, Type type, String identifier) {
        this.type = type;
        this.name = name;
        this.identifier = identifier;
        this.global = true;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable var) {
            return var.getName().equals(name);
        }
        return super.equals(obj);
    }

    public StringBuilder generateGetCode() {
        StringBuilder code = new StringBuilder();
        if (global) {
            code.append("getstatic ").append(identifier).append(" ").append(type.getAbbreviation());
        } else {
            code.append(type.getCommandWordPrefix()).append("load ").append(identifier);
        }
        code.append("       ;").append(name).append("\n");
        return code;
    }

    public StringBuilder generatePutCode() {
        StringBuilder code = new StringBuilder();
        if (global) {
            code.append("putstatic ").append(identifier).append(" ").append(type.getAbbreviation());
        } else {
            code.append(type.getCommandWordPrefix()).append("store ").append(identifier);
        }
        code.append("       ;").append(name).append("\n");
        return code;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
