package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="newMapNode")
@XmlType(propOrder = {"X","Y","fortPresent","terrain"})
public class NewMapNode {
	private int X;
	private int Y;
	private boolean fortPresent;
	private String terrain;
	
	public int getX() {
		return X;
	}
	public void setX(int X) {
		this.X = X;
	}
	
	public int getY() {
		return Y;
	}
	public void setY(int Y) {
		this.Y = Y;
	}
	public String getTerrain() {
		return terrain;
	}
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	public boolean isFortPresent() {
		return fortPresent;
	}
	public void setFortPresent(boolean fortPresent) {
		this.fortPresent = fortPresent;
	}
}
