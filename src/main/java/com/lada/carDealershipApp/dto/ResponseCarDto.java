package com.lada.carDealershipApp.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.lada.carDealershipApp.model.Car} entity
 */
@Data
@Builder
public class ResponseCarDto implements Serializable {
    private final Long id;
    @Size(min = 1, max = 64)
    private final String brand;
    private final String model;
    private final String equipment;
    private final int price;
}