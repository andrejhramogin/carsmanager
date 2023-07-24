package org.cars.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.cars.entity.Car;
import org.cars.entity.CarPage;
import org.cars.entity.CarSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CarCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;


    public CarCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Car> findAllWithFilters(CarPage carPage,
                                        CarSearchCriteria carSearchCriteria) {
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> carRoot = criteriaQuery.from(Car.class);
        Predicate predicate = getPredicate(carSearchCriteria, carRoot);
        criteriaQuery.where(predicate);
        setOrder(carPage, criteriaQuery, carRoot);

        TypedQuery<Car> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(carPage.getPageNumber() * carPage.getPageSize());
        typedQuery.setMaxResults(carPage.getPageSize());

        Pageable pageable = getPageable(carPage);

        long carsCount = getCarsCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, carsCount);
    }

    private Predicate getPredicate(CarSearchCriteria carSearchCriteria,
                                   Root<Car> carRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(carSearchCriteria.getBrand())) {
            predicates.add(
                    criteriaBuilder.like(carRoot.get("brand"),
                            "%" + carSearchCriteria.getBrand() + "%")
            );
        }
        if (Objects.nonNull(carSearchCriteria.getModel())) {
            predicates.add(
                    criteriaBuilder.like(carRoot.get("model"),
                            "%" + carSearchCriteria.getModel() + "%")
            );
        }

        if (carSearchCriteria.getYear() != 0) {
            predicates.add(
                    criteriaBuilder.equal(carRoot.get("year"),
                            carSearchCriteria.getYear())
            );
        }

        if (carSearchCriteria.getPrice() != 0) {
            predicates.add(
                    criteriaBuilder.equal(carRoot.get("price"),
                            carSearchCriteria.getPrice())
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(CarPage carPage,
                          CriteriaQuery<Car> criteriaQuery,
                          Root<Car> carRoot) {
        if (carPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(carRoot.get(carPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(carRoot.get(carPage.getSortBy())));
        }
    }

    private Pageable getPageable(CarPage carPage) {
        Sort sort = Sort.by(carPage.getSortDirection(), carPage.getSortBy());
        return PageRequest.of(carPage.getPageNumber(), carPage.getPageSize(), sort);
    }

    private long getCarsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Car> countRoot = countQuery.from(Car.class);
        countQuery.select(criteriaBuilder.count(countRoot));
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
