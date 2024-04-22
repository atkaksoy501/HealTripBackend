package codewizards.heal_trip.core.converter;

import java.util.Base64;

public class Base64ToByteConverter {
    public static byte[] convert(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }
}
