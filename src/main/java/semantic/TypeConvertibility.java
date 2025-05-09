package semantic;

import nodes.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TypeConvertibility {
    private static class TypePair {
        Type typeFrom, typeTo;
        StringBuilder command;

        public TypePair(Type typeFrom, Type typeTo, String command) {
            this.typeFrom = typeFrom;
            this.typeTo = typeTo;
            this.command = new StringBuilder(command);
        }

        public boolean equals(TypePair typePair) {
            return typeFrom == typePair.typeFrom && typeTo == typePair.typeTo;
        }
    }

    private static final List<TypePair> typePairs;
    static {
        typePairs = new ArrayList<>();
        typePairs.add(new TypePair(Type.INTEGER, Type.REAL, "i2f\n"));
        typePairs.add(new TypePair(Type.CHAR, Type.STRING, "invokestatic java/lang/String/valueOf(C)Ljava/lang/String;\n"));
    }

    public static boolean canConvert(Type typeFrom, Type typeTo) {
        TypePair pair = new TypePair(typeFrom, typeTo, "");
        for (TypePair curPair : typePairs) {
            if (curPair.equals(pair)) {
                return true;
            }
        }
        return false;
    }

    public static StringBuilder generateCode(Type typeFrom, Type typeTo) {
        TypePair pair = new TypePair(typeFrom, typeTo, "");
        for (TypePair curPair : typePairs) {
            if (curPair.equals(pair)) {
                return curPair.command;
            }
        }
        throw new RuntimeException("Can't convert " + typeFrom + " to " + typeTo);
    }
}
