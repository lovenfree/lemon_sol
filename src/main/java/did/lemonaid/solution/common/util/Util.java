package did.lemonaid.solution.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.TextUtils;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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


  public static boolean isLocalIP(String address) {
      log.info("utils:"+address);
    List<IpAddressMatcher> ipAddressMatchers = List.of(new IpAddressMatcher("127.0.0.1"),new IpAddressMatcher("0:0:0:0:0:0:0:1"));
      return ipAddressMatchers.stream().anyMatch(matcher->matcher.matches(address));

  }
}
