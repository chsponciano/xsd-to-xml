package transformation;

import utils.Formatting;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        return classLoader.loadClass(this.getUrlObjectFactory());
    }

    private URL getUrlObjectFactory() throws IOException {
        String packageComplete = Formatting.packageToPath(PACKAGE).concat("ObjectFactory.class");
        return Paths.get(packageComplete).toUri().toURL();
    }

    protected JAXBContext getContext() throws JAXBException {
        return JAXBContext.newInstance(PACKAGE);
    }

    protected Class getObjectFactory() {
        return this.OBJECT_FACTORY;
    }
}
