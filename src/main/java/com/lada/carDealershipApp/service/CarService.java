package com.lada.carDealershipApp.service;

import com.lada.carDealershipApp.dto.RequestCarDto;
import com.lada.carDealershipApp.dto.ResponseCarDto;
import com.lada.carDealershipApp.exception.CarNotFoundException;
import com.lada.carDealershipApp.interfaces.AdminInterface;
import com.lada.carDealershipApp.interfaces.UserInterface;
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
public class CarService implements AdminInterface, UserInterface {

    private final CarRepository carRepository;

    private final CarMapper carMapper;

    @Override
    public ResponseCarDto addCarToStorage(RequestCarDto requestCarDto) {
        Car car = carRepository.save(carMapper.toEntity(requestCarDto));
        return carMapper.toDto(car);
    }

    @Override
    public void deleteCarFromStorage(Long id) {
        if (isCarExist(id)) {
            carRepository.deleteById(id);
        }
    }

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

    private boolean isCarExist(Long id) { //Проверка на наличие авто на складе
        if (carRepository.existsById(id)) {
            return true;
        }
        throw new CarNotFoundException("Car with id " + id + " isn`t exist");
    }

    private Car getCarIfExist(Long id) { //Получение авто при на наличие авто на складе или проброс исключения
        return carRepository.findById(id).orElseThrow(() ->
                new CarNotFoundException("Car with id " + id + " isn`t exist"));
    }
}
