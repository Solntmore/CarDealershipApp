package com.lada.carDealershipApp;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lada.carDealershipApp.dto.RequestCarDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.lada.carDealershipApp.StaticMethodsAndConstantsForTests.CREATE_CARS;
import static com.lada.carDealershipApp.StaticMethodsAndConstantsForTests.RESET_IDS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql(statements = {RESET_IDS, CREATE_CARS})
class AdminCarControllerMockTest {
    private static ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void makeObjects() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Успешное создание авто")
    public void createCarGetStatus201() throws Exception {
        RequestCarDto requestCarDto = RequestCarDto.builder()
                .brand("Шевроле")
                .model("Ланос")
                .equipment("Драйв")
                .price(750000)
                .build();

        mockMvc.perform(
                        post("/admin/cars")
                                .content(objectMapper.writeValueAsString(requestCarDto))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.brand").value("Шевроле"))
                .andExpect(jsonPath("$.model").value("Ланос"))
                .andExpect(jsonPath("$.equipment").value("Драйв"))
                .andExpect(jsonPath("$.price").value(750000));
    }

    @Test
    @DisplayName("Ошибка при создании авто из-за пустого названия")
    public void createCarWithEmptyBrandGetStatus400() throws Exception {
        RequestCarDto requestCarDto = RequestCarDto.builder()
                .brand(" ")
                .model("Ланос")
                .equipment("Драйв")
                .price(750000)
                .build();

        mockMvc.perform(
                        post("/admin/cars")
                                .content(objectMapper.writeValueAsString(requestCarDto))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Ошибка при создании авто из-за пустой модели")
    public void createCarWithEmptyModelGetStatus400() throws Exception {
        RequestCarDto requestCarDto = RequestCarDto.builder()
                .brand("Шевроле")
                .model(" ")
                .equipment("Драйв")
                .price(750000)
                .build();

        mockMvc.perform(
                        post("/admin/cars")
                                .content(objectMapper.writeValueAsString(requestCarDto))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Ошибка при создании авто из-за пустой комплектации")
    public void createCarWithEmptyEquipmentGetStatus400() throws Exception {
        RequestCarDto requestCarDto = RequestCarDto.builder()
                .brand("Шевроле")
                .model("Ланос")
                .equipment(" ")
                .price(750000)
                .build();

        mockMvc.perform(
                        post("/admin/cars")
                                .content(objectMapper.writeValueAsString(requestCarDto))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Ошибка при создании авто из-за нулевой цены")
    public void createCarWithEmptyPriceGetStatus400() throws Exception {
        RequestCarDto requestCarDto = RequestCarDto.builder()
                .brand("Шевроле")
                .model("Ланос")
                .equipment("Драйв")
                .price(0)
                .build();

        mockMvc.perform(
                        post("/admin/cars")
                                .content(objectMapper.writeValueAsString(requestCarDto))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Успешное удаление авто")
    public void deleteCarGetStatus204() throws Exception {

        mockMvc.perform(
                        delete("/admin/cars/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());

        mockMvc.perform(
                        get("/admin/cars/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Попытка удаления не существующей машины")
    public void deleteCarIsNotExistGetStatus404() throws Exception {

        mockMvc.perform(
                        delete("/admin/cars/88")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Успешное получение авто по id")
    public void getCarGetStatus200() throws Exception {

        mockMvc.perform(
                        get("/admin/cars/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.brand").value("Лада"))
                .andExpect(jsonPath("$.model").value("Веста"))
                .andExpect(jsonPath("$.equipment").value("Люкс"))
                .andExpect(jsonPath("$.price").value(1000000));
    }

    @Test
    @DisplayName("Запрос несуществующей машины")
    public void getCarGetStatus404() throws Exception {

        mockMvc.perform(
                        get("/admin/cars/88")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Успешное получение всех машин")
    public void getAllCarsStatus200() throws Exception {

        mockMvc.perform(
                        get("/admin/cars")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].brand").value("Лада"))
                .andExpect(jsonPath("$[0].model").value("Веста"))
                .andExpect(jsonPath("$[1].brand").value("Лада"))
                .andExpect(jsonPath("$[1].model").value("Веста"))
                .andExpect(jsonPath("$[2].brand").value("Лада"))
                .andExpect(jsonPath("$[2].model").value("Веста"))
                .andExpect(jsonPath("$[4].brand").value("Лада"))
                .andExpect(jsonPath("$[4].model").value("Икс-рей"))
                .andExpect(jsonPath("$[5].brand").value("Лада"))
                .andExpect(jsonPath("$[5].model").value("Гранта"));
    }

    @Test
    @DisplayName("Успешное получение всех машин с пагинацией")
    public void getAllCarsWithPaginationStatus200() throws Exception {

        mockMvc.perform(
                        get("/admin/cars?from=0&size=3")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].brand").value("Лада"))
                .andExpect(jsonPath("$[0].model").value("Веста"))
                .andExpect(jsonPath("$[1].brand").value("Лада"))
                .andExpect(jsonPath("$[1].model").value("Веста"))
                .andExpect(jsonPath("$[2].brand").value("Лада"))
                .andExpect(jsonPath("$[2].model").value("Веста"));
    }

}
