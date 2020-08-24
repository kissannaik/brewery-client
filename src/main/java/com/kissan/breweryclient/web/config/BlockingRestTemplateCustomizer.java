package com.kissan.breweryclient.web.config;


import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
    private final Integer connMaxTotal;
    private final Integer connDefaultMaxPerRoute;
    private final Integer connRequestTimeout;
    private final Integer connSocketTimeout;

    public BlockingRestTemplateCustomizer(@Value("${brewery.connMaxTotal}") Integer connMaxTotal,
                                          @Value("${brewery.connDefaultMaxPerRoute}") Integer connDefaultMaxPerRoute,
                                          @Value("${brewery.connRequestTimeout}") Integer connRequestTimeout,
                                          @Value("${brewery.connSocketTimeout}") Integer connSocketTimeout) {
        this.connMaxTotal = connMaxTotal;
        this.connDefaultMaxPerRoute = connDefaultMaxPerRoute;
        this.connRequestTimeout = connRequestTimeout;
        this.connSocketTimeout = connSocketTimeout;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(connMaxTotal);
        connectionManager.setDefaultMaxPerRoute(connDefaultMaxPerRoute);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(connRequestTimeout)
                .setSocketTimeout(connSocketTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
