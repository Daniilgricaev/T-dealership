package com.orangemask.repository;

import com.orangemask.entity.CarOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOrderRepo extends JpaRepository<CarOrderEntity,Long> {
}
