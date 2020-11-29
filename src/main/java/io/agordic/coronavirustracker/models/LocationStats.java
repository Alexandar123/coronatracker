package io.agordic.coronavirustracker.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LocationStats {

    private Long id;
    private String state;
    private String country;
    private double latestTotalCases;
    private double diffFromPrevDay;


}
