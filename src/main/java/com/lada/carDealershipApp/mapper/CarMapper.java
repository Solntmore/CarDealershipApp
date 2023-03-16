package com.lada.carDealershipApp.mapper;

import com.lada.carDealershipApp.dto.RequestCarDto;
import com.lada.carDealershipApp.dto.ResponseCarDto;
import com.lada.carDealershipApp.model.Car;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CarMapper {

    Car toEntity(RequestCarDto requestCarDto);

    ResponseCarDto toDto(Car car);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Car partialUpdate(RequestCarDto requestCarDto, @MappingTarget Car car);
}