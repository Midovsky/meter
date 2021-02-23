package com.maaloul.meter.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.maaloul.meter.message.ResponseMessage;
import com.maaloul.meter.service.FilesStorageService;
import com.maaloul.meter.service.XmlParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class FileController {

  @Autowired
  XmlParse xmlParse;

  @Autowired
  FilesStorageService storageService;


  @PostMapping
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {

    String message = "";

    try {

      File tmpXml = new File("uploads/test.tmp");
      try (OutputStream os = new FileOutputStream(tmpXml)) {
        os.write(file.getBytes());
      }

      if (tmpXml.length() == 0){
        System.out.println("File is empty !!!");
        message = "File is empty: " + file.getOriginalFilename();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

      }
      else {

        // There is no need to store the file 
        //storageService.save(file);
      
        if (xmlParse.checkIfXml(tmpXml)) {

          xmlParse.parseFile(tmpXml);
          message = "Uploaded the file successfully: " + file.getOriginalFilename();
          return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }
        else {
          message = "Not an XML file " + file.getOriginalFilename();
          return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
      }

    }
    catch (Exception e) {
      
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
      }
    }
}
