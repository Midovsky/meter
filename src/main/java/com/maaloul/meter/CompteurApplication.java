package com.maaloul.meter;

import javax.annotation.Resource;

import com.maaloul.meter.service.FilesStorageService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompteurApplication implements CommandLineRunner {

	@Resource
	FilesStorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(CompteurApplication.class, args);
	}

	@Override
  public void run(String... arg) throws Exception {
	storageService.deleteAll();
    storageService.init();
  }

}
