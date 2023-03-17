package com.lada.carDealershipApp.controller;

import com.lada.carDealershipApp.dto.ResponseCarDto;
import com.lada.carDealershipApp.service.AdminCarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private final AdminCarService carService;

    @GetMapping("/cars/{carId}")
    public ResponseEntity<ResponseCarDto> getCarById(@PathVariable Long carId) {
        log.debug("A Get/user/cars/{} request was received. Get car from storage", carId);

        return ResponseEntity.status(HttpStatus.OK).body(carService.getCarById(carId));
    }

    @GetMapping("/cars")
    public ResponseEntity<List<ResponseCarDto>> getAllCars(@RequestParam int from, @RequestParam int size) {
        log.debug("A Get/user/cars request was received. Get all cars from storage");

        return ResponseEntity.status(HttpStatus.OK).body(carService.getAllCars(PageRequest.of(from, size)));
    }

}
