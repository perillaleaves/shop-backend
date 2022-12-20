package perillaleaves.community.config;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class EncryptUtils {

    public static String sha256(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuffer result = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String data = Integer.toHexString(hash[i] & 0xff);
                if (data.length() == 1) result.append('0');
                result.append(data);
            }

            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
