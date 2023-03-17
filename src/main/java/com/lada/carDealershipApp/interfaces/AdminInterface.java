package com.lada.carDealershipApp.interfaces;


import com.lada.carDealershipApp.dto.RequestCarDto;
import com.lada.carDealershipApp.dto.ResponseCarDto;

public interface AdminInterface {

    public ResponseCarDto addCarToStorage(RequestCarDto requestCarDto); //Добавить авто на склад

    public void deleteCarFromStorage(Long id); //Удалить авто со склада

}
