package com.lada.carDealershipApp.repository;

import com.lada.carDealershipApp.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

    Page<Car> findAll(Pageable pageable);
}