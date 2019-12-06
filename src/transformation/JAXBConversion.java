package transformation;

import utils.Formatting;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public class JAXBConversion {
    private static JAXBConversion ourInstance;
    public static final String PACKAGE;
    private final Class OBJECT_FACTORY;

    static {
        PACKAGE = "temporary.jaxb";
    }

    private JAXBConversion() throws IOException {
        this.OBJECT_FACTORY = this.loadObjectFactory();
    }

    public static JAXBConversion getInstance() {
        try {
            if (ourInstance == null) {
                ourInstance = new JAXBConversion();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ourInstance;
    }

    private Class loadObjectFactory() throws IOException {
        ClassLoader parentClassLoader = ObjectFactoryLoader.class.getClassLoader();
        ObjectFactoryLoader classLoader = new ObjectFactoryLoader(parentClassLoader);
        return classLoader.loadClass(Formatting.packageToPath(PACKAGE).concat("ObjectFactory.class"), PACKAGE);
    }

    protected JAXBContext getContext() throws JAXBException {
        return JAXBContext.newInstance(PACKAGE);
    }

    protected Class getObjectFactory() {
        return this.OBJECT_FACTORY;
    }
}
