package type;

import javax.xml.datatype.XMLGregorianCalendar;
import utils.Formatting;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class NativeJava {

    public static Object[] getObject(String type) throws IllegalAccessException, InstantiationException {
        if (type.contains("jaxb")) {
            return null;
        }

        if (type.contains(".")) {
            type = Formatting.getLastName(type);
        }

        switch (type) {
            case "String":
                return new Object[]{ String.class, new String("") };
            case "BigInteger":
                return new Object[]{ BigInteger.class, new BigInteger(String.valueOf(0)) };
            case "BigDecimal":
                return new Object[]{ BigDecimal.class, new BigDecimal(String.valueOf(0)) };
            case "Integer":
                return new Object[]{ Integer.class, new Integer(0) };
            case "int":
                return new Object[]{ int.class, 0 };
            case "byte":
                return new Object[]{ byte.class, (byte) 0 };
            case "Byte":
                return new Object[]{ Byte.class, new Byte((byte) 0) };
            case "XMLGregorianCalendar":
                return new Object[]{ XMLGregorianCalendar.class, XMLGregorianCalendar.class.newInstance() };
            default:
                return null;
        }

    }
}
