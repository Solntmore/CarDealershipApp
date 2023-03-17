package com.lada.carDealershipApp.service;

import com.lada.carDealershipApp.dto.ResponseCarDto;
import com.lada.carDealershipApp.exception.CarNotFoundException;
import com.lada.carDealershipApp.interfaces.GeneralInterface;
import com.lada.carDealershipApp.mapper.CarMapper;
import com.lada.carDealershipApp.model.Car;
import com.lada.carDealershipApp.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserCarService implements GeneralInterface {

    private final CarRepository carRepository;

    private final CarMapper carMapper;


    @Override
    public ResponseCarDto getCarById(Long id) {
        return carMapper.toDto(getCarIfExist(id));
    }

    @Override
    public List<ResponseCarDto> getAllCars(Pageable pageable) {
        return carRepository.findAll(pageable)
                .stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    private Car getCarIfExist(Long id) { //Получение авто при на наличие авто на складе или проброс исключения
        return carRepository.findById(id).orElseThrow(() ->
                new CarNotFoundException("Car with id " + id + " isn`t exist"));
    }
}
