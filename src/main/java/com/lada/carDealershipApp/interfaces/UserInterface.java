package com.lada.carDealershipApp.interfaces;

import com.lada.carDealershipApp.dto.ResponseCarDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserInterface {

    public ResponseCarDto getCarById(Long id); //Получить авто по id

    public List<ResponseCarDto> getAllCars(Pageable pageable); //Получить все авто постранично
}
