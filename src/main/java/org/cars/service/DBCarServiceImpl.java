package org.cars.service;

import org.cars.dto.request.CreateCarRq;
import org.cars.exception.ResourceNotFoundException;
import org.cars.dto.CarDto;
import org.cars.entity.Car;
import org.cars.entity.CarPage;
import org.cars.entity.CarSearchCriteria;
import org.cars.mapper.CarMapperImpl;
import org.cars.repository.CarCriteriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.cars.repository.CarJpaRepository;

/**
 * Имплементирует интерфейс CarService
 * Переопределяет методы интерфейса CarService
 */
@Component("dBCarServiceImpl")
public class DBCarServiceImpl implements CarService {

    private static final Logger logger = LoggerFactory.getLogger(DBCarServiceImpl.class);

    @Autowired
    private CarJpaRepository carJpaRepository;
    @Autowired
    private CarCriteriaRepository carCriteriaRepository;
    @Autowired
    private CarMapperImpl mapper;

    public DBCarServiceImpl(CarJpaRepository carJpaRepository,
                            CarCriteriaRepository carCriteriaRepository) {
        this.carJpaRepository = carJpaRepository;
        this.carCriteriaRepository = carCriteriaRepository;
    }

    public DBCarServiceImpl() {
    }

    @Override
    public Page<Car> getCarsWithSortingAndFiltration(CarPage carPage,
                                                     CarSearchCriteria carSearchCriteria) {
        return carCriteriaRepository.findAllWithFilters(carPage, carSearchCriteria);
    }

    //удаление авто по id
    @Override
    public void deleteCarById(int id) {
        carJpaRepository.deleteById(id);
    }

    //создаёт новое авто в таблице "cars" и возвращает его.
    public CarDto createNewCar(CreateCarRq carRq) {
        CarDto carDto = mapper.requestToDto(carRq);
        Car newCar = carJpaRepository.save(mapper.dtoToEntity(carDto));
        return findCarById(newCar.getId());
    }

    //находит в БД и возвращает авто по id.
    @Override
    public CarDto findCarById(int id) {
        return mapper.entityToDto(carJpaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Car with id " + id + " not found")));
    }

    //обновляет поля и возвращает обновленную car
    @Override
    public CarDto update(CreateCarRq carRq, int id) {

        if (carJpaRepository.existsById(id)) {
            CarDto carDto = mapper.requestToDto(carRq);
            Car newCar = mapper.dtoToEntity(carDto);
            Car updatedCar = carJpaRepository.getReferenceById(id);
            newCar.setId(updatedCar.getId());
            carJpaRepository.save(newCar);
            return findCarById(updatedCar.getId());
        }
        return mapper.entityToDto(carJpaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Car with id " + id + " not found")));
    }
}