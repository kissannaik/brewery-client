package com.kissan.breweryclient.web.model.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDTO {
    private UUID id;
    private Integer version;

    private OffsetDateTime createdDate;
    private OffsetDateTime lasModifiedDate;

    private String beerName;
    private BeerStyleEnum beerStyle;
    private Long upc;

    private Integer quantityOnHand;
    private double price;
}
