package com.lada.carDealershipApp.controller;

import com.lada.carDealershipApp.dto.RequestCarDto;
import com.lada.carDealershipApp.dto.ResponseCarDto;
import com.lada.carDealershipApp.interfaces.AdminInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    private AdminInterface carService;

    @PostMapping("/cars")
    public ResponseEntity<ResponseCarDto> addCarToStorage(@RequestBody @Valid RequestCarDto requestCarDto) {
        log.debug("A Post/admin/cars request was received. Add car to storage");

        return ResponseEntity.status(HttpStatus.CREATED).body(carService.addCarToStorage(requestCarDto));
    }

    @DeleteMapping("/cars/{carId}")
    public ResponseEntity<Void> deleteCarFromStorage(@PathVariable String carId) {
        log.debug("A Delete/admin/cars/{} request was received. Delete car from storage", carId);

        return ResponseEntity.status(204).build();
    }

    @GetMapping("/cars/{carId}")
    public ResponseEntity<ResponseCarDto> getCarById(@PathVariable Long carId) {
        log.debug("A Get/admin/cars/{} request was received. Get car from storage", carId);

        return ResponseEntity.status(HttpStatus.OK).body(carService.getCarById(carId));
    }

    @GetMapping("/cars")
    public ResponseEntity<List<ResponseCarDto>> getAllCars(@RequestParam int from, @RequestParam int size) {
        log.debug("A Get/admin/cars request was received. Get all cars from storage");

        return ResponseEntity.status(HttpStatus.OK).body(carService.getAllCars(PageRequest.of(from, size)));
    }

}
