package com.maaloul.meter.service;



import java.util.List;
import java.util.Optional;

import com.maaloul.meter.model.Meter;

public interface MeterDao {

    Optional<Meter> getMeter(Integer id);
	
	List<Meter> getAllMeters();
	
	Meter saveMeter(Meter meter);
	
	Meter updateMeter(Meter meter);

	void deleteMeter(Integer id);
}
