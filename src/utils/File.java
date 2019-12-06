package utils;

import java.io.IOException;
import java.nio.file.Files;

public abstract class File {

    public static void clear(String path) throws IOException {
        clear(new java.io.File(new java.io.File(".").getCanonicalPath()
                .concat("\\src\\".concat(path.replace(".", "\\")))));
    }

    private static void clear(java.io.File file) {
        java.io.File[] contents = file.listFiles();
        if (contents != null) {
            for (java.io.File content : contents) {
                if (!Files.isSymbolicLink(content.toPath())) {
                    clear(content);
                }
            }
        }

        file.delete();
    }

}
