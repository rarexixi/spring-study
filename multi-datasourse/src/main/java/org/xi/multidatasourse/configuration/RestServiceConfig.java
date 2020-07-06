package org.xi.multidatasourse.configuration;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class RestServiceConfig {

    @Bean(name = "basicRestTemplate")
    RestTemplate basicRestTemplate() {

        //使用HttpClient替换默认实现
        HttpClient httpClient = HttpClientBuilder.create().build();
        return getRestTemplate(httpClient);
    }

    @Bean(name = "trustRestTemplate")
    RestTemplate trustRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (x509Certificates, authType) -> true;
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setSSLSocketFactory(connectionSocketFactory);
        CloseableHttpClient httpClient = httpClientBuilder.build();
        RestTemplate restTemplate = getRestTemplate(httpClient);
        return restTemplate;
    }

    private RestTemplate getRestTemplate(HttpClient httpClient) {

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);
        factory.setConnectTimeout(1000 * 60 * 10);
        factory.setReadTimeout(1000 * 60 * 10);
        factory.setConnectionRequestTimeout(1000 * 60 * 10);

        RestTemplate restTemplate = new RestTemplate(factory);
        //解决中文乱码
        //restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(Charsets.UTF_8));
        return restTemplate;
    }
}
