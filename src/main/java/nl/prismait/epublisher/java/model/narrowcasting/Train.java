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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;

import nl.prismait.epublisher.java.model.VersionedBaseEntity;

@Entity
public class Train extends VersionedBaseEntity {

	private String previousStation;
	private String nextStation;
	private String finalDestination;
	private Set<String> trainRides;
	private Set<String> trainEquipments;
	
	private Set<String> rollingStockTypes;
	private String externalDisplayCodes;
	
	
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="train_equipments")
	public Set<String> getTrainEquipments() {
		return trainEquipments;
	}

	public void setTrainEquipments(Set<String> trainEquipments) {
		this.trainEquipments = trainEquipments;
	}
	
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="train_rides")
	public Set<String> getTrainRides() {
		return trainRides;
	}

	public void setTrainRides(Set<String> trainRides) {
		this.trainRides = trainRides;
	}
	
	public String getPreviousStation() {
		return previousStation;
	}
	
	public void setPreviousStation(String previousStation) {
		this.previousStation = previousStation;
	}
	
	public String getNextStation() {
		return nextStation;
	}
	
	public void setNextStation(String nextStation) {
		this.nextStation = nextStation;
	}
	
	public String getFinalDestination() {
		return finalDestination;
	}
	
	public void setFinalDestination(String finalDestination) {
		this.finalDestination = finalDestination;
	}
	
	public String getExternalDisplayCodes() {
		return externalDisplayCodes;
	}

	public void setExternalDisplayCodes(String externalDisplayCodes) {
		this.externalDisplayCodes = externalDisplayCodes;
	}

	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="train_rollingstocktypes")
	public Set<String> getRollingStockTypes() {
		return rollingStockTypes;
	}

	public void setRollingStockTypes(Set<String> rollingStockTypes) {
		this.rollingStockTypes = rollingStockTypes;
	}

	public Train copyObject()	{
		Train TrainClone = new Train();
		
		Set<String> trainRidesClone = new HashSet<>();
		for (String i : trainRides)
		{
			trainRidesClone.add(i);
		}
		
		Set<String> trainEquipmentsClone = new HashSet<>();
		for (String i : trainEquipments)
		{
			trainEquipmentsClone.add(i);
		}
		
		Set<String> rollingStockTypesClone = new HashSet<>();
		for (String i : rollingStockTypes)
		{
			rollingStockTypesClone.add(i);
		}
		
		TrainClone.setTrainRides(trainRidesClone);
		TrainClone.setTrainEquipments(trainEquipmentsClone);
		TrainClone.setFinalDestination(finalDestination);
		TrainClone.setNextStation(nextStation);
		TrainClone.setPreviousStation(previousStation);
		TrainClone.setRollingStockTypes(rollingStockTypesClone);		
		TrainClone.setExternalDisplayCodes(externalDisplayCodes);
		
		return TrainClone;
	}
    
}
