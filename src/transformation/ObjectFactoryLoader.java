package transformation;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;

public class ObjectFactoryLoader extends ClassLoader {

    private Integer data;
    private InputStream input;
    private ByteArrayOutputStream buffer;

    public ObjectFactoryLoader(ClassLoader parent) {
        super(parent);
    }

    public Class<?> loadClass(String name, String packagePath) {
        try {
            this.buffer = new ByteArrayOutputStream();
            this.input = Paths.get(name).toUri().toURL().openConnection().getInputStream();

            if (this.input.available() != 0) {

                while ((this.data = this.input.read()) != -1) {
                    this.buffer.write(this.data);
                }

                final byte[] classData = this.buffer.toByteArray();

                this.input.close();
                this.buffer.close();
                this.data = null;

                return defineClass(packagePath, classData, 0, classData.length);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
