import exception.ConfigurationException;
import utils.Date;
import utils.Validation;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            if (!Validation.thereIsXjcConfig()) {
                throw new ConfigurationException();
            }

            String pathFile = new File(".").getCanonicalPath() + "\\src\\test.xsd";

            generation.Proceeding gProceeding = generation.Proceeding.getInstance();
            gProceeding.setXsdCurrent(pathFile);

            System.out.println(Date.getLocalDateTime() + " - Iniciando geração das classes JAXB");

            if (gProceeding.run()) {
                System.out.println(Date.getLocalDateTime() + " - Classes JAXB geradas com sucesso");
                System.out.println(Date.getLocalDateTime() + " - Iniciando conversão das classes JAXB para XML");

                transformation.Proceeding tProceeding = transformation.Proceeding.getInstance();
                tProceeding.run();

            } else {
                System.err.println(Date.getLocalDateTime() + " - Erro na geração das classes JAXB");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
