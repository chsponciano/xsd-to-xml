package type;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import utils.Formatting;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class NativeJava {

    public static Object getObject(String type) {
        if (type.contains("jaxb")) {
            return null;
        }

        if (type.contains(".")) {
            type = Formatting.getLastName(type);
        }

        switch (type) {
            case "String":
                return new String("");
            case "BigInteger":
                return new BigInteger(String.valueOf(0));
            case "BigDecimal":
                return new BigDecimal(String.valueOf(0));
            case "Integer":
                return new Integer(0);
            case "int":
                return new Integer(0);
            case "byte":
                return new Byte((byte) 0);
            case "Byte":
                return new Byte((byte) 0);
            case "XMLGregorianCalendar":
                return new XMLGregorianCalendarImpl();
            default:
                return null;
        }

    }
}
