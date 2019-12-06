package utils;

import java.io.File;
import java.io.IOException;

public abstract class Formatting {
    public static String className(String name) {
        name = name.replaceAll("(create|Tc)", "");
        return name.substring(0, 1).toLowerCase().concat(name.substring(1));
    }

    public static String setMethodName(String name) {
        return "set".concat(name.substring(0, 1).toUpperCase()).concat(name.substring(1));
    }

    public static String getLastName(String name) {
        String[] aux = name.split("\\.");
        return aux[aux.length - 1];
    }

    public static String packageToPath(String packagePath) throws IOException {
        String[] temp = packagePath.split("\\.");
        packagePath = "";

        for (String pack : temp) {
            packagePath +=  File.separator + pack;
        }

        return new File(".").getCanonicalPath().concat("\\src").concat(packagePath).concat("\\");
    }

}
