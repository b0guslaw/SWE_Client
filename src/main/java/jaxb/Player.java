package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="player")
@XmlType(propOrder = {"uniquePlayerID","firstName","lastName","studentID","state","collectedTreasure"})
public class Player {
	private String uniquePlayerID;
	private String firstName;
	private String lastName;
	private String studentID;
	private String state;
	private boolean collectedTreasure;
	
	public String getUniquePlayerID() {
		return uniquePlayerID;
	}
	public void setUniquePlayerID(String uniquePlayerID) {
		this.uniquePlayerID = uniquePlayerID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isCollectedTreasure() {
		return collectedTreasure;
	}
	public void setCollectedTreasure(boolean collectedTreasure) {
		this.collectedTreasure = collectedTreasure;
	}
	
}
