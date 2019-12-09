package transformation;

import model.MirrorClass;
import org.w3c.dom.Document;
import type.NativeJava;
import utils.Formatting;
import utils.Validation;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.dom.DOMResult;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Proceeding {
    public  static Method generationMethod;
    private static Proceeding ourInstance;
    private JAXBConversion jaxbConversion;

    private Proceeding() {
        this.jaxbConversion = JAXBConversion.getInstance();
    }

    public static Proceeding getInstance() {
        if (ourInstance == null) {
            ourInstance = new Proceeding();
        }
        return ourInstance;
    }

    public Document run() throws Exception {
        Map<String, MirrorClass> map = this.getDataMap();
        Object[] attributeObject = null;

        for (Map.Entry<String, MirrorClass> current : map.entrySet()) {
            for (String attribute : current.getValue().getClassAttributes()) {
                for (Method method : current.getValue().getType().getMethods()) {
                    if (Formatting.setMethodName(attribute).equals(method.getName())) {
                        attributeObject = this.getAttributeObject(map, method.getParameterTypes()[0].getName());
                        try {
                            current.getValue().addAttributeObject(attribute, attributeObject);
                        } finally {
                            attributeObject = null;
                        }
                    }
                }
            }
        }

        Object root = map.get(Formatting.className(Formatting.getLastName(generationMethod.getParameterTypes()[0].getName()))).getObject();
        Object jaxbElement = generationMethod.invoke(this.jaxbConversion.getObjectFactory().newInstance(), root);
        return this.objectToXml(jaxbElement);
    }

    private Object[] getAttributeObject(final Map<String, MirrorClass> map, String type) {
        Object[] attributeObject = null;

        try {

            if ((attributeObject = NativeJava.getObject(type)) == null) {
                attributeObject = new Object[2];
                attributeObject[1] = map.get(Formatting.className(Formatting.getLastName(type))).getObject();
                attributeObject[0] = attributeObject[1].getClass();
            }

        } catch (Exception ex) {
            attributeObject = null;

        } finally {
            return attributeObject;

        }
    }

    private Document objectToXml(Object object) throws JAXBException {
        DOMResult domResult = new DOMResult();
        Marshaller jaxbMarshaller = jaxbConversion.getContext().createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        jaxbMarshaller.marshal(object, domResult);
        return (Document) domResult.getNode();
    }

    private Map<String, MirrorClass> getDataMap() throws InstantiationException, IllegalAccessException {
        Map<String, MirrorClass> map = new HashMap<>();
        String bufferName;

        for (Method current : jaxbConversion.getObjectFactory().getDeclaredMethods()) {
            if (!Validation.isTheMethodGeneration(current)) {
                bufferName = Formatting.className(current.getName());
                map.put(bufferName, new MirrorClass(bufferName, current.getReturnType()));
            }
        }

        return map;
    }
}
