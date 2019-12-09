package utils;

import transformation.Proceeding;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Validation {
    public static boolean isSchemeValid(final Source xsd, final Source xml) {
        boolean isValid = false;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema;
        Validator validator;

        try {
            schema = schemaFactory.newSchema(xsd);
            validator = schema.newValidator();
            validator.validate(xml);
            isValid = true;

        } catch (Exception e) {
            isValid = false;

        } finally {
            validator     = null;
            schema        = null;
            schemaFactory = null;

            return isValid;

        }
    }

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
