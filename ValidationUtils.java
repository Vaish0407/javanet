package system.score.vms.utils;

import system.score.vms.exception.ValidationException;
import system.score.vms.exception.ErrorCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class ValidationUtils {

    // Basic regex for IPv4 (checks structure only)
    private static final Pattern IPV4_PATTERN =
            Pattern.compile("^(\\d{1,3}\\.){3}\\d{1,3}$");

    public static void requireNonNull(Object value, String fieldName) {
        if (value == null) {
            throw new ValidationException(ErrorCode.VLITE_ERR_NULL_VALUE, fieldName);
        }
    }

    public static void requireNonEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(ErrorCode.VLITE_ERR_INVALID_INPUT, fieldName);
        }
    }

    public static void requireMinLength(String value, int minLength, String fieldName) {
        if (value == null || value.length() < minLength) {
            throw new ValidationException(ErrorCode.VLITE_ERR_STRING_TOO_SHORT, fieldName);
        }
    }

    public static void requireMaxLength(String value, int maxLength, String fieldName) {
        if (value != null && value.length() > maxLength) {
            throw new ValidationException(ErrorCode.VLITE_ERR_STRING_TOO_LONG, fieldName);
        }
    }


    public static void isValidIPv4(String ip, String fieldName) {
        if (!IPV4_PATTERN.matcher(ip).matches()) {
            //return false;
            throw new ValidationException(ErrorCode.VLITE_ERR_INVALID_IP_ADDRESS,fieldName);
        }
        String[] parts = ip.split("\\.");
        for (String part : parts) {
            int value = Integer.parseInt(part);
            if (value < 0 || value > 255) {
                //return false;
                throw new ValidationException(ErrorCode.VLITE_ERR_INVALID_IP_ADDRESS,fieldName);
            }
        }
        //return true;
    }

    public static void isGreaterThanZero(int value, String fieldName) {
        if (value <= 0) {
            throw new ValidationException(ErrorCode.VLITE_ERR_NOT_ZERO,fieldName);
        }
    }

    public static String currentTimestamp(){
        // Get the current timestamp
        LocalDateTime now = LocalDateTime.now();

        // Format the timestamp as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

}
