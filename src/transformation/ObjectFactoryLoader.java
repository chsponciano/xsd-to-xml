package transformation;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class ObjectFactoryLoader extends ClassLoader {

    private Integer data;
    private InputStream input;
    private ByteArrayOutputStream buffer;
    private final String OBJECT_FACTORY;

    public ObjectFactoryLoader(ClassLoader parent) {
        super(parent);
        this.OBJECT_FACTORY = JAXBConversion.PACKAGE.concat(".ObjectFactory");
    }

    public Class<?> loadClass(URL path) {
        Class loaded = null;

        try {
            this.buffer = new ByteArrayOutputStream();
            this.input  = path.openConnection().getInputStream();

            if (this.input.available() != 0) {

                while ((this.data = this.input.read()) != -1) {
                    this.buffer.write(this.data);
                }

                final byte[] classData = this.buffer.toByteArray();

                this.input.close();
                this.buffer.close();
                this.data = null;

                loaded = defineClass(this.OBJECT_FACTORY, classData, 0, classData.length);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            return loaded;
        }
    }
}
