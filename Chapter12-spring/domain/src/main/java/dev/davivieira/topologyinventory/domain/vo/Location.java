package dev.davivieira.topologyinventory.domain.vo;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Location {

    private String address;
    private String city;
    private String state;
    private int zipCode;
    private String country;

    private float latitude;
    private float longitude;
}
