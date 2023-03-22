package com.lada.carDealershipApp.model;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Builder
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand", length = 64, nullable = false)
    @Size(min = 1, max = 64)
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "equipment")
    private String equipment;

    @Column(name = "price")
    private int price;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return id != null && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
