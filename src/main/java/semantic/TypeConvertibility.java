package semantic;

import nodes.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TypeConvertibility {
    private class TypePair {
        Type typeFrom, typeTo;

        public TypePair(Type typeFrom, Type typeTo) {
            this.typeFrom = typeFrom;
            this.typeTo = typeTo;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            TypePair typePair = (TypePair) o;
            return typeFrom == typePair.typeFrom && typeTo == typePair.typeTo;
        }

        public boolean equals(TypePair typePair) {
            return typeFrom == typePair.typeFrom && typeTo == typePair.typeTo;
        }
    }

    private List<TypePair> typePairs = new ArrayList<TypePair>();

    public TypeConvertibility() {
        typePairs.add(new TypePair(Type.INTEGER, Type.REAL));
        typePairs.add(new TypePair(Type.CHAR, Type.STRING));
    }

    public boolean canConvert(Type typeFrom, Type typeTo) {
        TypePair pair = new TypePair(typeFrom, typeTo);
        for (TypePair curPair : typePairs) {
            if (curPair.equals(pair)) {
                return true;
            }
        }
        return false;
    }
}
