package org.cars.mapper;

import org.cars.dto.CarDto;
import org.cars.dto.request.CreateCarRq;
import org.cars.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "id", source = "carRq.id")
    @Mapping(target = "brand", source = "carRq.brand")
    @Mapping(target = "model", source = "carRq.model")
    @Mapping(target = "year", source = "carRq.year")
    @Mapping(target = "price", source = "carRq.price")
    CarDto requestToDto(CreateCarRq carRq);

    CarDto entityToDto(Car car);

    Car dtoToEntity(CarDto dto);
}
