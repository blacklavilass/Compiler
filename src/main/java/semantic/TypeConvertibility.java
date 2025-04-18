package semantic;

import nodes.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TypeConvertibility {
    private static class TypePair {
        Type typeFrom, typeTo;

        public TypePair(Type typeFrom, Type typeTo) {
            this.typeFrom = typeFrom;
            this.typeTo = typeTo;
        }

        public boolean equals(TypePair typePair) {
            return typeFrom == typePair.typeFrom && typeTo == typePair.typeTo;
        }
    }

    private static final List<TypePair> typePairs;
    static {
        typePairs = new ArrayList<>();
        typePairs.add(new TypePair(Type.INTEGER, Type.REAL));
        typePairs.add(new TypePair(Type.CHAR, Type.STRING));

    }

    public static boolean canConvert(Type typeFrom, Type typeTo) {
        TypePair pair = new TypePair(typeFrom, typeTo);
        for (TypePair curPair : typePairs) {
            if (curPair.equals(pair)) {
                return true;
            }
        }
        return false;
    }
}
