package perillaleaves.community.common;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;

public class HexConverter {

    public String getStringToHex(String str) throws UnsupportedEncodingException {
        byte[] testBytes = str.getBytes("UTF-8");
        return DatatypeConverter.printHexBinary(testBytes);
    }
}
