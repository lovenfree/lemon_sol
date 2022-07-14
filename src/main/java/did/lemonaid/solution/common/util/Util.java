package did.lemonaid.solution.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Util {
    public static String getMethodName() {
        String name = new Object() {}.getClass().getEnclosingMethod().getName();
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

//  public static String BASE64_ENCODE(byte[] data) {
//      if (data == null || data.length == 0 )
//         throw new InvalidValueException();
//    Base64.Encoder encoder = Base64.getEncoder();
//    byte[] encodedBytes = encoder.encode(data);
//    return String.valueOf(encodedBytes);
//
//  }
}
