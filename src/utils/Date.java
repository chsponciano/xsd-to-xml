package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Date {

    public static String getLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

}
