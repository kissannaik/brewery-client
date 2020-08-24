package com.kissan.breweryclient.web.client;

import com.kissan.breweryclient.web.model.v2.BeerDTO;
import com.kissan.breweryclient.web.model.v2.CustomerDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "brewery")
public class CustomerClient {
    private String apiHost;
    private String customerEndPoint;
    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDTO getCustomerById(UUID customerId){
        return restTemplate.getForObject(apiHost+customerEndPoint+customerId, CustomerDTO.class);
    }

    public URI addCustomer(CustomerDTO customerDTO) {
        return restTemplate.postForLocation(apiHost+customerEndPoint, customerDTO);
    }

    public void updateCustomer(UUID customerId, CustomerDTO customerDTO) {
        restTemplate.put(apiHost+customerEndPoint+customerId, customerDTO);
    }

    public void deleteCustomer(UUID customerId) {
        restTemplate.delete(apiHost+customerEndPoint+customerId);
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public void setCustomerEndPoint(String customerEndPoint) {
        this.customerEndPoint = customerEndPoint;
    }
}
