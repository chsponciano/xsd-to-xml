package utils;

import transformation.Proceeding;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Validation {
    public static boolean isTheMethodGeneration(final Method current) {
        if (Proceeding.generationMethod != null) {
            return false;
        }

        if (current.getReturnType().getName().contains("JAXBElement")) {
            Proceeding.generationMethod = current;
            return true;
        }

        return false;
    }

    public static boolean thereIsXjcConfig() {
        return ((!(System.getenv("JAXB_HOME") == null)
                && !(System.getenv("JAVA_HOME") == null)
                && (Files.exists(Paths.get(System.getenv("JAXB_HOME"))))
                && ((Files.exists(Paths.get(System.getenv("JAVA_HOME").concat("\\bin\\xjc.bat"))))
                || (Files.exists(Paths.get(System.getenv("JAVA_HOME").concat("\\bin\\xjc.sh"))))))
                || (!(System.getenv("JAVA_HOME") == null)
                && Files.exists(Paths.get(System.getenv("JAVA_HOME").concat("\\bin\\xjc.exe")))));
    }
}
