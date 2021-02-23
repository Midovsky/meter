package com.maaloul.meter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.parsers.DocumentBuilderFactory;

import com.maaloul.meter.message.ResponseMessage;
import com.maaloul.meter.model.Meter;
import com.maaloul.meter.repository.MeterRepository;

import org.springframework.stereotype.Service;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import com.maaloul.meter.service.PublisherService;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

@Service
public class XmlParse {

    @Autowired
    MeterRepository meterRepository;
    
    @Autowired
    MeterDao meterDao;

    @Autowired
    PublisherService publisherService;
  
    public void parseFile(File file){
        try {
            //File fXmlFile = new File("uploads/test.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
                    
            
            doc.getDocumentElement().normalize();
        
                    
            NodeList nList = doc.getElementsByTagName("properties");
                    

            for (int temp = 0; temp < nList.getLength(); temp++) {
		    	
		    	
		        Node nNode = nList.item(temp);
		        
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		            Element eElement = (Element) nNode;


                    Meter meter = new Meter();

		            for(int att=0;att<eElement.getElementsByTagName("name").getLength();att++){

                        switch (eElement.getElementsByTagName("name").item(att).getTextContent()) {

                            case "meter_id": meter.setMeterId(Long.parseLong(eElement.getElementsByTagName("value").item(att).getTextContent()));
                                
                                break;
                            case "device_type": meter.setDeviceType(eElement.getElementsByTagName("value").item(att).getTextContent());

                                
                                break;
                            case "region": meter.setRegion(eElement.getElementsByTagName("value").item(att).getTextContent());
                                
                                break;

                            case "com_type": meter.setComType(eElement.getElementsByTagName("value").item(att).getTextContent());

                                
                                break;
                            case "amr_router": meter.setAmrRouter(eElement.getElementsByTagName("value").item(att).getTextContent());
                                
                                break;
                            case "logical_device_name": meter.setLogicalDeviceName(eElement.getElementsByTagName("value").item(att).getTextContent());
                                
                                break;
                            case "constructor": meter.setConstructor(eElement.getElementsByTagName("value").item(att).getTextContent());
                                
                                break;
                            default:
                                break;
                        }

                    }

                    if(meterRepository.existsByMeterId(meter.getMeterId())) {
                            
                        System.out.println("Le meter dont l'ID est: " + meter.getMeterId() + " existe deja.");
                        //message = "Le meter existe dont l'ID est: " + meter.getMeterId() + " existe deja.";

                        //return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Meter with this id already exists"));

                    }  
                    else{
                        meterDao.saveMeter(meter); 
                        publisherService.publish(meter);
                    }
                    
		        }
            }
            } catch (Exception e) {
            e.printStackTrace();
            }
          }
    
    public Boolean checkIfXml(File file){

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();


            return true;
        } catch (Exception e) {
            return false;
        }
        
        
    }

    
}
