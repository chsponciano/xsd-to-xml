package utils;

import java.lang.reflect.Method;

public abstract class Validation {
    public static boolean isTheMethodGeneration(Method refGenerationMethod, final Method current) {
        if (refGenerationMethod != null || current.getReturnType().getName().contains("JAXBElement")) {
            return false;
        }

        refGenerationMethod = current;
        return true;
    }
}
