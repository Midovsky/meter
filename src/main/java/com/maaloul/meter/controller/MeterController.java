package com.maaloul.meter.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maaloul.meter.model.Meter;
import com.maaloul.meter.service.MeterDao;

@RestController
@RequestMapping("/api/meters")
public class MeterController {
	
    @Autowired
	private MeterDao meterDao;
	
	@GetMapping
	public List<Meter> getAllMeters(){
		return meterDao.getAllMeters();
	}
	
	@GetMapping("/{id}")
	public Optional<Meter> getMeter(@PathVariable Integer id){
		Optional<Meter> Meter = meterDao.getMeter(id);
		
		if (Meter.isPresent()) {
			return Meter;
		}
		else {
			throw new RuntimeException("Le meter avec cet id n'existe pas " + id);
		}
	}
	
	
	@PostMapping
	public Meter save(@RequestBody Meter Meter) {
		return meterDao.saveMeter(Meter);
		
	}
	
	@PutMapping
	public Meter update(@RequestBody Meter Meter) {
		return meterDao.updateMeter(Meter);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		meterDao.deleteMeter(id);
	}
}
