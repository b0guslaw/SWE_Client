package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ResponseEnvelope")
public class ResponseEnvelope {
	@XmlElement(name = "exceptionName")
	private String exceptionName;
	@XmlElement(name = "exceptionMessage")
	private String exceptionMessage;
	@XmlElement(name = "state")
	private String state;
	@XmlElement(name = "data")
	private UniquePlayerIdentifier data;
	
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
	public String getUniquePlayerIdentifier() {
		return data.getUniquePlayerID();
	}
	public void setUniquePlayerIdentifier(String uniquePlayerID) {
		this.data.setUniquePlayerID(uniquePlayerID);
	}
	
	public static class UniquePlayerIdentifier {
		private String uniquePlayerID;

		public String getUniquePlayerID() {
			return uniquePlayerID;
		}

		public void setUniquePlayerID(String uniquePlayerID) {
			this.uniquePlayerID = uniquePlayerID;
		}
	}
}
