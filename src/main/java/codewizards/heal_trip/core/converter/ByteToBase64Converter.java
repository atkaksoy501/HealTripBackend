package codewizards.heal_trip.core.converter;

public class ByteToBase64Converter {
    public static String convert(byte[] bytes) {
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }
}
