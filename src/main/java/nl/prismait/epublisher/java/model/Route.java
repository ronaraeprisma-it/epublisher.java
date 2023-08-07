// ***************************************************************************
//
//		Copyright 2011, Prisma-IT (www.prisma-it.com)
//		All rights reserved
//
//		$HeadURL: https://svn.prisma-it.com/epublisher/ePublisherJavaBlazeDSWeb/trunk/src/main/java/nl/prismait/epublisher/java/model/Route.java $
//		$Id: FunctionGroup.java 6374 2020-11-20 15:30:48Z d.lopes $
//
// ***************************************************************************
package nl.prismait.epublisher.java.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * @author d.lopes
 *
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Route extends VersionedBaseEntity {

	private String company;
	private String route;
	private Integer floor;
	private Location location;
	private String routeIcon;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinTable(
            name = "route_location",
            joinColumns = @JoinColumn(name = "route_id "),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
	@OrderBy("name")
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public String getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getRoute() {
		return route;
	}
	
	public void setRoute(String route) {
		this.route = route;
	}

	public String getRouteIcon() {
		return routeIcon;
	}

	public void setRouteIcon(String routeIcon) {
		this.routeIcon = routeIcon;
	}
	
}
