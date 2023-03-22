package com.lada.carDealershipApp.controller;

import com.lada.carDealershipApp.dto.RequestCarDto;
import com.lada.carDealershipApp.dto.ResponseCarDto;
import com.lada.carDealershipApp.service.AdminCarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class AdminController {

    private final AdminCarService carService;

    @PostMapping("/cars")
    public ResponseEntity<ResponseCarDto> addCarToStorage(@RequestBody @Valid RequestCarDto requestCarDto) {
        log.debug("A Post/admin/cars request was received. Add car to storage");

        return ResponseEntity.status(HttpStatus.CREATED).body(carService.addCarToStorage(requestCarDto));
    }

    @PutMapping("/cars/{carId}")
    public ResponseEntity<ResponseCarDto> updateCarInStorage(@RequestBody @Valid RequestCarDto requestCarDto,
                                                             @PathVariable Long carId) {
        log.debug("A Put/admin/cars request was received. Update car in storage");

        return ResponseEntity.status(HttpStatus.CREATED).body(carService.updateCarInStorage(requestCarDto, carId));
    }

    @DeleteMapping("/cars/{carId}")
    public ResponseEntity<Void> deleteCarFromStorage(@PathVariable Long carId) {
        log.debug("A Delete/admin/cars/{} request was received. Delete car from storage", carId);
        carService.deleteCarFromStorage(carId);

        return ResponseEntity.status(204).build();
    }

    @GetMapping("/cars/{carId}")
    public ResponseEntity<ResponseCarDto> getCarById(@PathVariable Long carId) {
        log.debug("A Get/admin/cars/{} request was received. Get car from storage", carId);

        return ResponseEntity.status(HttpStatus.OK).body(carService.getCarById(carId));
    }

    @GetMapping("/cars")
    public ResponseEntity<List<ResponseCarDto>> getAllCars(@RequestParam(required = false, defaultValue = "0") int from,
                                                           @RequestParam(required = false, defaultValue = "10") int size) {
        log.debug("A Get/admin/cars request was received. Get all cars from storage");

        return ResponseEntity.status(HttpStatus.OK).body(carService.getAllCars(PageRequest.of(from, size)));
    }

    @GetMapping("/init")
    public ResponseEntity<List<ResponseCarDto>> initialize(@RequestParam(required = false, defaultValue = "0") int from,
                                                           @RequestParam(required = false, defaultValue = "10") int size) {
        log.debug("A Get/admin/cars request was received. Get all cars from storage");
        carService.addCarToStorage(RequestCarDto.builder().brand("Лада").model("Веста").equipment("Люкс").price(1163025).build());
        carService.addCarToStorage(RequestCarDto.builder().brand("Лада").model("Гранта").equipment("Актив").price(753850).build());
        carService.addCarToStorage(RequestCarDto.builder().brand("Лада").model("Веста").equipment("Люкс").price(1150000).build());
        carService.addCarToStorage(RequestCarDto.builder().brand("Лада").model("Приора").equipment("Люкс").price(556300).build());
        carService.addCarToStorage(RequestCarDto.builder().brand("Лада").model("Веста").equipment("Актив").price(1202000).build());
        carService.addCarToStorage(RequestCarDto.builder().brand("Лада").model("Икс-рей").equipment("Люкс").price(1250000).build());

        return ResponseEntity.status(HttpStatus.OK).body(carService.getAllCars(PageRequest.of(from, size)));
    }
}
