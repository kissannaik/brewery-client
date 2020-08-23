package com.kissan.breweryclient.web.client;

import com.kissan.breweryclient.web.model.v2.BeerDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "brewery", ignoreUnknownFields = false)
public class BreweryClient {
    private String apiHost;
    private String beerEndPoint;
    private RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDTO getBeerById(UUID beerId){
        return restTemplate.getForObject(apiHost+beerEndPoint+beerId.toString(), BeerDTO.class);
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public void setBeerEndPoint(String beerEndPoint) {
        this.beerEndPoint = beerEndPoint;
    }


}
