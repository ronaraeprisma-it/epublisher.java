// ***************************************************************************
// 
//		Copyright 2013, Prisma-IT (www.prisma-it.com)
//		All rights reserved
// 
//		$HeadURL$
//		$Id$
// 
// ***************************************************************************
package nl.prismait.epublisher.java.model.narrowcasting;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Transient;

import nl.prismait.epublisher.java.model.VersionedBaseEntity;

@Entity
public class Geolocation extends VersionedBaseEntity {

	private Double latitude;
	private Double longitude;
	private Double range;
	private String city;
	
	private String externalName;
	private String companyName;
	private String website;
	private String description;
	private String businessArea;
	private String street;
	private String postalCode;
	private String houseNumber;
	private String state;
	private Integer openingHour;
	private Integer openingMinute;
	private Integer closeHour;
	private Integer closeMinute;
	private Integer visitorsPerWeek;
	private Integer percentageMaleVisitors;
	private Integer percentageFemaleVisitors;
	@Transient
	private Date startDate;
	@Transient
	private Date endDate;
	
	
	
	@Transient
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Transient
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getCloseHour() {
		return closeHour;
	}
	public void setCloseHour(Integer closeHour) {
		this.closeHour = closeHour;
	}
	public Integer getCloseMinute() {
		return closeMinute;
	}
	public void setCloseMinute(Integer closeMinute) {
		this.closeMinute = closeMinute;
	}
	public String getExternalName() {
		return externalName;
	}
	public void setExternalName(String externalName) {
		this.externalName = externalName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getOpeningHour() {
		return openingHour;
	}
	public void setOpeningHour(Integer openingHour) {
		this.openingHour = openingHour;
	}
	public Integer getOpeningMinute() {
		return openingMinute;
	}
	public void setOpeningMinute(Integer openingMinute) {
		this.openingMinute = openingMinute;
	}
	public Integer getVisitorsPerWeek() {
		return visitorsPerWeek;
	}
	public void setVisitorsPerWeek(Integer visitorsPerWeek) {
		this.visitorsPerWeek = visitorsPerWeek;
	}
	public Integer getPercentageMaleVisitors() {
		return percentageMaleVisitors;
	}
	public void setPercentageMaleVisitors(Integer percentageMaleVisitors) {
		this.percentageMaleVisitors = percentageMaleVisitors;
	}
	public Integer getPercentageFemaleVisitors() {
		return percentageFemaleVisitors;
	}
	public void setPercentageFemaleVisitors(Integer percentageFemaleVisitors) {
		this.percentageFemaleVisitors = percentageFemaleVisitors;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getRange() {
		return range;
	}
	public void setRange(Double range) {
		this.range = range;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
    
	public Geolocation copyObject()	{
		Geolocation GeoLocationClone = new Geolocation();
		
		GeoLocationClone.setCity(city);
		GeoLocationClone.setLatitude(latitude);
		GeoLocationClone.setLongitude(longitude);
		GeoLocationClone.setRange(range);

		return GeoLocationClone;
	}
    
}
