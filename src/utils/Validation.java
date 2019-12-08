package utils;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Validation {
    public static boolean isTheMethodGeneration(Method refGenerationMethod, final Method current) {
        if (refGenerationMethod != null || current.getReturnType().getName().contains("JAXBElement")) {
            return false;
        }

        refGenerationMethod = current;
        return true;
    }

    public static boolean thereIsXjcConfig() {
        return (!(System.getenv("JAXB_HOME") == null)
                && !(System.getenv("JAVA_HOME") == null)
                && (Files.exists(Paths.get(System.getenv("JAXB_HOME"))))
                && ((Files.exists(Paths.get(System.getenv("JAVA_HOME").concat("\\bin\\xjc.bat"))))
                || (Files.exists(Paths.get(System.getenv("JAVA_HOME").concat("\\bin\\xjc.sh"))))));
    }
}
