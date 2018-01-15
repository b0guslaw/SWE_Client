package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ResponseEnvelope")
@XmlType(propOrder = {"exceptionName","exceptionMessage","state","data","uniquePlayerID"})
public class ResponseEnvelope {
	
	private String exceptionName;
	private String exceptionMessage;
	private String state;
	private String data;
	private UniquePlayerID uniquePlayerID;
	
	public String getExceptionName() {
		return exceptionName;
	}
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String getUniquePlayerID() {
		return uniquePlayerID.getUniquePlayerID();
	}
	
	public void setUniquePlayerID(UniquePlayerID uniquePlayerID) {
		this.uniquePlayerID = uniquePlayerID;
	}
}
