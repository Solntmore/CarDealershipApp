package com.lada.carDealershipApp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.lada.carDealershipApp.model.Car} entity
 */
@Data
@Builder
@Jacksonized
public class RequestCarDto implements Serializable {

    @NotBlank
    @Size(min = 1, max = 64)
    private final String brand;

    @NotBlank
    @Size(min = 1, max = 64)
    private final String model;

    @NotBlank
    @Size(min = 1, max = 64)
    private final String equipment;

    @NotNull
    @Min(1)
    private final int price;
}