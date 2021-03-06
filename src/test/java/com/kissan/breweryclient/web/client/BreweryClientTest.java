package com.kissan.breweryclient.web.client;

import com.kissan.breweryclient.web.model.v2.BeerDTO;
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
class BreweryClientTest {
    @Autowired
    BreweryClient breweryClient;

    @Test
    void getBeerById() {
        BeerDTO beerDTO = breweryClient.getBeerById(UUID.randomUUID());

        assertNotNull(beerDTO);
    }

    @Test
    void addBeer() {
        BeerDTO beerDTO = BeerDTO.builder().beerName("New Beer").build();

        URI uri = breweryClient.addBeer(beerDTO);
        assertNotNull(uri);
        System.out.println(uri.toString());
    }

    @Test
    void updateBeer() {
        BeerDTO beerDTO = BeerDTO.builder().beerName("Update Beer").build();

        breweryClient.updateBeer(UUID.randomUUID(), beerDTO);
    }

    @Test
    void deleteBeer() {
        breweryClient.deleteBeer(UUID.randomUUID());
     }
}