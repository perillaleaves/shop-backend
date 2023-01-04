package perillaleaves.shop.common;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class HexConverter {

    public String getStringToHex(String str) throws UnsupportedEncodingException {
        byte[] testBytes = str.getBytes(StandardCharsets.UTF_8);
        return DatatypeConverter.printHexBinary(testBytes);
    }
}
