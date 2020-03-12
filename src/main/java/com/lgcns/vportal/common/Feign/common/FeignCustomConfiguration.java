package com.lgcns.vportal.common.Feign.common;

import feign.Client;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Component
public class FeignCustomConfiguration {

  @Bean
  public Client feignClient(){
    Client trustSSLSockets = new Client.Default(getSSLSocketFactory(),
      new NoopHostnameVerifier());
    return trustSSLSockets;
  }

  private SSLSocketFactory getSSLSocketFactory() {
    try {
      TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
        @Override
        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
          //Do your validations
          return true;
        }
      };
//      TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
//        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//          return null;
//        }
//        public void checkClientTrusted(X509Certificate[] certs, String authType) {
//        }
//        public void checkServerTrusted(X509Certificate[] certs, String authType) {
//        }
//      }
//      };

      SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
//      sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
      return sslContext.getSocketFactory();
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }
}
