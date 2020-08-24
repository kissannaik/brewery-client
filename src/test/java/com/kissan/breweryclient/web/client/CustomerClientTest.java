package com.kissan.breweryclient.web.client;

import com.kissan.breweryclient.web.model.v2.BeerDTO;
import com.kissan.breweryclient.web.model.v2.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
class CustomerClientTest {

    @Autowired
    CustomerClient customerClient;

    @Test
    void getCustomerById() {
        CustomerDTO customerDTO = customerClient.getCustomerById(UUID.randomUUID());

        assertNotNull(customerDTO);
    }

    @Test
    void addCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder().name("New Customer").build();

        URI uri = customerClient.addCustomer(customerDTO);
        assertNotNull(uri);
        System.out.println(uri.toString());
    }

    @Test
    void updateCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder().name("Update Customer").build();

        customerClient.updateCustomer(UUID.randomUUID(), customerDTO);
    }

    @Test
    void deleteCustomer() {
        customerClient.deleteCustomer(UUID.randomUUID());
    }
}