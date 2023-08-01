package org.cars.mapper;

import org.cars.dto.CarDto;
import org.cars.dto.request.CreateCarRq;
import org.cars.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapperImpl implements CarMapper {

    @Override
    //из RegisterCarRq в dto
    public CarDto requestToDto(CreateCarRq carRq) {
        if (carRq == null){
            return null;
        }

        CarDto dto = new CarDto();
        dto.setBrand(carRq.brand());
        dto.setModel(carRq.model());
        dto.setYear(carRq.year());
        dto.setPrice(carRq.price());
        return dto;
    }

    //из entity в dto
    @Override
    public CarDto entityToDto(Car car){
        if (car == null){
            return null;
        }
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setYear(car.getYear());
        dto.setPrice(car.getPrice());
        return dto;
    }

    //из dto в entity
    @Override
    public Car dtoToEntity(CarDto dto) {
        Car entity = new Car();
        entity.setBrand(dto.getBrand());
        entity.setModel(dto.getModel());
        entity.setYear(dto.getYear());
        entity.setPrice(dto.getPrice());
        return entity;
    }
}
