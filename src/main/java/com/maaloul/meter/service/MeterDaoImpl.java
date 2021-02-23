package com.maaloul.meter.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maaloul.meter.model.Meter;
import com.maaloul.meter.repository.MeterRepository;

@Service
public class MeterDaoImpl implements MeterDao{

    @Autowired
	private MeterRepository meterRepository;

	@Override
	public Optional<Meter> getMeter(Integer id) {
		// TODO Auto-generated method stub
		return meterRepository.findById(id);
	}

	@Override
	public List<Meter> getAllMeters() {
		// TODO Auto-generated method stub
		return meterRepository.findAll();
	}

	@Override
	public Meter saveMeter(Meter meter) {
		// TODO Auto-generated method stub
		return meterRepository.save(meter);
	}

	@Override
	public Meter updateMeter(Meter meter) {
		// TODO Auto-generated method stub
		return meterRepository.saveAndFlush(meter);
	}

	@Override
	public void deleteMeter(Integer id) {
		// TODO Auto-generated method stub
		meterRepository.deleteById(id);

	}
    
}
