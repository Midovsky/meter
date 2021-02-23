package com.maaloul.meter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.maaloul.meter.model.Meter;

public interface MeterRepository extends JpaRepository<Meter, Integer>{

    Boolean existsByMeterId(Long meterId);
    
}
