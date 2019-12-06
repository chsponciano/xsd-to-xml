package generation;

import transformation.JAXBConversion;
import utils.File;
import utils.Formatting;

import java.io.IOException;

public class Proceeding {
    private static Proceeding ourInstance;
    private final String COMMAND_XJC;
    private final String COMMAND_COMPILE;
    private String xsdCurrent;

    public static Proceeding getInstance() throws IOException {
        if (ourInstance == null) {
            ourInstance = new Proceeding();
        }

        return ourInstance;
    }

    private Proceeding() throws IOException {
        this.COMMAND_XJC =  System.getenv("JAVA_HOME")
                            .concat("\\bin\\xjc -d src -p ")
                            .concat(JAXBConversion.PACKAGE)
                            .concat(" %s");
        this.COMMAND_COMPILE =  "javac "
                                .concat(Formatting.packageToPath(JAXBConversion.PACKAGE))
                                .concat("*.java");
    }

    public boolean run() throws Exception {
        File.clear(JAXBConversion.PACKAGE);
        if (this.processCommand(String.format(this.COMMAND_XJC, this.xsdCurrent))){
           return this.processCommand(this.COMMAND_COMPILE);
        }

        return false;
    }

    public void setXsdCurrent(String xsdCurrent) {
        this.xsdCurrent = xsdCurrent;
    }

    private boolean processCommand (String command) {
        Process process = null;
        boolean outcome = false;

        try {
            process = Runtime.getRuntime().exec(command);
            outcome = process.getErrorStream().read() == -1;

        } finally {
            if (process != null) {
                process.destroyForcibly();
            }

            return outcome;
        }
    }


}
