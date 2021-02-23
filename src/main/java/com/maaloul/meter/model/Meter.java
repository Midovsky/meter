package com.maaloul.meter.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meters")
public class Meter implements Serializable{
    @Id
	private Long meterId;
		
	private String deviceType;

	private String region;

	private String comType;

	private String amrRouter;

	private String logicalDeviceName;

	private String constructor;

   

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public String getConstructor() {
        return constructor;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor;
    }

    

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getComType() {
        return comType;
    }

    public void setComType(String comType) {
        this.comType = comType;
    }

    public String getAmrRouter() {
        return amrRouter;
    }

    public void setAmrRouter(String amrRouter) {
        this.amrRouter = amrRouter;
    }

    public String getLogicalDeviceName() {
        return logicalDeviceName;
    }

    public void setLogicalDeviceName(String logicalDeviceName) {
        this.logicalDeviceName = logicalDeviceName;
    }

	

}
