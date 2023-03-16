package com.lada.carDealershipApp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.lada.carDealershipApp.model.Car} entity
 */
@Data
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
    private final int price;
}