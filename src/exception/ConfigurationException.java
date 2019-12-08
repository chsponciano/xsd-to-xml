package exception;

public class ConfigurationException extends Exception {
    private String error;

    public ConfigurationException(String error) {
        super(error);
        this.error = error;
    }

    public ConfigurationException() {
        super("É necessário a configuração do XJC e JAXB neste dispositivo antes da utilização!");
        this.error = this.getMessage();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
