package es.imatia.tracking.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Util {

    public static Timestamp convertStringToTimestamp(String dateString) throws ParseException {
        // Define el formato de fecha y la zona horaria esperada en la cadena
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        // Establece la zona horaria a UTC (puede ajustarse según tus necesidades)
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        // Parsea la cadena en un objeto Date
        Date date = sdf.parse(dateString);

        // Crea un Timestamp a partir del objeto Date
        return new Timestamp(date.getTime());
    }

    public static String convertTimestampToString(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        // Establece la zona horaria a +01:00 (puede ajustarse según tus necesidades)
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+01:00"));

        return sdf.format(timestamp);
    }

}
